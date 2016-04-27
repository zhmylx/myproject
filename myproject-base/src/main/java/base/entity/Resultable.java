package base.entity;

import java.io.Serializable;

/**
 * 
 * @author Homing Tsang
 *
 * 2015年11月3日
 */
public class Resultable implements Serializable {

	private static final long serialVersionUID = 7032650742134406471L;

	private Boolean success;// 状态
	private String msg;// 消息
	private Object data;

	public Resultable() {
	}

	public Resultable(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Resultable(Boolean success, String msg, Object data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	public Resultable(Object data) {
		this.success = true;
		this.msg = "";
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Resultable [success=" + success + ", msg=" + msg + ", data=" + data + "]";
	}

}
