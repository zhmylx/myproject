package ins.framework.exception;

public class BusinessException extends BaseException {
	private static final long serialVersionUID = 1L;

	public BusinessException() {
	}

	public BusinessException(String messageKey, Object arg) {
		this(messageKey, new Object[] { arg });
	}

	public BusinessException(String messageKey, Object[] args) {
		ExceptionCause cause = new ExceptionCause(messageKey, args);
		addCause(cause);
	}

	public BusinessException(String messageKey, boolean isResource) {
		super(messageKey);
		ExceptionCause cause = new ExceptionCause(messageKey, isResource);
		addCause(cause);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.exception.BusinessException JD-Core Version:
 * 0.5.4
 */