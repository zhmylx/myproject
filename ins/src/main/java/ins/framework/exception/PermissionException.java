package ins.framework.exception;

public class PermissionException extends BaseException {
	private static final long serialVersionUID = 1L;
	private String taskCode = "";

	public PermissionException() {
	}

	public PermissionException(String taskCode) {
		super(taskCode);
		this.taskCode = taskCode;
	}

	public String getTaskCode() {
		return this.taskCode;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.exception.PermissionException JD-Core Version:
 * 0.5.4
 */