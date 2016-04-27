package base.dao.hibernate;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;

import utils.AssertUtils;
import utils.ReflectionUtils;
import base.entity.Page;

public class HibernateDao<T, ID extends Serializable> extends
		SimpleHibernateDao<T, ID> {
	public HibernateDao() {
	}

	public HibernateDao(Class<T> entityClass) {
		super(entityClass);
	}

	public Page<T> getAll(Page<T> page) {
		return findPage(page, new Criterion[0]);
	}

	public Page<T> findPage(Page<T> page, String hql, Object[] values) {
		AssertUtils.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		long totalCount = countHqlResult(hql, values);
		page.setTotalItems(totalCount);

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	public Page<T> findPage(Page<T> page, String hql, Map<String, ?> values) {
		AssertUtils.notNull(page, "page不能为空");

		Query q = createQuery(hql, values);

		long totalCount = countHqlResult(hql, values);
		page.setTotalItems(totalCount);

		setPageParameterToQuery(q, page);

		List result = q.list();
		page.setResult(result);
		return page;
	}

	public Page<T> findPage(Page<T> page, Criterion[] criterions) {
		AssertUtils.notNull(page, "page不能为空");

		Criteria c = createCriteria(criterions);

		long totalCount = countCriteriaResult(c);
		page.setTotalItems(totalCount);

		setPageParameterToCriteria(c, page);

		List result = c.list();
		page.setResult(result);
		return page;
	}

	protected Query setPageParameterToQuery(Query q, Page<T> page) {
		AssertUtils.isTrue(page.getPageSize() > 0,
				"Page Size must larger than zero");

		q.setFirstResult(page.getOffset());
		q.setMaxResults(page.getPageSize());

		return q;
	}

	protected Criteria setPageParameterToCriteria(Criteria c, Page<T> page) {
		AssertUtils.isTrue(page.getPageSize() > 0,
				"Page Size must larger than zero");

		c.setFirstResult(page.getOffset());
		c.setMaxResults(page.getPageSize());

		if (page.isOrderBySetted()) {
			String[] orderByArray = StringUtils.split(page.getOrderBy(), ',');
			String[] orderArray = StringUtils.split(page.getOrder(), ',');

			AssertUtils.isTrue(orderByArray.length == orderArray.length,
					"分页多重排序参数中,排序字段与排序方向的个数不相等");

			for (int i = 0; i < orderByArray.length; ++i) {
				if ("asc".equals(orderArray[i]))
					c.addOrder(Order.asc(orderByArray[i]));
				else {
					c.addOrder(Order.desc(orderByArray[i]));
				}
			}
		}
		return c;
	}

	protected long countHqlResult(String hql, Object[] values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = (Long) findUnique(countHql, values);
			return count.longValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	protected long countHqlResult(String hql, Map<String, ?> values) {
		String countHql = prepareCountHql(hql);
		try {
			Long count = (Long) findUnique(countHql, values);
			return count.longValue();
		} catch (Exception e) {
			throw new RuntimeException("hql can't be auto count, hql is:"
					+ countHql, e);
		}
	}

	private String prepareCountHql(String orgHql) {
		String fromHql = orgHql;

		fromHql = "from " + StringUtils.substringAfter(fromHql, "from");
		fromHql = StringUtils.substringBefore(fromHql, "order by");

		String countHql = "select count(*) " + fromHql;
		return countHql;
	}

	protected long countCriteriaResult(Criteria c) {
		CriteriaImpl impl = (CriteriaImpl) c;

		Projection projection = impl.getProjection();
		ResultTransformer transformer = impl.getResultTransformer();

		List orderEntries = null;
		try {
			orderEntries = (List) ReflectionUtils.getFieldValue(impl,
					"orderEntries");

			ReflectionUtils
					.setFieldValue(impl, "orderEntries", new ArrayList());
		} catch (Exception e) {
			this.logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		Long totalCountObject = Long.valueOf(0L);
		Object object = c.setProjection(Projections.rowCount()).uniqueResult();
		if (object instanceof Long)
			totalCountObject = (Long) object;
		else if (object instanceof Integer) {
			totalCountObject = Long.valueOf(((Integer) object).intValue());
		}
		long totalCount = (totalCountObject != null) ? totalCountObject
				.longValue() : 0L;

		c.setProjection(projection);

		if (projection == null) {
			c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		if (transformer != null)
			c.setResultTransformer(transformer);
		try {
			ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);
		} catch (Exception e) {
			this.logger.error("不可能抛出的异常:{}", e.getMessage());
		}

		return totalCount;
	}
}
