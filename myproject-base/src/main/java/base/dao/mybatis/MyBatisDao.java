package base.dao.mybatis;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;

import utils.AssertUtils;
import base.entity.Page;
import base.entity.QueryCondition;

/**
 * 
 * 如：myBatisDao.selectPage(page, "Userlog.selectUserlogPage", (UserlogQueryVo)page.getParameter());
 * 
 * @author Homing tsang
 *
 */
@Component
public class MyBatisDao extends SqlSessionDaoSupport {
	
	/**
	 * sql 方言  ：支持mysql、Oracle
	 */
	private String dialect;

	public String getDialect() {
		return dialect;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	/**
	 * mybatis 插入
	 * @param statementName mapper名称
	 * @param parameter 参数
	 * @return
	 */
	public int insert(String statementName, Object parameter) {
		return getSqlSession().insert(statementName, parameter);
	}

	/**
	 * mybatis 更新 
	 * @param statementName mapper名称
	 * @param parameter 参数
	 * @return
	 */
	public int update(String statementName, Object parameter) {
		return getSqlSession().update(statementName, parameter);
	}

	/**
	 * mybatis 删除
	 * @param statementName mapper名称
	 * @param parameter 参数
	 * @return
	 */
	public int delete(String statementName, Object parameter) {
		return getSqlSession().delete(statementName, parameter);
	}

	/**
	 * mybatis 查询
	 * @param statementName mapper名称
	 * @param parameter 参数
	 * @return
	 */
	public <T> List<T> selectAll(String statementName, Object parameter) {
		List list = getSqlSession().selectList(statementName, parameter);
		return list;
	}

	/**
	 * mybatis 查询
	 * @param statementName mapper名称
	 * @param parameter 参数
	 * @return
	 */
	public <T> List<T> selectList(String statementName, Object parameter) {
		return getSqlSession().selectList(statementName, parameter);
	}

	/**
	 * mybatis 查询一个
	 * @param statementName mapper名称
	 * @param parameter 参数
	 * @return
	 */
	public Object selectOne(String statementName, Object parameter) {
		return getSqlSession().selectOne(statementName, parameter);
	}

	/**
	 * 查询分页
	 * @param page 分页对象
	 * @param statementName mapper名称  默认的查询总数的mapper为：statementNameCount
	 * @param parameter 查询条件
	 * @return
	 */
	public <T> Page<T> selectPage(Page<T> page, String statementName,
			QueryCondition parameter) {
		AssertUtils.notNull(page, "page对象不能为空");
		String countStatementName = statementName.trim() + "Count";
		return selectPage(page, statementName, countStatementName, parameter);
	}

	/**
	 * 查询分页  指定查询总数的mapper
	 * @param page
	 * @param statementName
	 * @param countStatementName
	 * @param parameter
	 * @return
	 */
	public <T> Page<T> selectPage(Page<T> page, String statementName,
			String countStatementName, QueryCondition parameter) {
		RowBounds rowBounds = getLimits(page);
		Number totalItems = (Number) getSqlSession().selectOne(
				countStatementName, parameter);

		if ((totalItems != null) && (totalItems.longValue() > 0L)) {
			List list = getSqlSession().selectList(statementName, parameter,
					rowBounds);
			page.setResult(list);
			page.setTotalItems(totalItems.longValue());
		}
		return page;
	}

	/**
	 * 根据不同的数据 设置分页
	 * @param p
	 * @return
	 */
	protected RowBounds getLimits(Page p) {
		
		if (StringUtils.isEmpty(dialect)&&"ORACLE".equalsIgnoreCase(dialect)) {
			return new RowBounds(p.getStartRow(), p.getEndRow());
		}
		return new RowBounds(p.getOffset(), p.getPageSize());
	}
}

