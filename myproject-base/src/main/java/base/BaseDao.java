package base;

import java.util.List;


/**
 * 
 * @author Homing Tsang
 *
 * @param <T>
 * 2015年11月3日
 */
public interface BaseDao<T> {

	/**
	 * @方法名：addBatch
	 * 
	 * @方法描述：添加
	 * 
	 * @传入参数：List<T> list（插入集合）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 */
	public Integer addBatch(List<T> list);

	/**
	 * @方法名：add
	 * 
	 * @方法描述：添加
	 * 
	 * @传入参数：TLog log（对象）
	 * 
	 */
	public void add(T t);
	
	/**
	 * @方法名：addReturnId
	 * 
	 * @方法描述：添加 并返回 插入id
	 * 
	 * @传入参数：TLog log（对象）
	 * 
	 */
	public int addReturnId(T t);

	/**
	 * @方法名：delete
	 * 
	 * @方法描述：删除
	 * 
	 * @传入参数：Object[] objects（Array）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 */
	public Integer delete(Object[] objects);

	/**
	 * @方法名：update
	 * 
	 * @方法描述：修改
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 */
	public Integer update(T t);

	/**
	 * @方法名：getList
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：List<T > （T 集合）
	 * 
	 */
	public List<T> getList(T t);

	/**
	 * @方法名：getMaxId
	 * 
	 * @方法描述：查询
	 * 
	 * @返回值：Integer（Max ID）
	 * 
	 */
	public Long getMaxId();

	/**
	 * @方法名：getObject
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：Long id
	 * 
	 * @返回值：T （T 对象）
	 * 
	 */
	public T getObject(Long id);

	/**
	 * @方法名：count
	 * 
	 * @方法描述：统计
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：Long （总条数）
	 * 
	 */
	public Long count(T t);

}
