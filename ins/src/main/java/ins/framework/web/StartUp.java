package ins.framework.web;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface StartUp
{
  public abstract int priority();

  public abstract boolean ignoreError();
}

/* Location:           C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name:     ins.framework.web.StartUp
 * JD-Core Version:    0.5.4
 */