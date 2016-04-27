package ins.framework.exception;

public class ExceptionCause {
	private String messageKey = null;
	private Object[] messageArgs = null;
	private boolean resource = false;
	private int index = -1;

	public ExceptionCause() {
	}

	public ExceptionCause(String messageKey) {
		this(messageKey, true);
	}

	public ExceptionCause(String messageKey, boolean resource) {
		this.messageKey = messageKey;
		this.resource = resource;
	}

	public ExceptionCause(String messageKey, Object messageArgs) {
		this(messageKey, new Object[] { messageArgs });
	}

	public ExceptionCause(String messageKey, Object[] messageArgs) {
		this.messageKey = messageKey;
		this.messageArgs = messageArgs;
		this.resource = true;
	}

	public Object[] getMessageArgs() {
		return this.messageArgs;
	}

	public void setMessageArgs(Object[] messageArgs) {
		this.messageArgs = messageArgs;
	}

	public String getMessageKey() {
		return this.messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public boolean isResource() {
		return this.resource;
	}

	public void setResource(boolean resource) {
		this.resource = resource;
	}

	public int getIndex() {
		return this.index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.exception.ExceptionCause JD-Core Version: 0.5.4
 */