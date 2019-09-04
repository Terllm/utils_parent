package com.terllm.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author  terllm 2019-09-04
 * apache httpclientl 连接池工具
 *
 */
public class HttpClientUtils {

	private static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

	private static String DEFAULT_CHARSET = "UTF-8";
	/**
	 * 最大连接数
	 */
	private static int CONNTIONPOOL_MAX_TOTAL = 1000;
	/**
	 * 路由最大连接数
	 */
	private static int CONNTIONPOOL_DEFAULT_MAX_PER_ROUTE = 1000;
	/**
	 * Socket连接超时时间(ms)
	 */
	private static int SOCKET_TIMEOUT = 300000;
	/**
	 * connect 超时时间(ms)
	 */
	private static int CONNECT_TIMEOUT = 300000;

	private static CloseableHttpClient httpClient;

	private synchronized static CloseableHttpClient getHttpClient() {
//		if (httpClient != null) {
//			return httpClient;
//		}

		try {

			PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
			cm.setMaxTotal(CONNTIONPOOL_MAX_TOTAL);
			cm.setDefaultMaxPerRoute(CONNTIONPOOL_DEFAULT_MAX_PER_ROUTE);

			RequestConfig defaultRequestConfig;
			
			String useProxy = "false";
			if (!StringUtils.isBlank(useProxy) && "true".equalsIgnoreCase(useProxy)) {
				System.out.println("HttpClient.getHttpClient:[Start to setting Proxy!]");
				log.debug("HttpClient.getHttpClient:[Start to setting Proxy!]");
				defaultRequestConfig = RequestConfig.custom()
						.setSocketTimeout(SOCKET_TIMEOUT)
						.setConnectTimeout(CONNECT_TIMEOUT)
						.setProxy(new HttpHost("",
										0)).build();
				
				
			}else{
				defaultRequestConfig = RequestConfig.custom()
						.setSocketTimeout(SOCKET_TIMEOUT)
						.setConnectTimeout(CONNECT_TIMEOUT).build();
				System.out.println("HttpClient.getHttpClient:[socketTimeout]"
						+ SOCKET_TIMEOUT + ",[connectTimeout]"
						+ CONNECT_TIMEOUT);
				log.debug("HttpClient.getHttpClient:[socketTimeout]"
						+ SOCKET_TIMEOUT + ",[connectTimeout]"
						+ CONNECT_TIMEOUT);
			}

			httpClient = HttpClients.custom().setConnectionManager(cm)
					.setDefaultRequestConfig(defaultRequestConfig).build();
			return httpClient;
		} catch (Exception e) {
			log.error(LogUtils.logFormat(e));
			return null;
		}
	}

	public static String doPost(String url, String content)
			throws Exception {
		return doPost(url, content, null);
	}

	public static String doPost(String url, String content, String contentType)
			throws Exception {
		String result = null;

		HttpEntity resEntity = null;
		CloseableHttpResponse response = null;
		try {
			HttpPost post = new HttpPost(url);
			if (contentType != null) {
				post.setHeader("Content-Type", contentType);
			}
			post.setEntity(new StringEntity(content, DEFAULT_CHARSET));

			CloseableHttpClient client = getHttpClient();
			if (client == null || post == null) {
				log.info("client is null");
				return null;
			}
			response = client.execute(post);

			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new Exception("请求失败！ STATUS CODE:"
						+ response.getStatusLine().getStatusCode());
			}

			resEntity = response.getEntity();
			result = null == resEntity ? "" : EntityUtils.toString(resEntity,
					DEFAULT_CHARSET);
		} catch (IOException e) {
			throw new Exception(e);
		} finally {
			try {
				if (resEntity != null) {
					EntityUtils.consume(resEntity);
				}
				if (response != null) {
					response.close();
				}

			} catch (IOException e) {
				throw new IOException(e);
			}

		}
		return result;
	}

	public static String doPostForAuth(String url, String content)
			throws Exception {
		String result = null;

		HttpEntity resEntity = null;
		CloseableHttpResponse response = null;
		try {

			HttpPost post = new HttpPost(url);
			post.setEntity(new StringEntity(content, DEFAULT_CHARSET));

			CloseableHttpClient client = getHttpClient();
			if (client == null || post == null) {
				log.info("client is null");
				return null;
			}

			HttpClientContext context = HttpClientContext.create();
			CredentialsProvider credsProvider = new BasicCredentialsProvider();
			credsProvider.setCredentials(AuthScope.ANY,
					new UsernamePasswordCredentials(
							"",
							""));
			context.setCredentialsProvider(credsProvider);
			response = client.execute(post, context);

			response = client.execute(post, context);
			if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
				throw new Exception("请求失败！ STATUS CODE:"
						+ response.getStatusLine().getStatusCode());
			}

			resEntity = response.getEntity();
			result = null == resEntity ? "" : EntityUtils.toString(resEntity,
					DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new Exception(e);
		} catch (ClientProtocolException e) {
			throw new ClientProtocolException(e);
		} catch (IOException e) {
			throw new IOException(e);
		} finally {
			try {
				if (resEntity != null) {
					EntityUtils.consume(resEntity);
				}
				if (response != null) {
					response.close();
				}

			} catch (IOException e) {
				throw new IOException(e);
			}

		}
		return result;
	}

	public static String doGet(String url, Map<String, String> params)
			throws Exception {
		HttpEntity entity = null;
		CloseableHttpResponse res = null;
		try {
			String paramStr = genGetParams(params);
			String reqUrl = url + (url.contains("?") ? "&" : "?") + paramStr;
			HttpGet hget = new HttpGet(reqUrl);
			res = getHttpClient().execute(hget);
			entity = res.getEntity();
			return entity == null ? "" : EntityUtils.toString(entity,
					DEFAULT_CHARSET);
		} catch (ClientProtocolException e) {
			throw new Exception(e);
		} catch (IOException e) {
			throw new IOException(e);
		}finally{
			try {
				if (entity != null) {
					EntityUtils.consume(entity);
				}
				if (res != null) {
					res.close();
				}

			} catch (IOException e) {
				throw new IOException(e);
			}
		}
	}

	public static String genGetParams(Map<String, String> params) {
		if (params == null || params.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer();
		Iterator<String> keyIter = params.keySet().iterator();
		while (keyIter.hasNext()) {
			String key = keyIter.next();
			String value = params.get(key);
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(key).append("=").append(value);
		}

		return sb.toString();
	}

}
