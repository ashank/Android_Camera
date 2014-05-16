package com.example.deno_camera;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
/**
 * 
 * @author Administrator
 *1.������ͷ
 *2.����ʵʱԤ��
 *3.��ʼԤ��
 *4.��ʼ¼����Ƶ
 *4a.����
 *4b.����MediaRecorder 
 */
public class GatherActivity extends Activity implements OnClickListener {
	//����ͷ
	private Camera mCamera;
	//Ԥ����
	private CameraPreview mPreview;
	//¼����
	private MediaRecorder mMediaRecorder;
	
	private boolean isRecording = false;
	private LayoutParams layoutParams;
	private String result=null;
	Button recordButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/*
		 * ȫ����ʾ
		 */
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFormat(PixelFormat.TRANSLUCENT);
		setContentView(R.layout.activity_gather);
		
		//���
	    StrictModeUtils.setStrictMode();
		//������ͷ
		if (Tool.checkCameraHardware(this)) {
			//������ͷ
			mCamera=getCameraInstance();
			//����Ԥ���Ϳ�ʼԤ��
			mPreview=new CameraPreview(this, mCamera);
			
			FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview); 
			layoutParams=new LayoutParams(173, 173);
			mPreview.setLayoutParams(layoutParams);
	    	preview.addView(mPreview);
//	    	mPreview.setAlpha(0);//��Ҫ����ʱ͸����
	    	
	    	recordButton=(Button)findViewById(R.id.button_capture_video);
	    	recordButton.setOnClickListener(this);
		}else {
			//����ͷ������
		}
		
	}
	
	
	/** ��ȫ��ȡCamera����ʵ��ķ���*/ 
	public static Camera getCameraInstance(){ 
	    Camera c = null; 
	    try { 
	        c = Camera.open(0); // ��ͼ��ȡCameraʵ
//	        c.setDisplayOrientation(180);
	        } 
	    catch (Exception e){ 
	        // ����ͷ�����ã���ռ�û򲻴��ڣ�
	    } 
	    return c; // �������򷵻�null
	}

	//¼��
	@Override
	public void onClick(View arg0) {
		
		//����MediaRecord
		if (isRecording) {
			recordButton.setText("录像");
			 // stop recording and release camera
			mMediaRecorder.stop();  // stop the recording
            releaseMediaRecorder(); // release the MediaRecorder object
            mCamera.lock();         // take camera access back from MediaRecorder
            // inform the user that recording has stopped
//            releaseCamera();
            isRecording = false;
            Log.v("Linker", "停止录制...");
		}else {
			recordButton.setText("停止录像");
			  // initialize video camera
            if (PrepareMediaRecord()) {
                // Camera is available and unlocked, MediaRecorder is prepared,
                // now you can start recording
            	 Log.v("Linker", "开始录制...");
                mMediaRecorder.start();
                // inform the user that recording has started
                isRecording = true;
            } else {
                // prepare didn't work, release the camera
                releaseMediaRecorder();
                // inform user
            }
		}
		
    	
	}

	private boolean PrepareMediaRecord() {
		//����
		mCamera.unlock();
		mMediaRecorder=new MediaRecorder();
		//Step 1����¼�Ƶ�����ͷ
		mMediaRecorder.setCamera(mCamera);
		//Step 2������ƵԴ����ƵԴ
		mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
		mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
		// Step 3: Set a CamcorderProfile (requires API Level 8 or higher)
		CamcorderProfile profile=CamcorderProfile.get(0,CamcorderProfile.QUALITY_480P);
		mMediaRecorder.setProfile(profile);
		
		//Step 4:set output file
		mMediaRecorder.setOutputFile(getOutputMediaFile(MEDIA_TYPE_VIDEO).toString());
		 // Step 5: Set the preview output
	    mMediaRecorder.setPreviewDisplay(mPreview.getHolder().getSurface());
	    
	    //Step 6: Prepare configured MediaRecorder
	    try {
	        mMediaRecorder.prepare();
	    } catch (IllegalStateException e) {
	    	e.printStackTrace();
	        Log.d("Linker", "IllegalStateException preparing MediaRecorder: " + e);
	        releaseMediaRecorder();
	        return false;
	    } catch (IOException e) {
	        Log.d("Linker", "IOException preparing MediaRecorder: " + e.getMessage());
	        releaseMediaRecorder();
	        return false;
	    }
	    
	    return true;
	}
	
	
	public static final int MEDIA_TYPE_IMAGE = 1;
	public static final int MEDIA_TYPE_VIDEO = 2;

	/** Create a file Uri for saving an image or video */
	private static Uri getOutputMediaFileUri(int type){
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	/** Create a File for saving an image or video */
	private static File getOutputMediaFile(int type){
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	    File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
	              Environment.DIRECTORY_PICTURES), "MyCameraApp");
	    Log.d("Linker", "·��"+mediaStorageDir.toString());
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("Linker", "failed to create directory");
	            return null;
	        }
	    }

	    // Create a media file name
	    String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ timeStamp + ".jpg");
	    } else if(type == MEDIA_TYPE_VIDEO) {
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "VID_"+ timeStamp + ".mp4");
	    } else {
	        return null;
	    }

	    return mediaFile;
	}
	
	
	
	
	
	private void releaseMediaRecorder(){
        if (mMediaRecorder != null) {
            mMediaRecorder.reset();   // clear recorder configuration
            mMediaRecorder.release(); // release the recorder object
            mMediaRecorder = null;
//            mCamera.lock();           // lock camera for later use
        }
    }
	
	@Override
    protected void onPause() {
        super.onPause();
        Log.v("Linker", "Activity   Onpause");
        releaseMediaRecorder();       // if you are using MediaRecorder, release it first
//        releaseCamera();              // release the camera immediately on pause event
    }
	
	
	private void releaseCamera(){
        if (mCamera != null){
        	mCamera.setPreviewCallback(null);
        	mCamera.stopPreview();
            mCamera.release();        // release the camera for other applications
            mCamera = null;
        }
    }
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.v("Linker", "Activity   Destory");
		releaseMediaRecorder();     
		releaseCamera();
		super.onDestroy();
	}

	
}