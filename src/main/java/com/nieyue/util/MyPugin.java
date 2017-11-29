package com.nieyue.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class MyPugin {
	@Value("${myPugin.location}")
	String location;
	
	 public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * 读取 properties
	 * @param key
	 * @return
	 */
	public  String GetValueByKey(String key){
         Properties prop = new Properties(); 
 		InputStream is;
 		try {
 			is = new FileInputStream(location);
 				prop.load(is);
 				is.close();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			return null;
 		}  
 		String value = (String) prop.get(key);
 		return value;
	}
	/**
	 * 写入properties
	 * @param key
	 * @return
	 * @throws IOException 
	 */
	public  String SetValueByKey(String key,String value) throws IOException{
		Properties prop = new Properties();  
		InputStream is=new FileInputStream(location) ;
		OutputStream fos;
		String  s="";
		try {
			 prop.load(is);
			 is.close();
			 s = (String) prop.setProperty(key, value);
	         fos = new FileOutputStream(location); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}  
          prop.store(fos, "Update '" + key + "' value");
		 fos.close();
		return s;
	}
	public static void main(String[] args) {
		try {
			new MyPugin().SetValueByKey("phone", "1aaa");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(new MyPugin().GetValueByKey("phone"));
	}
}
