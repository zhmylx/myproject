package utils.template;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

public class FreeMarkerUtils {
	public static void main(String[] args) {
		String test = "name=${name}";
		Map model = new HashMap();
		model.put("name", "张三");
		System.out.println(rendereString(test, model));
	}

	public static String rendereString(String templateString,
			Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString),
					new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException("Freemarker template error", e);
		} catch (TemplateException e) {
			throw new RuntimeException("Freemarker template error", e);
		}
	}

	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (IOException e) {
			throw new RuntimeException("Freemarker template not exist", e);
		} catch (TemplateException e) {
			throw new RuntimeException("Freemarker template error", e);
		}
	}

	public static Configuration buildConfiguration(String directory)
			throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}
}

