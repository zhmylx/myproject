package ins.framework.exception;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private List<ExceptionCause> causeList = new ArrayList();

	public BaseException() {
	}

	public BaseException(String message) {
		super(message);
	}

	public void addCause(ExceptionCause exceptionCause) {
		this.causeList.add(exceptionCause);
	}

	public List<ExceptionCause> getCauseList() {
		return this.causeList;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.exception.BaseException JD-Core Version: 0.5.4
 */