package com.owen.test;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.owen.data.SqlHander;
import com.owen.util.PropsUtil;

/**
 * 测试方法
 * @author OwenWilliam 2016-6-30
 * @see
 * @version 1.0
 *
 */
public class ConverterTest
{
	/**
	 * driver 驱动
	 */
	private static final String DRIVER;
	/**
	 * url 地址
	 */
	private static final String URL;
	/**
	 * 用户名
	 */
	private static final String USERNAME;
	/**
	 * 密码
	 */
	private static final String PASSWORD;
	
	/**
	 * 获取config.properties的信息
	 */
	static
	{
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");
	}

	private  static String sql = "";
	
	public static void main(String[] args)
	{
		doMethod("ExternalConverter", "Converter", "202000000005462567");
		doMethod("InternalConverter", "Converter", "6567");
	}
	
	/**
	 * 反射方法调用
	 * @param className  类名包含包名
	 * @param ConverterConverter 类对应的方法名
	 * @param transactionID 需要转换的值ID
	 */
	
	public static void doMethod(String className, String ConverterConverter,String transactionID)
	{
		sql = "select * from class_inf t where t.c_name='" + className +"'";
		//查找类名
		Map<Object, String> c_name = getDatas(sql).get(className);
		//根据类得到包名和类名
		String c_package = c_name.get("c_package");
		try
		{
			//获取class对象
			Class<?> cls = (Class<?>) Class.forName(c_package);
			//反射机制得到可用的类
			 Object instance = cls.newInstance();
			 //调用方法，第一个参数是方法名，第二个是方法包含参数的类型
			 Method method = cls.getMethod(ConverterConverter, Object.class);
			 //反射方法的调用
			System.out.println(method.invoke(instance, transactionID));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 连接数据库，查找数据，得到需要的资源
	 * @param sql 语句
	 * @return  查找的结果
	 */
	public static Map<Object,Map<Object, String>> getDatas(String sql)
	{
		//连接数据库
		  Connection conn = SqlHander.getConn(DRIVER,URL,USERNAME,PASSWORD);
	      Statement stmt = SqlHander.getStatement(conn);
	      ResultSet rs = SqlHander.getResultSet(stmt, sql);
	      Map<Object, String> mapDatas = new HashMap<Object, String>();
	      Map<Object,Map<Object, String>> listDatas = new HashMap<Object,Map<Object, String>>();
	      try
		{
			while (rs.next())
			  {
				//获取需要的字段值
				 String c_name = rs.getString("c_name");
				 String c_package = rs.getString("c_package");
				 //放入map的容器中
				 mapDatas.put("c_package", c_package);
				 listDatas.put(c_name, mapDatas);
			  }
		} 
	    catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			//关闭
			SqlHander.close();
		}
	      
		return listDatas;
	}
	
	

}
