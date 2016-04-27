package base;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import base.dao.BaseDao;

/**
 * 
 * @author Homing Tsang
 *
 * @param <T>
 * 2015年11月3日
 */
public abstract class BaseService<T>{
	
	private static final int INSERT_LIMIT = 100;
	private BaseDao baseDao;
	protected abstract BaseDao getBaseDao();
	protected final Logger log = Logger.getLogger(getClass());
	
	
	/**
	 * @方法名：addBatchLog
	 * 
	 * @方法描述：添加
	 * 
	 * @传入参数：List<TLog> logList（插入集合）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public Integer addBatch(List<T> list) {

		if (null == list) {
			return new Integer(0);
		}

		if (list.isEmpty()) {
			return new Integer(0);
		}

		int divisor = list.size() / INSERT_LIMIT;
		int friction = list.size() % INSERT_LIMIT;
		int page = friction == 0 ? divisor : divisor + 1;
		int resultNum = 0;

		for (int i = 0; i < page; i++) {
			if (i == (page - 1)) {
				if (0 == friction) {
					resultNum += getBaseDao().addBatch(list.subList(i * INSERT_LIMIT, (i + 1) * INSERT_LIMIT));
				} else {
					resultNum += getBaseDao().addBatch(list.subList(i * INSERT_LIMIT, i * INSERT_LIMIT + friction));
				}
			} else {
				resultNum += getBaseDao().addBatch(list.subList(i * INSERT_LIMIT, (i + 1) * INSERT_LIMIT));
			}
		}

		return resultNum;
	}

	/**
	 * @方法名：add
	 * 
	 * @方法描述：添加
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @创建时间：2014年10月31日17时42分21秒
	 * 
	 */
	public void add(T t) {
		getBaseDao().add(t);
	}
	
	
	

	/**
	 * @方法名：delete
	 * 
	 * @方法描述：删除
	 * 
	 * @传入参数：Object[] objects（Array）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public Integer delete(Object[] objects) {
		return getBaseDao().delete(objects);
	}

	/**
	 * @方法名：update
	 * 
	 * @方法描述：修改
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：Integer （影响条数）
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public Integer update(T t) {
		return getBaseDao().update(t);
	}

	/**
	 * @方法名：getList
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：List<T > （T 集合）
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public List<T> getList(T t) {
		return getBaseDao().getList(t);
	}

	/**
	 * @方法名：getLog
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：TLog log（对象）
	 * 
	 * @返回值：TLog log（对象）
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public T getObject(T t) {
		List<T> list = getBaseDao().getList(t);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	/**
	 * @方法名：getMaxId
	 * 
	 * @方法描述：查询
	 * 
	 * @返回值：Integer（Max ID）
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public Long getMaxId() {
		Long id = getBaseDao().getMaxId();
		return id == null ? 0 : id;
	}
	
	/**
	 * @方法名：getObject
	 * 
	 * @方法描述：查询
	 * 
	 * @传入参数：Long id
	 * 
	 * @返回值：T （T 对象）
	 * 
	 * @创建时间：2014年10月31日17时42分21秒
	 * 
	 */
	public T getObject(Long id){
		if(null == id){
			return null;
		}
		return (T) getBaseDao().getObject(id);
	}

	/**
	 * @方法名：count
	 * 
	 * @方法描述：统计
	 * 
	 * @传入参数：T t（对象）
	 * 
	 * @返回值：Long （总条数）
	 * 
	 * @创建时间：2014年11月03日16时05分05秒
	 * 
	 */
	public Long count(T t) {
		return getBaseDao().count(t);
	}

}
