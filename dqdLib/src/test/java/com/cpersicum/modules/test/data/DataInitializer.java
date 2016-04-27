package com.cpersicum.modules.test.data;

import javax.sql.DataSource;
import org.springframework.beans.factory.InitializingBean;

public class DataInitializer implements InitializingBean {
	private DataSource dataSource;
	private String dataFile;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public void setDataFile(String dataFile) {
		this.dataFile = dataFile;
	}

	public void afterPropertiesSet() throws Exception {
		DataFixtures
				.reloadData(this.dataSource, new String[] { this.dataFile });
	}
}

