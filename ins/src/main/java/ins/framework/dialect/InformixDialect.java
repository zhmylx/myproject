package ins.framework.dialect;

import org.hibernate.dialect.function.NoArgSQLFunction;
import org.hibernate.type.StandardBasicTypes;

public class InformixDialect extends org.hibernate.dialect.InformixDialect {
	public InformixDialect() {
		registerFunction("current_date", new NoArgSQLFunction("current",
				StandardBasicTypes.DATE, false));

		registerFunction("current_time", new NoArgSQLFunction("current",
				StandardBasicTypes.TIME, false));

		registerFunction("current_timestamp", new NoArgSQLFunction("current",
				StandardBasicTypes.TIMESTAMP, false));
	}
}

/*
 * Location: C:\Users\zhm\Desktop\ins-arch5-5.0.9-20130605.065114-1.jar
 * Qualified Name: ins.framework.dialect.InformixDialect JD-Core Version: 0.5.4
 */