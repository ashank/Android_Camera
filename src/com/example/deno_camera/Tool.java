package com.example.deno_camera;

import android.content.Context;
import android.content.pm.PackageManager;

public class Tool {
	
	/** ����豸�Ƿ��ṩ����ͷ */ 

	public static boolean checkCameraHardware(Context context) { 

	    if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){ 
	        // ����ͷ���� 
	        return true; 
	    } else { 

	        // ����ͷ������ 
	        return false; 

	    } 

	}

}
