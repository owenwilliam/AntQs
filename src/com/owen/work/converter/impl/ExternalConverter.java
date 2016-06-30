package com.owen.work.converter.impl;

import com.owen.work.converter.MessageConverter;

/**
 * 系统内部的ID号要转换为第三方的格式：
 *   去掉前面的“202”开关，然后有零的开始也要去掉。剩下的是不以“202”和零开头的值
 * @author OwenWilliam 2016-6-30
 * @version 1.0
 *
 */
public class ExternalConverter implements MessageConverter
{

	/**
	 * 开关是202开始的
	 */
	private final static String PRE_ID = "202";
	
	@Override
	public Object Converter(Object value)
	{
		if (null == value || !(value instanceof String))
		{
			return null;
		}
		return outConverter((String)value);
	}
	
	/**
	 * 数值转换，将开关202去掉和零开头的零也去掉
	 * @param value
	 * @return
	 */
	public String outConverter(String value)
	{
		if (value.startsWith(PRE_ID))
		{
			//先去掉202
			String preValue = value.substring(PRE_ID.length());
			//再去掉以零开头的
			String delZero = preValue.replaceFirst("^0*", "");
			//剩下的是不以202和零开头的值
			return delZero;
		}
		else
		{
			System.out.println("The number starts does not with '202'");
			return value;
		}
		
	}

}
