package com.cpersicum.modules.entity.vo;

import com.cpersicum.modules.orm.QueryCondition;
import com.google.common.collect.Lists;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Page<T> implements Iterable<T>, Serializable {
	protected static Logger logger = LoggerFactory.getLogger(Page.class);
	public static final String ASC = "asc";
	public static final String DESC = "desc";
	protected int pageNo = 1;
	protected int pageSize = -1;
	protected boolean autoCount = true;
	protected String orderBy = null;
	protected String order = null;
	protected QueryCondition parameter;
	protected List<T> result = Lists.newArrayList();

	protected long totalItems = 0L;

	protected long totalPage = 0L;

	protected boolean isLastPage = false;

	protected boolean isHasNextPage = false;

	protected boolean isFirstPage = false;

	protected boolean isHasPrePage = false;

	public Page() {
		setPageSize(10);
	}

	public Page(int pageSize) {
		setPageSize(pageSize);
	}

	public Page(int pageNo, int pageSize) {
		setPageNo(pageNo);
		setPageSize(pageSize);
	}

	public int getPageNo() {
		return this.pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1)
			this.pageNo = 1;
	}

	public void setPageNo(String pageNo) {
		String regex = "^-?[1-9]{1}\\d{0,2}(,\\d{3})*?$";
		if (StringUtils.isEmpty(pageNo)) {
			logger.error("error:data is null, please try again!!!");
		} else if (!(pageNo.matches(regex))) {
			logger.error("error: data type is not digit: " + this.totalItems
					+ ", please try again!!!");
		} else {
			this.pageNo = Integer.valueOf(pageNo).intValue();

			if (Integer.valueOf(pageNo).intValue() < 1)
				this.pageNo = 1;
		}
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageSize(String pageSize) {
		String regex = "^-?[1-9]{1}\\d{0,2}(,\\d{3})*?$";
		if (StringUtils.isEmpty(pageSize))
			logger.error("error:data is null, please try again!!!");
		else if (!(pageSize.matches(regex)))
			logger.error("error: data type is not digit: " + this.totalItems
					+ ", please try again!!!");
		else
			this.pageSize = Integer.parseInt(pageSize);
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return this.order;
	}

	public void setOrder(String order) {
		String lowcaseOrder = StringUtils.lowerCase(order);

		String[] orders = StringUtils.split(lowcaseOrder, ',');
		if (orders != null) {
			for (String orderStr : orders) {
				if ((StringUtils.equals("desc", orderStr))
						|| (StringUtils.equals("asc", orderStr)))
					continue;
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
			}
		}

		this.order = lowcaseOrder;
	}

	public boolean isOrderBySetted() {
		return ((StringUtils.isNotBlank(this.orderBy)) && (StringUtils
				.isNotBlank(this.order)));
	}

	public int getOffset() {
		return ((this.pageNo - 1) * this.pageSize);
	}

	public int getStartRow() {
		return (getOffset() + 1);
	}

	public int getEndRow() {
		return (this.pageSize * this.pageNo);
	}

	public List<T> getResult() {
		return this.result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Iterator<T> iterator() {
		return ((this.result == null) ? IteratorUtils.EMPTY_ITERATOR
				: this.result.iterator());
	}

	public long getTotalItems() {
		return this.totalItems;
	}

	public void setTotalItems(long totalItems) {
		this.totalItems = totalItems;
	}

	public void setTotalItems(String totalItems) {
		String regex = "^-?[1-9]{1}\\d{0,2}(,\\d{3})*?$";
		if (StringUtils.isEmpty(totalItems))
			logger.error("error:data is null, please try again!!!");
		else if (!(totalItems.matches(regex)))
			logger.error("error: data type is not digit: " + totalItems
					+ ", please try again!!!");
		else
			setTotalItems(Long.valueOf(totalItems).longValue());
	}

	public boolean isLastPage() {
		return ((getTotalPages() > 1L) && (this.pageNo < getTotalPages()));
	}

	public boolean isHasNextPage() {
		return (this.pageNo + 1 <= getTotalPages());
	}

	public int getNextPage() {
		if (isHasNextPage()) {
			return (this.pageNo + 1);
		}
		return this.pageNo;
	}

	public boolean isFirstPage() {
		return (this.pageNo > 1);
	}

	public boolean isHasPrePage() {
		return (this.pageNo - 1 >= 1);
	}

	public int getPrePage() {
		if (isHasPrePage()) {
			return (this.pageNo - 1);
		}
		return this.pageNo;
	}

	public long getTotalPages() {
		if (this.totalItems < 0L) {
			return -1L;
		}

		long count = this.totalItems / this.pageSize;
		if (this.totalItems % this.pageSize > 0L) {
			count += 1L;
		}
		return count;
	}

	public List<Long> getSlider(int count) {
		int halfSize = count / 2;
		long totalPage = getTotalPages();

		long startPageNumber = Math.max(this.pageNo - halfSize, 1);
		long endPageNumber = Math.min(startPageNumber + count - 1L, totalPage);

		if (endPageNumber - startPageNumber < count) {
			startPageNumber = Math.max(endPageNumber - count, 1L);
		}

		List result = Lists.newArrayList();
		for (long i = startPageNumber; i <= endPageNumber; i += 1L) {
			result.add(new Long(i));
		}
		return result;
	}

	public long getTotalPage() {
		return getTotalPages();
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalPage(String totalPage) {
		String regex = "^-?[1-9]{1}\\d{0,2}(,\\d{3})*?$";
		if (StringUtils.isEmpty(totalPage))
			logger.error("error:data is null, please try again!!!");
		else if (!(totalPage.matches(regex)))
			logger.error("error: data type is not digit: " + this.totalItems
					+ ", please try again!!!");
		else
			setTotalPage(Long.valueOf(totalPage).longValue());
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public void setHasNextPage(boolean isHasNextPage) {
		this.isHasNextPage = isHasNextPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public void setHasPrePage(boolean isHasPrePage) {
		this.isHasPrePage = isHasPrePage;
	}

	public QueryCondition getParameter() {
		return this.parameter;
	}

	public void setParameter(QueryCondition parameter) {
		this.parameter = parameter;
	}
}

