package ins.framework.caches;

public abstract interface Cache<T>
{
  public abstract T get(Object[] paramArrayOfObject);

  public abstract T set(Object[] paramArrayOfObject);

  public abstract T del(Object[] paramArrayOfObject);

  public abstract boolean has(Object[] paramArrayOfObject);

  public abstract void clear(Object[] paramArrayOfObject);

  public abstract void clearAll();
}

/* Location:           C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name:     ins.framework.caches.Cache
 * JD-Core Version:    0.5.4
 */