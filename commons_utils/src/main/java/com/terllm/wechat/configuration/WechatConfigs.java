package com.terllm.wechat.configuration;

import com.alibaba.fastjson.JSONObject;
import com.terllm.utils.HttpClientUtils;
import com.terllm.wechat.entity.WeChatAccessTokenRes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author  terllm 2019-09-04
 * 微信能力开放平台，配置项
 *
 */
public class WechatConfigs {

	private static String access_token = null;

	private static String jsapi_ticket = null;

	
	private static long access_token_time =0;
	
	private static long jsapi_ticket_time =0;
	
	private static final String CORPID =""; //填写企业微信的appid
	
	private static final String CORPSECRET =""; //填写企业微信的corpsecret
	
	/**
	 * 刷新Access Token
	 */
	public static void refreshAccessToken() throws Exception {

		if(access_token_time  !=0 && System.currentTimeMillis() < access_token_time ) return; 
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("corpid", CORPID);
		params.put("corpsecret",CORPSECRET);
		String resJson = HttpClientUtils.doGet(
				"https://qyapi.weixin.qq.com/cgi-bin/gettoken", params);

		
		
		WeChatAccessTokenRes res = JSONObject.toJavaObject(JSONObject.parseObject(resJson), WeChatAccessTokenRes.class);

		access_token = res.getAccess_token();
		
		access_token_time = System.currentTimeMillis()+6900;
		

	}


	
	
	
	
	public static void getJsapiTicket() throws Exception {

		if(jsapi_ticket_time !=0 &&  jsapi_ticket_time > System.currentTimeMillis()) return;
		
		String resJson = HttpClientUtils.doGet(
				"https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="
						+ access_token, null);

		JSONObject jsonRes = JSONObject.parseObject(resJson);
		jsapi_ticket = (String) jsonRes.get("ticket");

		jsapi_ticket_time = System.currentTimeMillis()+6900;
		
	}

	public static String getSignature(String noncestr, String timestamp,
			String url) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {

		String item = "jsapi_ticket="+jsapi_ticket + "&noncestr=" + noncestr + "&timestamp="
				+ timestamp + "&url=" + url;

		MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(item.getBytes("UTF-8"));
		String signature = byteToHex(crypt.digest());

		return signature;

	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	public static String creatNoncestr() {
		return UUID.randomUUID().toString();
		
	}
	
	public static String getTimeStamp() {
		
		return String.valueOf(System.currentTimeMillis());
		
	}
	
	
	
	

}
