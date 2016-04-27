package base.dao.mybatis;


import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Component;

import com.alibaba.druid.util.StringUtils;

import utils.AssertUtils;
import utils.config.SystemConfig;
import base.entity.Page;
import base.entity.QueryCondition;

@Component
public class MyBatisDao extends SqlSessionDaoSupport {
	public int insert(String statementName, Object parameter) {
		return getSqlSession().insert(statementName, parameter);
	}

	public int update(String statementName, Object parameter) {
		return getSqlSession().update(statementName, parameter);
	}

	public int delet(String statementName, Object parameter) {
		return getSqlSession().delete(statementName, parameter);
	}

	public <T> List<T> selectAll(String statementName, Object parameter) {
		List list = getSqlSession().selectList(statementName, parameter);
		return list;
	}

	public <T> List<T> selectList(String statementName, Object parameter) {
		return getSqlSession().selectList(statementName, parameter);
	}

	public Object selectOne(String statementName, Object parameter) {
		return getSqlSession().selectOne(statementName, parameter);
	}

	public <T> Page<T> selectPage(Page<T> page, String statementName,
			QueryCondition parameter) {
		AssertUtils.notNull(page, "page对象不能为空");
		String countStatementName = statementName.trim() + "Count";
		return selectPage(page, statementName, countStatementName, parameter);
	}

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

	protected static RowBounds getLimits(Page p) {
		
		String databaseType = SystemConfig.getString("db.dialect");
		if (StringUtils.isEmpty(databaseType)&&"ORACLE".equalsIgnoreCase(databaseType)) {
			return new RowBounds(p.getStartRow(), p.getEndRow());
		}
		return new RowBounds(p.getOffset(), p.getPageSize());
	}
}

