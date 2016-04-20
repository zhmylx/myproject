package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Homing Tsang
 *
 * @param <T>
 * 2015年11月3日
 */
 
public class Pageable<T extends BaseEntity> implements Serializable {

	private static final long serialVersionUID = -6194225349369776246L;

	private List<T> content = new ArrayList<T>(); // 数据
	private Long total; // 总条数
	private Integer page;// 当前页数
	private Integer pageSzie;// 每页显示条数

	public Pageable() {
		super();
	}

	public Pageable(T t) {
		page = t.getPage();
		pageSzie = t.getLimit();
	}

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSzie() {
		return pageSzie;
	}

	public void setPageSzie(Integer pageSzie) {
		this.pageSzie = pageSzie;
	}

	@Override
	public String toString() {
		return "Pageable [content=" + content + ", total=" + total + ", page=" + page + ", pageSzie=" + pageSzie + "]";
	}

}
