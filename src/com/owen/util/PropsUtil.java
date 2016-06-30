package com.owen.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 加载config.properties的文件，读取数据库连接信息
 * @author OwenWilliam 2016-6-30
 * @version 1.0
 *
 */
public class PropsUtil
{

	/**
	 * 加载文件
	 * @param fileName 文件名
	 * @return
	 */
	public static Properties loadProps(String fileName)
	{
		Properties props = null;
		InputStream is = null;
		
		try
		{
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if (is == null)
			{
				throw new FileNotFoundException(fileName + " file is not found");
			}
			
			props = new Properties();
			props.load(is);

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (is != null)
			{
				try
				{
					is.close();
				}
				catch (IOException ex)
				{
					ex.printStackTrace();
				}
			}
		}
		return props;
	}
}
