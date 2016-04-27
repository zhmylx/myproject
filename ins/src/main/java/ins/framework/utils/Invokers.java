package ins.framework.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Map;
import java.util.WeakHashMap;
import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtField;
import javassist.CtMethod;
import org.apache.commons.lang3.reflect.MethodUtils;

public class Invokers {
	private static final Map<Method, Invoker> INVOKER_MAP = new WeakHashMap();

	private static final Map<Integer, Invoker> PUBLIC_INVOKER_MAP = new WeakHashMap();

	private static String argumentTypesToString(Class<?>[] argTypes) {
		StringBuilder buf = new StringBuilder();
		buf.append("(");
		if (argTypes != null) {
			for (int i = 0; i < argTypes.length; ++i) {
				if (i > 0) {
					buf.append(", ");
				}
				Class c = argTypes[i];
				buf.append((c == null) ? "null" : c.getName());
			}
		}
		buf.append(")");
		return buf.toString();
	}

	// public static Object invokePublic(Object host, String name, Object[]
	// args) throws NoSuchMethodException { // Byte code:
	// 0: aload_0
	// 1: instanceof 11
	// 4: ifeq +10 -> 14
	// 7: aload_0
	// 8: checkcast 11 java/lang/Class
	// 11: goto +7 -> 18
	// 14: aload_0
	// 15: invokevirtual 12 java/lang/Object:getClass ()Ljava/lang/Class;
	// 18: astore_3
	// 19: aload_2
	// 20: ifnonnull +14 -> 34
	// 23: iconst_1
	// 24: anewarray 13 java/lang/Object
	// 27: dup
	// 28: iconst_0
	// 29: aconst_null
	// 30: aastore
	// 31: goto +4 -> 35
	// 34: aload_2
	// 35: astore_2
	// 36: aload_2
	// 37: arraylength
	// 38: anewarray 11 java/lang/Class
	// 41: astore 4
	// 43: iconst_0
	// 44: istore 5
	// 46: iload 5
	// 48: aload 4
	// 50: arraylength
	// 51: if_icmpge +32 -> 83
	// 54: aload 4
	// 56: iload 5
	// 58: aload_2
	// 59: iload 5
	// 61: aaload
	// 62: ifnonnull +7 -> 69
	// 65: aconst_null
	// 66: goto +10 -> 76
	// 69: aload_2
	// 70: iload 5
	// 72: aaload
	// 73: invokevirtual 12 java/lang/Object:getClass ()Ljava/lang/Class;
	// 76: aastore
	// 77: iinc 5 1
	// 80: goto -34 -> 46
	// 83: iconst_3
	// 84: newarray int
	// 86: astore 5
	// 88: aload 5
	// 90: iconst_0
	// 91: aload_3
	// 92: invokevirtual 14 java/lang/Object:hashCode ()I
	// 95: iastore
	// 96: aload 5
	// 98: iconst_1
	// 99: aload_1
	// 100: invokevirtual 15 java/lang/String:hashCode ()I
	// 103: iastore
	// 104: aload 5
	// 106: iconst_2
	// 107: aload 4
	// 109: invokestatic 16 java/util/Arrays:hashCode ([Ljava/lang/Object;)I
	// 112: iastore
	// 113: aload 5
	// 115: invokestatic 17 java/util/Arrays:hashCode ([I)I
	// 118: istore 6
	// 120: getstatic 18 ins/framework/utils/Invokers:PUBLIC_INVOKER_MAP
	// Ljava/util/Map;
	// 123: iload 6
	// 125: invokestatic 19 java/lang/Integer:valueOf (I)Ljava/lang/Integer;
	// 128: invokeinterface 20 2 0
	// 133: checkcast 21 ins/framework/utils/Invokers$Invoker
	// 136: astore 7
	// 138: aload 7
	// 140: ifnonnull +82 -> 222
	// 143: aload_3
	// 144: aload_1
	// 145: aload 4
	// 147: invokestatic 22
	// org/apache/commons/lang3/reflect/MethodUtils:getMatchingAccessibleMethod
	// (Ljava/lang/Class;Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
	// 150: astore 8
	// 152: aload 8
	// 154: ifnonnull +45 -> 199
	// 157: new 23 java/lang/NoSuchMethodException
	// 160: dup
	// 161: new 2 java/lang/StringBuilder
	// 164: dup
	// 165: invokespecial 3 java/lang/StringBuilder:<init> ()V
	// 168: aload_3
	// 169: invokevirtual 8 java/lang/Class:getName ()Ljava/lang/String;
	// 172: invokevirtual 5 java/lang/StringBuilder:append
	// (Ljava/lang/String;)Ljava/lang/StringBuilder;
	// 175: ldc 24
	// 177: invokevirtual 5 java/lang/StringBuilder:append
	// (Ljava/lang/String;)Ljava/lang/StringBuilder;
	// 180: aload_1
	// 181: invokevirtual 5 java/lang/StringBuilder:append
	// (Ljava/lang/String;)Ljava/lang/StringBuilder;
	// 184: aload 4
	// 186: invokestatic 25 ins/framework/utils/Invokers:argumentTypesToString
	// ([Ljava/lang/Class;)Ljava/lang/String;
	// 189: invokevirtual 5 java/lang/StringBuilder:append
	// (Ljava/lang/String;)Ljava/lang/StringBuilder;
	// 192: invokevirtual 10 java/lang/StringBuilder:toString
	// ()Ljava/lang/String;
	// 195: invokespecial 26 java/lang/NoSuchMethodException:<init>
	// (Ljava/lang/String;)V
	// 198: athrow
	// 199: aload 8
	// 201: invokestatic 27 ins/framework/utils/Invokers:newInvoker
	// (Ljava/lang/reflect/Method;)Lins/framework/utils/Invokers$Invoker;
	// 204: astore 7
	// 206: getstatic 18 ins/framework/utils/Invokers:PUBLIC_INVOKER_MAP
	// Ljava/util/Map;
	// 209: iload 6
	// 211: invokestatic 19 java/lang/Integer:valueOf (I)Ljava/lang/Integer;
	// 214: aload 7
	// 216: invokeinterface 28 3 0
	// 221: pop
	// 222: aload 7
	// 224: aload_0
	// 225: aload_2
	// 226: invokeinterface 29 3 0
	// 231: areturn }
	public static Invoker newInvoker(Method method) {
		Invoker invoker = (Invoker) INVOKER_MAP.get(method);
		if (invoker == null) {
			StringBuilder proxyClassNameBuilder = new StringBuilder();
			proxyClassNameBuilder.append("proxy.invoker.method$");
			proxyClassNameBuilder.append(method.hashCode() + 10000000000L);
			String proxyClassName = proxyClassNameBuilder.toString();
			try {
				Class proxyClass;
				try {
					proxyClass = Class.forName(proxyClassName);
				} catch (Throwable e) {
					ClassPool cp = new ClassPool(true);
					cp.insertClassPath(new ClassClassPath(Invokers.class));
					CtClass cc = cp.makeClass(proxyClassName);
					cc.addField(CtField.make(
							"private java.lang.reflect.Method m;", cc));

					CtConstructor ctConstructor = new CtConstructor(
							new CtClass[] { cp.get(Method.class.getName()) },
							cc);

					ctConstructor
							.setBody("{this.m=(java.lang.reflect.Method)$1;}");

					cc.addConstructor(ctConstructor);
					cc.addInterface(cp.get(Invoker.class.getName()));
					cc.addMethod(CtMethod
							.make("public java.lang.reflect.Method method(){return m;}",
									cc));

					StringBuilder invokeCode = new StringBuilder();
					invokeCode
							.append("public Object invoke(Object host, Object[] args){");

					StringBuilder parameterCode = new StringBuilder();
					for (int i = 0; i < method.getParameterTypes().length; ++i) {
						if (i > 0) {
							parameterCode.append(",");
						}
						Class parameterType = method.getParameterTypes()[i];
						parameterCode.append(generateCast("args[" + i + "]",
								Object.class, parameterType));
					}

					if (method.getParameterTypes().length > 0) {
						invokeCode.append("if(args==null||args.length!=");
						invokeCode.append(method.getParameterTypes().length);
						invokeCode
								.append(")throw new IllegalArgumentException(\"wrong number of arguments\");");
					}

					StringBuilder executeCode = new StringBuilder();

					executeCode.append("((");
					executeCode.append(method.getDeclaringClass()
							.getCanonicalName());

					executeCode.append(")");
					String objCode = (Modifier.isStatic(method.getModifiers())) ? ""
							: "host";

					executeCode.append(objCode);
					executeCode.append(").");
					executeCode.append(method.getName());
					executeCode.append("(");
					executeCode.append(parameterCode);
					executeCode.append(")");

					if (!method.getReturnType().equals(Void.TYPE)) {
						invokeCode.append("return ");
						invokeCode.append(generateCast(executeCode.toString(),
								method.getReturnType(), Object.class));

						invokeCode.append(";");
					} else {
						invokeCode.append(executeCode.toString());
						invokeCode.append(";return null;");
					}
					invokeCode.append("}");
					cc.addMethod(CtMethod.make(invokeCode.toString(), cc));
					proxyClass = cc.toClass();
				}
				invoker = (Invoker) proxyClass.getConstructors()[0]
						.newInstance(new Object[] { method });

				INVOKER_MAP.put(method, invoker);
			} catch (Throwable e) {
				if (e instanceof RuntimeException) {
					throw ((RuntimeException) e);
				}
				throw new RuntimeException(e);
			}
		}
		return invoker;
	}

	public static <T> T newInvoker(Class<T> superClass, Class<?> hostClass,
			String methodName, Class<?>[] hostMethodParameterTypes,
			Class<?> hostMethodReturnType) {
		try {
			methodName = (methodName == null) ? null : methodName.trim();
			StringBuilder proxyClassNameBuilder = new StringBuilder();
			proxyClassNameBuilder.append("proxy.invoker$");
			proxyClassNameBuilder.append(superClass.hashCode() + 10000000000L);
			proxyClassNameBuilder.append("$");
			proxyClassNameBuilder.append(hostClass.hashCode() + 10000000000L);
			proxyClassNameBuilder.append("$");
			if ((methodName != null) && (!methodName.equals(""))) {
				proxyClassNameBuilder.append(methodName);
			}
			proxyClassNameBuilder.append("$");
			if ((hostMethodParameterTypes != null)
					&& (hostMethodParameterTypes.length > 0)) {
				proxyClassNameBuilder.append(10000000000L + Arrays
						.hashCode(hostMethodParameterTypes));
			}

			proxyClassNameBuilder.append("$");
			if (hostMethodReturnType != null) {
				proxyClassNameBuilder
						.append(10000000000L + hostMethodReturnType.hashCode());
			}

			String proxyClassName = proxyClassNameBuilder.toString();
			Class proxyClass;
			try {
				proxyClass = Class.forName(proxyClassName);
			} catch (Exception ex) {
				ClassPool cp = new ClassPool(true);
				cp.insertClassPath(new ClassClassPath(Invokers.class));
				CtClass cc = cp.makeClass(proxyClassName);
				if (superClass.isInterface())
					cc.addInterface(cp.get(superClass.getName()));
				else {
					cc.setSuperclass(cp.get(superClass.getName()));
				}
				Method[] methods = superClass.getMethods();
				for (Method method : methods) {
					int mod = method.getModifiers();
					if (Modifier.isFinal(mod))
						continue;
					if (Modifier.isStatic(mod)) {
						continue;
					}
					Class[] parameterTypes = method.getParameterTypes();
					if ((parameterTypes.length < 1)
							|| ((!hostClass.isAssignableFrom(parameterTypes[0])) && (!parameterTypes[0]
									.isAssignableFrom(hostClass)))) {
						throw new IllegalArgumentException(
								"The first argument is not a host instance");
					}

					if ((hostMethodParameterTypes != null)
							&& (hostMethodParameterTypes.length != parameterTypes.length - 1)) {
						throw new IllegalArgumentException(
								String.format(
										"The host method parameter types'number should be %d",
										new Object[] { Integer
												.valueOf(parameterTypes.length - 1) }));
					}

					Class returnType = method.getReturnType();
					StringBuilder methodCode = new StringBuilder();
					StringBuilder paramCode = new StringBuilder();
					methodCode.append("public ");
					methodCode.append(returnType.getCanonicalName());
					methodCode.append(" ");
					methodCode.append(method.getName());
					methodCode.append("(");
					for (int i = 0; i < parameterTypes.length; ++i) {
						String canonicalName = parameterTypes[i]
								.getCanonicalName();

						if (i > 0) {
							methodCode.append(",");
							if (i > 1) {
								paramCode.append(",");
							}

							if (hostMethodParameterTypes != null) {
								String param = generateCast("p" + i,
										parameterTypes[i],
										hostMethodParameterTypes[(i - 1)]);

								paramCode.append(param);
							} else {
								String param = generateCast("p" + i,
										parameterTypes[i],
										parameterTypes[(i - 1)]);

								paramCode.append(param);
							}
						}
						methodCode.append(canonicalName);
						methodCode.append(" p");
						methodCode.append(i);
					}
					methodCode.append("){");
					StringBuilder executeCode = new StringBuilder();
					executeCode.append("((");
					executeCode.append(hostClass.getCanonicalName());
					executeCode.append(")p0).");
					if (methodName == null)
						executeCode.append(method.getName());
					else {
						executeCode.append(methodName);
					}
					executeCode.append("(");
					executeCode.append(paramCode);
					executeCode.append(")");
					if (!returnType.equals(Void.TYPE)) {
						methodCode.append("return ");
						hostMethodReturnType = (hostMethodReturnType == null) ? returnType
								: hostMethodReturnType;

						String returnCode = generateCast(
								executeCode.toString(), hostMethodReturnType,
								returnType);

						methodCode.append(returnCode);
					} else {
						methodCode.append(executeCode);
					}
					methodCode.append(";");
					methodCode.append("}");
					cc.addMethod(CtMethod.make(methodCode.toString(), cc));
				}
				proxyClass = cc.toClass();
			}
			return (T) proxyClass.newInstance();
		} catch (Exception e) {
			if (e instanceof RuntimeException) {
				throw ((RuntimeException) e);
			}
			throw new RuntimeException(e);
		}
	}

	private static String generateCast(String arg, Class<?> fromClass,
			Class<?> toClass) {
		StringBuilder cast = new StringBuilder();
		if ((fromClass.isPrimitive()) && (!toClass.isPrimitive())) {
			Class wraperClass = toClass;
			if (!isWraper(toClass)) {
				wraperClass = getWraper(fromClass);
			}
			cast.append("(");
			cast.append(toClass.getCanonicalName());
			cast.append(")");
			cast.append(wraperClass.getCanonicalName());
			cast.append(".valueOf((");
			cast.append(getPrimitive(wraperClass).getCanonicalName());
			cast.append(")");
			cast.append(arg);
			cast.append(")");
		} else if ((!fromClass.isPrimitive()) && (toClass.isPrimitive())) {
			cast.append("(");
			cast.append(toClass.getCanonicalName());
			cast.append(")");
			Class wraperClass = fromClass;
			if (!isWraper(fromClass)) {
				wraperClass = getWraper(toClass);
				cast.append("((");
				if (Number.class.isAssignableFrom(wraperClass))
					cast.append(Number.class.getCanonicalName());
				else {
					cast.append(wraperClass.getCanonicalName());
				}
				cast.append(")");
				cast.append(arg);
				cast.append(")");
			} else {
				cast.append(arg);
			}
			cast.append(".");
			cast.append(getPrimitive(wraperClass).getCanonicalName());
			cast.append("Value()");
		} else {
			cast.append("(");
			cast.append(toClass.getCanonicalName());
			cast.append(")");
			if ((fromClass.isPrimitive()) || (toClass.isPrimitive())) {
				cast.append(arg);
			} else {
				cast.append(Invokers.class.getCanonicalName());
				cast.append(".cast(");
				cast.append(arg);
				cast.append(",");
				cast.append(toClass.getCanonicalName());
				cast.append(".class)");
			}
		}
		return cast.toString();
	}

	public static <T> T cast(Object a, Class<T> type) {
		return (T) a;
	}

	private static Class<?> getPrimitive(Class<?> wraperClass) {
		if (wraperClass.equals(Integer.class)) {
			return Integer.TYPE;
		}
		if (wraperClass.equals(Short.class)) {
			return Short.TYPE;
		}
		if (wraperClass.equals(Long.class)) {
			return Long.TYPE;
		}
		if (wraperClass.equals(Float.class)) {
			return Float.TYPE;
		}
		if (wraperClass.equals(Double.class)) {
			return Double.TYPE;
		}
		if (wraperClass.equals(Byte.class)) {
			return Byte.TYPE;
		}
		if (wraperClass.equals(Character.class)) {
			return Character.TYPE;
		}
		if (wraperClass.equals(Boolean.class)) {
			return Boolean.TYPE;
		}
		if (wraperClass.equals(Void.class)) {
			return Void.TYPE;
		}
		return wraperClass;
	}

	private static Class<?> getWraper(Class<?> toClass) {
		if (toClass.equals(Integer.TYPE)) {
			return Integer.class;
		}
		if (toClass.equals(Short.TYPE)) {
			return Short.class;
		}
		if (toClass.equals(Long.TYPE)) {
			return Long.class;
		}
		if (toClass.equals(Float.TYPE)) {
			return Float.class;
		}
		if (toClass.equals(Double.TYPE)) {
			return Double.class;
		}
		if (toClass.equals(Byte.TYPE)) {
			return Byte.class;
		}
		if (toClass.equals(Character.TYPE)) {
			return Character.class;
		}
		if (toClass.equals(Boolean.TYPE)) {
			return Boolean.class;
		}
		if (toClass.equals(Void.TYPE)) {
			return Void.class;
		}
		return toClass;
	}

	private static boolean isWraper(Class<?> toClass) {
		if (toClass.equals(Integer.class)) {
			return true;
		}
		if (toClass.equals(Short.class)) {
			return true;
		}
		if (toClass.equals(Long.class)) {
			return true;
		}
		if (toClass.equals(Float.class)) {
			return true;
		}
		if (toClass.equals(Double.class)) {
			return true;
		}
		if (toClass.equals(Byte.class)) {
			return true;
		}
		if (toClass.equals(Character.class)) {
			return true;
		}
		if (toClass.equals(Boolean.class)) {
			return true;
		}

		return toClass.equals(Void.class);
	}

	public static <T> T newInvoker(Class<T> superClass, Class<?> hostClass,
			String methodName) {
		return newInvoker(superClass, hostClass, methodName, null, null);
	}

	public static <T> T newInvoker(Class<T> superClass, Class<?> hostClass) {
		return newInvoker(superClass, hostClass, null);
	}

	public static abstract interface Invoker {
		public abstract Method method();

		public abstract Object invoke(Object paramObject,
				Object[] paramArrayOfObject);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.utils.Invokers JD-Core Version: 0.5.4
 */