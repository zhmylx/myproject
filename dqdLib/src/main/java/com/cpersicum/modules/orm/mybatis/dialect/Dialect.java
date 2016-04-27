package com.cpersicum.modules.orm.mybatis.dialect;

public abstract class Dialect {
	public abstract String getLimitString(String paramString, int paramInt1,
			int paramInt2);

	public static enum Type {
		MYSQL, ORACLE;
	}
}
