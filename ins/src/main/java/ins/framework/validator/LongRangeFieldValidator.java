package ins.framework.validator;

import com.opensymphony.xwork2.validator.validators.AbstractRangeValidator;

public class LongRangeFieldValidator extends AbstractRangeValidator {
	private Long max;
	private Long min;

	public LongRangeFieldValidator() {
		this.max = null;
		this.min = null;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public Long getMax() {
		return this.max;
	}

	public Comparable getMaxComparatorValue() {
		return this.max;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMin() {
		return this.min;
	}

	public Comparable getMinComparatorValue() {
		return this.min;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.validator.LongRangeFieldValidator JD-Core
 * Version: 0.5.4
 */