package base.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Homing Tsang
 *
 * 2015年11月3日
 */
public class BaseEntity implements Serializable {

	private static final int PAGE = 1;
	private static final int START = 0;
	private static final int LIMIT = 10;
	private static final int ORDER = 1;
	
	
	
	private static final long serialVersionUID = 4566767234709666470L;
	protected Integer page = PAGE; // 当前页数
	protected Integer start = START;// 起始数目
	protected Integer limit = LIMIT;// 每页显示数
	protected Integer order = ORDER;//是否排序

	

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	

}
