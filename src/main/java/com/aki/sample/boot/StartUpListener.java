package com.aki.sample.boot;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.google.common.base.Charsets;
import com.google.common.io.Files;


/**
 * 
 * <pre>
 * </pre>
 * @createAt 2018-09-01 02:28:33
 * @author vikingblackey vikingblackey@gmail.com
 */
@Component
public class StartUpListener implements ApplicationRunner {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DataSource datasource;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		init();
	}
	
	public Connection getConnetion() throws SQLException {
		Connection conn = datasource.getConnection();
		conn.setAutoCommit(false);
		return conn;
	}
	
	
	private void init() throws IOException, SQLException {
		Connection conn = this.getConnetion();
		Statement stat = conn.createStatement();
		
		File dir = ResourceUtils.getFile("classpath:sql");
		logger.info(dir.getAbsolutePath());
		if(dir.isDirectory()) {
			for(File f : dir.listFiles()) {
				String tableName = f.getName().replace(".sql", "");
				ResultSet rs = stat.executeQuery("select count(0) as num from sqlite_master where type = 'table' and name = '"+ tableName +"';");
				int count = rs.getInt("num");
				if(count==0) {
					String sql = Files.asCharSource(f, Charsets.UTF_8).read();
					logger.info("File : {} -> {}", f.getName(), sql);
					stat.executeUpdate(sql);
				}else {
					rs = stat.executeQuery("select sql from sqlite_master where type = 'table' and name = '"+tableName+"';");
					while (rs.next()) {
						logger.info("Table : {} -> {}",tableName, rs.getString("sql"));
					}
				}
				rs.close();
			}
			conn.commit();
			conn.close();
		}
		
	}

}
