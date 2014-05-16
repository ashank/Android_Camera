package com.example.deno_camera;

import android.os.StrictMode;

/**
 * 
* @ClassName: StrictModeUtils 
* @Description: TODO(监控程序的类) 
* @author Conan_Zhi 
* @date 2013-6-5 上午10:16:07
 */
public class StrictModeUtils {
	
	private static Boolean DEVICE_MODE=true;
	
	public static void setStrictMode() {
		// TODO Auto-generated method stub
		if (DEVICE_MODE) {
			/*
			 * 类StrictMode可以用于捕捉发生在应用程序主线程中耗时的磁盘、网络访问或函数调用，
			 * 可以帮助开发者使其改进程序，使主线程处理UI和动画在磁盘读写和网络操作时变得更平滑，
			 * 避免主线程被阻塞，导致ANR窗口的发生。
			 * 放在Application中程序已启动就应该监控一切了
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
