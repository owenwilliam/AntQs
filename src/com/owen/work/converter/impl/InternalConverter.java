package com.owen.work.converter.impl;

import com.owen.work.converter.MessageConverter;

/**
 * 当外部的ID进入我们系统时，我们需要将值进行转换：
 *     数值以“202”开始，总的18位，不足的在“202”和数值之间加“0”，凑成18位
 * @author OwenWilliam 2016-6-30
 * @version 1.0
 *
 */
public class InternalConverter implements MessageConverter
{
   /**
    * 扣除202还有15位
    */
	private final static int TRANSACTION_ID_LENTH = 15;
	/**
	 * 默认15位为“0”
	 */
	private final static String TRANSACTION_ZERO = "000000000000000";
	/**
	 * 以202开头
	 */
	private final static String PRE_ID = "202";
	
	@Override
	public Object Converter(Object value)
	{
		if (null == value || !(value instanceof String))
		{
			return null;
		}
		
		return IntConverter((String)value);
	}

	/**
	 * 将第三方系统的ID转换为“202”开关的数，总的18位
	 * @param value
	 * @return
	 */
	public String IntConverter(String value)
	{
		//如果值大于15位，我们不做处理，返回null,记下日志.
		if (value.length() > TRANSACTION_ID_LENTH)
		{
			System.out.println("The number is illegal!");
			return null;
		}
		
		//第三方有多位“0”，那么15的“0”就要截取掉多个“0”
		String addZero = TRANSACTION_ZERO.substring(0,TRANSACTION_ZERO.length()-value.length());
		
		//加止“202”和剩下的“0”，及第三方的ID返回
		return PRE_ID + addZero + value;
	}
	
	
}
