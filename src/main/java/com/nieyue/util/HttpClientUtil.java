package com.nieyue.util;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import com.nieyue.weixin.ssl.ClientCustomSSL;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * httpclient工具
 * @author yy
 *
 */
public class HttpClientUtil {
	

	  public static void main(String arg[]) throws Exception {
	    String url = "http://www.baidu.com";
	    JSONObject params = new JSONObject();
	    params.put("SRC_STM_CODE", "wsf");
	    params.put("TENANT_ID", "123");
	    params.put("NM", "张三");
	    params.put("BRTH_DT", "1983-01-20");
	    params.put("GND_CODE", "1");
	    JSONArray params2 = new JSONArray();
	    JSONObject param3 = new JSONObject();
	    param3.put("DOC_TP_CODE", "10100");
	    param3.put("DOC_NBR", "100200198301202210");
	    param3.put("DOC_CUST_NM", "test");
	    params2.add(param3);
	    params.put("DOCS", params2);
	   // String ret = doPostString(url, "sdf=sdf").toString();
	    
	    //String ret = doPostXml(url, "<xml></xml>").toString();
	    //String ret = doGet("http://3g.k.sohu.com/t/n152233000");
	    Connection ret = Jsoup.connect("http://3g.k.sohu.com/t/n152233000");
	    System.out.println(ret.get().select(".scroll-wrap"));
	  }

	  /**
	   httpClient的get请求方式2
	   * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	   * @throws Exception
	   */
	  public static String doGet(String url) throws Exception {
		//创建默认的httpClient实例
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	            //用get方法发送http请求
	            HttpGet get = new HttpGet(url);
	            CloseableHttpResponse httpResponse = null;
	            //发送get请求
	            httpResponse = httpClient.execute(get);
	                //response实体
	                HttpEntity entity = httpResponse.getEntity();
	                String content="";
	                if (null != entity){
	                	content=EntityUtils.toString(entity,"UTF-8");
	                    return content;
	                }
	               
	                return content;
	  }

	  /**
	   * post请求json
	   * @param url
	   * @param json
	   * @return
	   */
	  
	  public static String doPostJson(String url,String json){
		  CloseableHttpClient  client = HttpClients.createDefault();
		    HttpPost post = new HttpPost(url);
		    String result="";
		    try {
		     StringEntity s = new StringEntity(json,"utf-8");
		   //创建参数列表
		     post.setEntity(s);
		      CloseableHttpResponse res = client.execute(post);
		      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
		       System.out.println(res.getEntity().getContentType());
		       System.out.println(res.getEntity().getContentEncoding());
		       result = EntityUtils.toString(res.getEntity(),"UTF-8");// 返回json格式：
		       // Map<String, Object> map = MyDom4jUtil.xmlStrToMap(result);
		       // response=JSONObject.fromObject(map);
		        return result;
		      }
		    } catch (Exception e) {
		      throw new RuntimeException(e);
		    }
		    return result;
	  }
	  /**
	   * 带证书的post请求json
	   * @param url
	   * @param json
	   * @return
	 * @throws Exception 
	   */
	  
	  public static JSONObject doSSLPostJson(String url,String json) throws Exception{
		  CloseableHttpClient client = ClientCustomSSL.getCloseableHttpClient();
	    HttpPost post = new HttpPost(url);
	    JSONObject response = null;
	    try {
	      StringEntity s = new StringEntity(json.toString(),"utf-8");
	      post.setEntity(s);
	      CloseableHttpResponse res = client.execute(post);
	      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        String result = EntityUtils.toString(res.getEntity(),"utf-8");// 返回json格式：
	        response = JSONObject.fromObject(result);
	      }
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	    return response;
	  }
	  /**
	   * 带证书的post请求json
	   * @param url
	   * @param xml
	   * @return
	 * @throws Exception 
	   */
	  
	  public static JSONObject doSSLXmlPostJson(String url,String xml) throws Exception{
		  CloseableHttpClient client = ClientCustomSSL.getCloseableHttpClient();
	    HttpPost post = new HttpPost(url);
	    JSONObject response = null;
	    try {
	      StringEntity s = new StringEntity(xml);
	      post.setEntity(s);
	      CloseableHttpResponse res = client.execute(post);
	      if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
	        String result = EntityUtils.toString(res.getEntity() ,"utf-8");// 返回json格式：
	        Map<String, Object> map = MyDom4jUtil.xmlStrToMap(result);
	        response=JSONObject.fromObject(map);
	        return response;
	      }
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	    return response;
	  }
	  /**
	   * post请求 String
	   * @param url
	   * @param string
	   * @return
	   */
	  
	  public static String doPostString(String url,String string){
		  CloseableHttpClient  client = HttpClients.createDefault();
		  HttpPost post = new HttpPost(url);
		  String response = null;
		  try {
			  StringEntity s = new StringEntity(string,"utf-8");
			  post.setEntity(s);
			  CloseableHttpResponse res = client.execute(post);
			  if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				  response = EntityUtils.toString(res.getEntity(),"utf-8");// 返回json格式：
				  //response = JSONObject.fromObject(result);
			  }
		  } catch (Exception e) {
			  throw new RuntimeException(e);
		  }
		  return response;
	  }
	  /**
	   * 带证书的post 获取xml
	   * @param url
	   * @param xml
	   * @return
	   * @throws Exception
	   */
	  public static String doSSLPostXml(String url,String xml) throws Exception{
		  CloseableHttpClient client = ClientCustomSSL.getCloseableHttpClient();
		  HttpPost post = new HttpPost(url);
		  String response = null;
		  try {
			  StringEntity s = new StringEntity(xml,"utf-8");
			  post.setEntity(s);
			  System.out.println(s);
			  CloseableHttpResponse res = client.execute(post);
			  if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				  response = EntityUtils.toString(res.getEntity(),"utf-8");// 返回xml格式：
				  //response = JSONObject.fromObject(result);
			  }
		  } catch (Exception e) {
			  throw new RuntimeException(e);
		  }
		  return response;
	  }
	  /**
	   * 不带证书的post 获取xml
	   * @param url
	   * @param xml
	   * @return
	   */
	  public static String doPostXml(String url,String xml){
		  CloseableHttpClient  client = HttpClients.createDefault();
		  HttpPost post = new HttpPost(url);
		  String response = null;
		  try {
			  StringEntity s = new StringEntity(xml,"utf-8");
			  post.setEntity(s);
			  System.out.println(s);
			  CloseableHttpResponse res = client.execute(post);
			  System.out.println(res);
			  if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				  response = EntityUtils.toString(res.getEntity(),"utf-8");// 返回xml格式：
				  //response = JSONObject.fromObject(result);
			  }
		  } catch (Exception e) {
			  throw new RuntimeException(e);
		  }
		  return response;
	  }
	  
	
	
}
