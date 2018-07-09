package base.dao.mybatis;

import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;

import base.dao.mybatis.dialect.Dialect;
import base.dao.mybatis.dialect.MySql5Dialect;
import base.dao.mybatis.dialect.OracleDialect;
import base.entity.QueryCondition;


/**
 * 拦截 mybatis 实现物理分页
 * 
 * 需要在 configuratiom.xml 中配置
 * <configuration>
	<properties>
		<property name="dialect" value="Oracle"/>
	</properties>
	<plugins>
		<plugin interceptor="com.cpersicum.modules.orm.mybatis.PaginationInterceptor">
		</plugin>
	</plugins>
</configuration>

 * @author Homing tsang
 *
 */
@Intercepts({ @org.apache.ibatis.plugin.Signature(type = StatementHandler.class, method = "prepare", args = { java.sql.Connection.class }) })
public class PaginationInterceptor implements Interceptor {
	private static final Log log = LogFactory
			.getLog(PaginationInterceptor.class);

	public Object intercept(Invocation invocation) throws Throwable {
		//得到参数
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		ParameterHandler parameterHandler = statementHandler
				.getParameterHandler();
		Object parameter = parameterHandler.getParameterObject();

		if (parameter instanceof QueryCondition) {//判断参数是否为查询
            //得到分页信息
			BoundSql boundSql = statementHandler.getBoundSql();
			MetaObject metaStatementHandler = MetaObject
					.forObject(statementHandler);
			RowBounds rowBounds = (RowBounds) metaStatementHandler
					.getValue("delegate.rowBounds");
			if ((rowBounds == null) || (rowBounds == RowBounds.DEFAULT)) {
				return invocation.proceed();
			}
			Configuration configuration = (Configuration) metaStatementHandler
					.getValue("delegate.configuration");
			Dialect.Type databaseType = null;
			try {
				databaseType = Dialect.Type.valueOf(configuration
						.getVariables().getProperty("dialect").toUpperCase());
			} catch (Exception localException) {
			}
			if (databaseType == null) {
				throw new RuntimeException(
						"the value of the dialect property in configuration.xml is not defined : "
								+ configuration.getVariables().getProperty(
										"dialect"));
			}
			Dialect dialect = null;
			switch (databaseType.ordinal()) {
			case 1:
				dialect = new MySql5Dialect();
			case 2:
				dialect = new OracleDialect();
			}

			String originalSql = (String) metaStatementHandler
					.getValue("delegate.boundSql.sql");
			metaStatementHandler.setValue("delegate.boundSql.sql", dialect
					.getLimitString(originalSql, rowBounds.getOffset(),
							rowBounds.getLimit()));
			metaStatementHandler.setValue("delegate.rowBounds.offset",
					Integer.valueOf(0));
			metaStatementHandler.setValue("delegate.rowBounds.limit",
					Integer.valueOf(2147483647));
			if (log.isDebugEnabled()) {
				log.debug("分页查询〉SQL : " + boundSql.getSql());
			}
		}
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
