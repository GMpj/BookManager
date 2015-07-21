package com.librarymanager.dao;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库写操作，使用commons-dbutils-1.6.jar
 */
public class DbWriter {
	public static int write(String sql,String params[]) {
		// 建立连接
		Connection conn = DbConnect.getConn();
		int flag = 0;
		// 创建sql执行工具
		QueryRunner qr = new QueryRunner();
		// sql插入语句
		try {
			// 执行插入
			flag = qr.update(conn, sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DbUtils.closeQuietly(conn);
		return flag;
	}

	
}
