package com.cpersicum.modules.orm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.type.Type;

public class SqlRestriction implements Serializable {
	public static final Type STRING = Hibernate.STRING;
	public static final Type INTEGER = Hibernate.INTEGER;
	public static final Type LONG = Hibernate.LONG;
	public static final Type DOUBLE = Hibernate.DOUBLE;
	public static final Type DATE = Hibernate.DATE;
	public static final Type BOOLEAN = Hibernate.BOOLEAN;
	private String sql;
	private List values = new ArrayList();
	private List<Type> dataTypes = new ArrayList();

	public String getSql() {
		return this.sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public Object[] getValues() {
		return this.values.toArray();
	}

	public Type[] getDataTypes() {
		return ((Type[]) this.dataTypes
				.toArray(new Type[this.dataTypes.size()]));
	}

	public void addValue(Object value, Type type) {
		this.values.add(value);
		this.dataTypes.add(type);
	}
}

