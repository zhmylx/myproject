package ins.framework.validator;

import java.math.BigDecimal;
import java.util.Map;
import ognl.DefaultTypeConverter;

public class BigDecimalTypeConverter extends DefaultTypeConverter {
	public Object convertValue(Map context, Object value, Class toType) {
		if (toType == BigDecimal.class) {
			String decimal = ((String[]) (String[]) value)[0];
			if ("".equals(decimal)) {
				return null;
			}
			return new BigDecimal(decimal);
		}
		if (toType == String.class) {
			if (value == null) {
				return "";
			}
			return ((BigDecimal) value).toString();
		}
		return null;
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.validator.BigDecimalTypeConverter JD-Core
 * Version: 0.5.4
 */