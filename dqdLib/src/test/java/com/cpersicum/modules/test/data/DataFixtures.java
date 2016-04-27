package com.cpersicum.modules.test.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.lang3.StringUtils;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2Connection;
import org.dbunit.ext.mysql.MySqlConnection;
import org.dbunit.ext.oracle.OracleConnection;
import org.dbunit.operation.DatabaseOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class DataFixtures {
	private static Logger logger = LoggerFactory.getLogger(DataFixtures.class);
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public static void reloadData(DataSource dataSource, String[] xmlFilePaths)
			throws Exception {
		execute(DatabaseOperation.CLEAN_INSERT, dataSource, xmlFilePaths);
	}

	public static void loadData(DataSource dataSource, String[] xmlFilePaths)
			throws Exception {
		execute(DatabaseOperation.INSERT, dataSource, xmlFilePaths);
	}

	public static void deleteData(DataSource dataSource, String[] xmlFilePaths)
			throws Exception {
		execute(DatabaseOperation.DELETE_ALL, dataSource, xmlFilePaths);
	}

	private static void execute(DatabaseOperation operation,
			DataSource dataSource, String[] xmlFilePaths)
			throws DatabaseUnitException, SQLException {
		IDatabaseConnection connection = getConnection(dataSource);
		try {
			for (String xmlPath : xmlFilePaths)
				try {
					InputStream input = resourceLoader.getResource(xmlPath)
							.getInputStream();
					IDataSet dataSet = new FlatXmlDataSetBuilder()
							.setColumnSensing(true).build(input);
					operation.execute(connection, dataSet);
				} catch (IOException e) {
					logger.warn(xmlPath + " file not found", e);
				}
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	protected static IDatabaseConnection getConnection(DataSource dataSource)
			throws DatabaseUnitException, SQLException {
		Connection connection = dataSource.getConnection();
		String dbName = connection.getMetaData().getURL();
		if (StringUtils.contains(dbName, ":h2:"))
			return new H2Connection(connection, null);
		if (StringUtils.contains(dbName, ":mysql:"))
			return new MySqlConnection(connection, null);
		if (StringUtils.contains(dbName, ":oracle:")) {
			return new OracleConnection(connection, null);
		}
		return new DatabaseConnection(connection);
	}
}

/*
 * Location: E:\BaiduYunDownload\cpersicum-core-4.0.0.RC3.jar Qualified Name:
 * com.cpersicum.modules.test.data.DataFixtures JD-Core Version: 0.5.3
 */