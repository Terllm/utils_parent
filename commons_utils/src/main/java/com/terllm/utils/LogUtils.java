package com.terllm.utils;


/**
 *
 * @author terllm 2019-08-23
 * 日志工具
 *
 */
public class LogUtils {
	
	/**
	 * <p>日志格式</p>
	 * @param e 异常
	 * @return String 日志信息
	 */
	public static String logFormat(Exception e){
		if(e == null){
			return "";
		}
		StackTraceElement[] exTrace = e.getStackTrace();
		
		String errCode = "";

		
		StringBuffer buf = new StringBuffer();
		buf.append(e.getClass().getName())
			.append(":")
			.append(errCode)
			.append(e.getMessage())
			.append("\r\n");
		if (exTrace != null) {
			for (int i = 0; i < exTrace.length; i++) {
				buf.append(exTrace[i].toString())
					.append("\r\n");
			}
		}
		return buf.toString();
	}
}
