package com.guoxiaozhuang4.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @author guoxiaozhuang4
 * @explain	��������������
 */
public class publicParameterUtil {

	/**
	 * @explain  ���������ļ���
	 */
	public static final String TARGET_DIR = "F:\\ProjectData\\PropertiesDir\\";
	
	/*
	 * @explain  ���������ļ�
	 */
	public static final String PUBLICPARAMETER_FILE = "PublicParameter.properties";
	
	/**
	 * @explain	 �����µĲ����ļ�
	 * @param name  ���ļ���
	 * @return  �Ƿ񴴽��ɹ� 
	 */
	public static boolean makeParameterFile(String name) {
		
		boolean isMakeSuccess = false;
		
		String fileWholeName = TARGET_DIR + name + ".properties";
		
		File parameterFile = new File(fileWholeName);
		
		try {
			
			File targetDir = new File(TARGET_DIR);
			
			if(!targetDir.exists()) {
				
				targetDir.mkdirs();
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		try {
			
			if(!parameterFile.exists()) {
				
				parameterFile.createNewFile();
				
				isMakeSuccess = true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return isMakeSuccess;
	}
	
	/**
	 * @explain  ��ȡ��������ֵ
	 * @param key  keyֵ
	 * @param path  ���������ļ�
	 * @return	value  ����ֵ
	 */
	public static String getParameterValue(String key, String path) {
		
		String parameterValue = "NO DATA";
		
		Properties parameterProperties = new Properties();
		
		try(
				
				FileInputStream in = new FileInputStream(path);
				) {
			
			parameterProperties.load(in);
			
			parameterValue = parameterProperties.getProperty(key);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return parameterValue;
	}
	
	public static String getParameterValue(String key) {
		
		String path = TARGET_DIR + PUBLICPARAMETER_FILE;
		
		return getParameterValue(key, path);
	}
	
	/**
	 * @explain  ���ù�������ֵ
	 * @param key  keyֵ
	 * @param value  ����ֵ
	 * @param path  ���������ļ�
	 * @return  �Ƿ����óɹ�
	 */
	public static boolean setParameterValue(String key, String value, String path, String notes) {
		
		boolean isSetSuccess = false;
		
		Properties parameterProperties = new Properties();
		
		try(

				FileOutputStream out = new FileOutputStream(path, true);
				) {

			parameterProperties.setProperty(key, value);
			
			parameterProperties.store(out, notes);
			
			isSetSuccess = true;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return isSetSuccess;
	}
	
	public static boolean setParameterValue(String key, String value, String notes) {
		
		String path = TARGET_DIR + PUBLICPARAMETER_FILE;
		
		return setParameterValue(key, value, path, notes);
	}
	
	public static boolean setParameterValue(String key, String value) {
		
		return setParameterValue(key, value, "");
	}
}
