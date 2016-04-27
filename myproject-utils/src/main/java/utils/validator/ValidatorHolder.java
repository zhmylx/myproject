package utils.validator;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;

import utils.AssertUtils;

public class ValidatorHolder implements DisposableBean {
	private static Validator validator;

	@Autowired
	public void setValidator(Validator validator) {
		validator = validator;
	}

	public static Validator getValidator() {
		assertValidatorInjected();
		return validator;
	}

	public static <T> Set<ConstraintViolation<T>> validate(T object,
			Class<?>[] groups) {
		assertValidatorInjected();
		return getValidator().validate(object, groups);
	}

	public static void validateWithException(Object object, Class<?>[] groups)
			throws ConstraintViolationException {
		Set constraintViolations = validate(object, groups);
		if (!(constraintViolations.isEmpty()))
			throw new ConstraintViolationException(constraintViolations);
	}

	public static String convertMessage(
			Set<? extends ConstraintViolation> constraintViolations,
			String separator) {
		List errorMessages = Lists.newArrayList();
		for (ConstraintViolation violation : constraintViolations) {
			errorMessages.add(violation.getMessage());
		}
		return StringUtils.join(errorMessages, separator);
	}

	public static String convertMessage(ConstraintViolationException e,
			String separator) {
		return convertMessage(e.getConstraintViolations(), separator);
	}

	public static void clearHolder() {
		validator = null;
	}

	public void destroy() throws Exception {
		clearHolder();
	}

	private static void assertValidatorInjected() {
		AssertUtils.state(validator != null, "Validator属性未注入.");
	}

	public static void main(String[] rags) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		ValidatorHolder holder = new ValidatorHolder();
		holder.setValidator(factory.getValidator());
	}
}

