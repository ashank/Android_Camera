package com.example.deno_camera;

import android.content.Context;
import android.content.pm.PackageManager;

public class Tool {
	
	/** 检查设备是否提供摄像头 */ 

	public static boolean checkCameraHardware(Context context) { 

	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){ 
	        // 摄像头存在 
	        return true; 
	    } else { 

	        // 摄像头不存在 
	        return false; 

	    } 

	}

}
