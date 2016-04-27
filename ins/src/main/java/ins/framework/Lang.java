package ins.framework;


import ins.framework.utils.Https;
import ins.framework.utils.Https.HttpCallback;
import ins.framework.utils.Invokers;
import ins.framework.utils.Invokers.Invoker;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import org.apache.commons.collections.map.LRUMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.http.cookie.Cookie;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class Lang {

	public static RuntimeException wrapCause(Throwable cause) {
		if (cause instanceof RuntimeException) {
			return (RuntimeException) cause;
		}
		return new RuntimeException(cause);
	}

	public static RuntimeException wrapCause(Throwable cause, String format,
			Object[] args) {
		return new RuntimeException(String.format(format, args), cause);
	}

	public static RuntimeException newCause(String format, Object[] args) {
		return new RuntimeException(String.format(format, args));
	}

	public static Throwable getCause(Throwable e) {
		Throwable cause = e.getCause();
		if (cause == null) {
			return e;
		}
		return getCause(cause);
	}


	public static <T> T[] newArray(T[] args) {
		return args;
	}

	public static <T> List<T> newList(T[] args) {
		return Arrays.asList(args);
	}

	public static <T> Set<T> newSet(T[] args) {
		Set set = new LinkedHashSet();
		for (Object arg : args) {
			set.add(arg);
		}
		return set;
	}

	public static Map<?, ?> newMap(Object[] args) {
		Map map = new HashMap();
		if (args == null) {
			return map;
		}
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException(
					"The number of parameters can not be odd.");
		}

		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[(i + 1)]);
		}
		return map;
	}

	public static Map<?, ?> newWeakMap(Object[] args) {
		Map map = new WeakHashMap();
		if (args == null) {
			return map;
		}
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException(
					"The number of parameters can not be odd.");
		}

		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[(i + 1)]);
		}
		return map;
	}

	public static Map<?, ?> newLRUMap(int maxCapacity, Object[] args) {
		Map map = new LRUMap(maxCapacity);
		if (args == null) {
			return map;
		}
		if (args.length % 2 != 0) {
			throw new IllegalArgumentException(
					"The number of parameters can not be odd.");
		}

		for (int i = 0; i < args.length; i += 2) {
			map.put(args[i], args[(i + 1)]);
		}
		return map;
	}

	

	

	public static void newThread(Runnable run) {
		new Thread(run).start();
	}

	private static int arrayHashCode(Object array) {
		if (array == null) {
			return 0;
		}
		Class arrayClass = array.getClass();
		if (!arrayClass.isArray()) {
			return array.hashCode();
		}
		Class componentType = arrayClass.getComponentType();
		if (componentType.isPrimitive()) {
			if (componentType.equals(Long.TYPE)) {
				return Arrays.hashCode((long[]) (long[]) array);
			}
			if (componentType.equals(Integer.TYPE)) {
				return Arrays.hashCode((int[]) (int[]) array);
			}
			if (componentType.equals(Short.TYPE)) {
				return Arrays.hashCode((short[]) (short[]) array);
			}
			if (componentType.equals(Character.TYPE)) {
				return Arrays.hashCode((char[]) (char[]) array);
			}
			if (componentType.equals(Byte.TYPE)) {
				return Arrays.hashCode((byte[]) (byte[]) array);
			}
			if (componentType.equals(Boolean.TYPE)) {
				return Arrays.hashCode((boolean[]) (boolean[]) array);
			}
			if (componentType.equals(Float.TYPE)) {
				return Arrays.hashCode((float[]) (float[]) array);
			}
			if (componentType.equals(Double.TYPE)) {
				return Arrays.hashCode((double[]) (double[]) array);
			}
		}
		Object[] a = (Object[]) (Object[]) array;

		int result = 1;

		for (int i = 0; i < a.length; ++i) {
			Object element = a[i];
			result = 31 * result + arrayHashCode(element);
		}
		return result;
	}

	public static int hashCode(Object[] array) {
		return arrayHashCode(array);
	}

	public static long identityHashCode(Object[] array) {
		return 10000000000L + arrayHashCode(array);
	}

	private static boolean arrayEquals(Object a, Object a2) {
		if (a == a2) {
			return true;
		}
		if ((a == null) || (a2 == null)) {
			return false;
		}
		Class aClass = a.getClass();
		Class a2Class = a2.getClass();
		if (!aClass.isArray()) {
			return a.equals(a2);
		}
		if (!a2Class.isArray()) {
			return false;
		}
		Class componentType = aClass.getComponentType();
		Class componentType2 = aClass.getComponentType();
		if (!componentType.equals(componentType2)) {
			return false;
		}
		if (componentType.isPrimitive()) {
			if (componentType.equals(Long.TYPE)) {
				return Arrays.equals((long[]) (long[]) a, (long[]) (long[]) a2);
			}
			if (componentType.equals(Integer.TYPE)) {
				return Arrays.equals((int[]) (int[]) a, (int[]) (int[]) a2);
			}
			if (componentType.equals(Short.TYPE)) {
				return Arrays.equals((short[]) (short[]) a,
						(short[]) (short[]) a2);
			}
			if (componentType.equals(Character.TYPE)) {
				return Arrays.equals((char[]) (char[]) a, (char[]) (char[]) a2);
			}
			if (componentType.equals(Byte.TYPE)) {
				return Arrays.equals((byte[]) (byte[]) a, (byte[]) (byte[]) a2);
			}
			if (componentType.equals(Boolean.TYPE)) {
				return Arrays.equals((boolean[]) (boolean[]) a,
						(boolean[]) (boolean[]) a2);
			}
			if (componentType.equals(Float.TYPE)) {
				return Arrays.equals((float[]) (float[]) a,
						(float[]) (float[]) a2);
			}
			if (componentType.equals(Double.TYPE)) {
				return Arrays.equals((double[]) (double[]) a,
						(double[]) (double[]) a2);
			}
		}

		int length = ((Object[]) (Object[]) a).length;
		if (((Object[]) (Object[]) a2).length != length) {
			return false;
		}

		for (int i = 0; i < length; ++i) {
			Object o1 = ((Object[]) (Object[]) a)[i];
			Object o2 = ((Object[]) (Object[]) a2)[i];
			if (o1 == null)
				if (o2 != null)
					;
				else if (!arrayEquals(o1, o2)) {
					return false;
				}
		}

		return true;
	}

	public static boolean equals(Object o, Object o2) {
		return arrayEquals(o, o2);
	}

	


	public static Invokers.Invoker newInvoker(Method method) {
		return Invokers.newInvoker(method);
	}

	


	public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj.getClass().isArray()) {
			return Array.getLength(obj) == 0;
		}
		if (obj instanceof Collection) {
			return ((Collection) obj).isEmpty();
		}
		if (obj instanceof Map) {
			return ((Map) obj).isEmpty();
		}
		if (obj instanceof CharSequence) {
			return obj.toString().trim().equals("");
		}
		return false;
	}

	public static String toString(Object o) {
		if (o == null) {
			return "null";
		}
		if ((o.getClass().isPrimitive()) || (o instanceof CharSequence)
				|| (o instanceof Number)) {
			return o.toString();
		}
		if (o instanceof Throwable) {
			Throwable throwable = (Throwable) o;
			StringWriter sw = new StringWriter();
			throwable.printStackTrace(new PrintWriter(sw));
			return sw.toString();
		}
		if (o instanceof InputStream) {
			try {
				return IOUtils.toString((InputStream) o);
			} catch (IOException e) {
				throw wrapCause(e);
			}
		}
		if (o instanceof Date) {
			return o.getClass()
					.getName()
					.concat("@")
					.concat(Integer.toString(System.identityHashCode(o)))
					.concat("{value = ")
					.concat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
							.format((Date) o)).concat("}");
		}

		return ToStringBuilder.reflectionToString(o);
	}

	public static Document toDocument(Object in) {
		if (in instanceof Document)
			return (Document) in;
		try {
			SAXReader saxReader = new SAXReader();
			if (in instanceof File) {
				return saxReader.read((File) in);
			}
			if (in instanceof URL) {
				return saxReader.read((URL) in);
			}
			if (in instanceof InputStream) {
				return saxReader.read((InputStream) in);
			}
			if (in instanceof Reader) {
				return saxReader.read((Reader) in);
			}
			return saxReader.read(new StringReader(toString(in)));
		} catch (Throwable e) {
			throw wrapCause(e);
		}
	}

	public static Object xpathValue(Object in, String xpathExpression) {
		try {
			if (in instanceof Node) {
				return ((Node) in).selectObject(xpathExpression);
			}
			Document doc = toDocument(in);
			return doc.selectObject(xpathExpression);
		} catch (Throwable e) {
			throw wrapCause(e);
		}
	}

	public static File toFile(Object o) {
		if (o instanceof File) {
			return (File) o;
		}
		if (o instanceof URL) {
			return FileUtils.toFile((URL) o);
		}
		return new File(toString(o));
	}

	public static String readFileToString(Object file, String encoding) {
		try {
			return FileUtils.readFileToString(toFile(file), encoding);
		} catch (IOException e) {
			throw wrapCause(e);
		}
	}

	public static byte[] readFileToByteArray(Object file) {
		try {
			return FileUtils.readFileToByteArray(toFile(file));
		} catch (IOException e) {
			throw wrapCause(e);
		}
	}

	public static void writeStringToFile(Object file, CharSequence data,
			String encoding) {
		try {
			FileUtils.write(toFile(file), data, encoding);
		} catch (IOException e) {
			throw wrapCause(e);
		}
	}

	public static void writeByteArrayToFile(Object file, byte[] data) {
		try {
			FileUtils.writeByteArrayToFile(toFile(file), data);
		} catch (IOException e) {
			throw wrapCause(e);
		}
	}

	public static void deleteFile(Object o) {
		try {
			FileUtils.forceDelete(toFile(o));
		} catch (IOException e) {
			throw wrapCause(e);
		}
	}

	public static boolean moveFile(Object src, Object dest) {
		return toFile(src).renameTo(toFile(dest));
	}

	/*public static Map<?, ?> toMap(Object data) {
		if (data == null) {
			return Collections.unmodifiableMap(new HashMap());
		}
		if (data instanceof Map) {
			return Collections.unmodifiableMap((Map) data);
		}
		return Collections.unmodifiableMap(new PropertyMap(data));
	}*/

	public static void get(String url, Https.HttpCallback httpCallback,
			Object data) {
		Https.get(url, httpCallback, data, null, new Cookie[0]);
	}

	public static void post(String url, Https.HttpCallback httpCallback,
			Object data) {
		Https.post(url, httpCallback, data, null, new Cookie[0]);
	}

	public static void upload(String url, Https.HttpCallback httpCallback,
			File file) {
		Https.upload(url, httpCallback, file, null, new Cookie[0]);
	}

	

}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.Lang JD-Core Version: 0.5.4
 */