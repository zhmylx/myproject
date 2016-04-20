package utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationUtil {
	
	/**
	 * 得到类中的注解
	 * @param handler 对象
	 * @param clazz 注解类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T getClassAnnotation(Object handler,Class<? extends Annotation> clazz){
		T annotation=null;
        boolean isPresent  = handler.getClass().isAnnotationPresent(clazz);
         if(isPresent){
        	 annotation =(T) handler.getClass().getAnnotation(clazz); 
         }
		return annotation;
	}
	
	/**
	 * 得到方法中的注解
	 * @param handler 方法
	 * @param clazz 注解类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static  <T> T getMethodAnnotation(Method handler,Class<? extends Annotation> clazz){
		T annotation=null;
        boolean isPresent  = handler.isAnnotationPresent(clazz);
         if(isPresent){
        	 annotation =(T) handler.getAnnotation(clazz); 
         }
		return annotation;
	}
	
	

}
