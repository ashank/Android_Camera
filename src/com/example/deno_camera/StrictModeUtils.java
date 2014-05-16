package com.example.deno_camera;

import android.os.StrictMode;

/**
 * 
* @ClassName: StrictModeUtils 
* @Description: TODO(��س������) 
* @author Conan_Zhi 
* @date 2013-6-5 ����10:16:07
 */
public class StrictModeUtils {
	
	private static Boolean DEVICE_MODE=true;
	
	public static void setStrictMode() {
		// TODO Auto-generated method stub
		if (DEVICE_MODE) {
			/*
			 * ��StrictMode�������ڲ�׽������Ӧ�ó������߳��к�ʱ�Ĵ��̡�������ʻ������ã�
			 * ���԰���������ʹ��Ľ�����ʹ���̴߳���UI�Ͷ����ڴ��̶�д���������ʱ��ø�ƽ����
			 * �������̱߳�����������ANR���ڵķ�����
			 * ����Application�г�����������Ӧ�ü��һ����
			 */
			StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()        
	        .detectDiskReads()        
	        .detectDiskWrites()        
	        .detectNetwork()   // or .detectAll() for all detectable problems       
	        .penaltyLog()        
	        .build());  
			
	         StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()        
	        .detectLeakedSqlLiteObjects()     
	        .penaltyLog()        
	        .penaltyDeath()        
	        .build());     
		}

	}

}
