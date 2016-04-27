package ins.framework.web;

import ins.framework.Lang;
import ins.framework.log.Logs;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

public class StartUpListener implements ServletContextListener {
	private static final String PARAMETER_NAME_SCAN_PACKAGES = "startup.scan.packages";
	private static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";
	private ResourcePatternResolver resourcePatternResolver;
	private MetadataReaderFactory metadataReaderFactory;

	public StartUpListener() {
		this.resourcePatternResolver = new PathMatchingResourcePatternResolver();

		this.metadataReaderFactory = new CachingMetadataReaderFactory(
				this.resourcePatternResolver);
	}

	private void doStartUp(String scanPackages) {
		List<MethodMetadata> methodMetadatas = new ArrayList();
		for (String basePackage : scanPackages.split(",")) {
			basePackage = '/' + basePackage.trim().replace('.', '/');
			String packageSearchPath = "classpath*:" + basePackage + "/"
					+ "**/*.class";
			try {
				Resource[] resources = this.resourcePatternResolver
						.getResources(packageSearchPath);

				for (Resource resource : resources) {
					MetadataReader metadataReader = this.metadataReaderFactory
							.getMetadataReader(resource);

					Set methodMetadataSet = metadataReader
							.getAnnotationMetadata().getAnnotatedMethods(
									StartUp.class.getCanonicalName());

					methodMetadatas.addAll(methodMetadataSet);
				}
			} catch (Exception e) {
				throw Lang.wrapCause(e);
			}
		}
		Collections.sort(methodMetadatas, new Comparator<MethodMetadata>() {

			public int compare(MethodMetadata o1, MethodMetadata o2) {
				int p1 = ((Integer) o1.getAnnotationAttributes(
						StartUp.class.getCanonicalName()).get("priority"))
						.intValue();

				int p2 = ((Integer) o2.getAnnotationAttributes(
						StartUp.class.getCanonicalName()).get("priority"))
						.intValue();

				return p2 - p1;
			}
		});
		for (MethodMetadata methodMetadata : methodMetadatas)
			try {
				Class cls = Class.forName(methodMetadata
						.getDeclaringClassName());

				Method method = cls.getMethod(methodMetadata.getMethodName(),
						new Class[0]);
				try {
					Logs.info(String.format("Begin execute start up method:%s",
							new Object[] { method }));

					if (methodMetadata.isStatic())
						method.invoke(cls, new Object[0]);
					else {
						method.invoke(cls.newInstance(), new Object[0]);
					}
					Logs.info(String.format(
							"Complete executed start up method:%s",
							new Object[] { method }));
				} catch (Exception e) {
					boolean ignoreError = ((Boolean) methodMetadata
							.getAnnotationAttributes(
									StartUp.class.getCanonicalName()).get(
									"ignoreError")).booleanValue();

					if (!ignoreError) {
						Logs.error(String.format(
								"Failure executed start up method:%s",
								new Object[] { method }));

						throw Lang.wrapCause(e);
					}
					Logs.error(String.format(
							"Failure executed start up method:%s",
							new Object[] { method }), e);
				}

			} catch (ClassNotFoundException e) {
				throw Lang.wrapCause(e);
			} catch (SecurityException e) {
				throw Lang.wrapCause(e);
			} catch (NoSuchMethodException e) {
				throw Lang
						.wrapCause(
								e,
								String.format(
										"The start up method(%s.%s) should be a public and non-arguments method",
										new Object[] {
												methodMetadata
														.getDeclaringClassName(),
												methodMetadata.getMethodName() }),
								new Object[0]);
			} catch (IllegalArgumentException e) {
				throw Lang.wrapCause(e);
			}
	}

	public void contextInitialized(ServletContextEvent sce) {
		String scanPackages = sce.getServletContext().getInitParameter(
				"startup.scan.packages");

		if (!Lang.isEmpty(scanPackages))
			doStartUp(scanPackages);
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
