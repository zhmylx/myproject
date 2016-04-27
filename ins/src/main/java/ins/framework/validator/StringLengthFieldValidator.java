package ins.framework.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

public class StringLengthFieldValidator extends FieldValidatorSupport {
	private boolean doTrim;
	private int maxLength;
	private int minLength;

	public StringLengthFieldValidator() {
		this.doTrim = true;
		this.maxLength = -1;
		this.minLength = -1;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public int getMaxLength() {
		return this.maxLength;
	}

	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}

	public int getMinLength() {
		return this.minLength;
	}

	public void setTrim(boolean trim) {
		this.doTrim = trim;
	}

	public boolean getTrim() {
		return this.doTrim;
	}

	public void validate(Object object) throws ValidationException {
		String fieldName = getFieldName();
		String val = (String) getFieldValue(fieldName, object);
		if ((val == null) || (val.length() <= 0)) {
			return;
		}
		if (this.doTrim) {
			val = val.trim();
			if (val.length() <= 0) {
				return;
			}
		}
		int len = val.getBytes().length;
		if ((this.minLength > -1) && (len < this.minLength))
			addFieldError(fieldName, object);
		else if ((this.maxLength > -1) && (len > this.maxLength))
			addFieldError(fieldName, object);
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.validator.StringLengthFieldValidator JD-Core
 * Version: 0.5.4
 */