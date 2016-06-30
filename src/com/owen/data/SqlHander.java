package com.owen.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 连接数据库
 * @author OwenWilliam  2016-6-30
 * @version 1.0
 *
 */
public class SqlHander
{
	private static Connection conn = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	
	/**
	 * Connect to the DataBase
	 * @param driver 驱动
	 * @param url 地址
	 * @param username 用户名
	 * @param password  密码
	 * @return
	 */
    public static Connection getConn(String driver, String url, String username , String password)
    {
    	
    	try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} 
    	catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
    	catch (SQLException ex)
    	{
    		ex.printStackTrace();
    	}
		return conn;
    }
    
    /**
     * 操作数据
     * @param conn
     * @return
     */
    public static Statement getStatement(Connection conn)
    {
    	try
		{
			stmt = conn.createStatement();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    	return stmt;
    }
    
    /**
     * sql执行数据库
     * @param stmt
     * @param sql 语言
     * @return
     */
    public static ResultSet getResultSet(Statement stmt, String sql)
    {
    	try
		{
			rs = stmt.executeQuery(sql);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    	
    	return rs;
    }
    
    /**
     * 关闭
     */
    public static void close()
    {
    	if (rs != null)
    	{
    		try
			{
				rs.close();
				rs = null;
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
    	}
    	
    	if (stmt != null)
    	{
    		try
			{
				stmt.close();
				stmt = null;
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
    	}
    	
    	if (conn != null)
    	{
    		try
			{
				conn.close();
				conn = null;
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
    		
    	}
    }
}
