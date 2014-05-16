package com.example.deno_camera;

import java.io.IOException;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements Callback {
	
	private Camera mCamera;
	private SurfaceHolder mHolder; 
	private String TAG="Linker";

	
	public  CameraPreview(Context context ,Camera camera) {
		super(context);
		mCamera=camera;
		// ��װһ��SurfaceHolder.Callback��
		mHolder=getHolder();//���ʵ��
//		mHolder.setFixedSize(320,240);//��������÷ֱ��ʣ�������
		mHolder.addCallback(this);
		// �ѹ��ڵ����ã����汾����3.0��Android����Ҫ
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
	}

	/**
	 * ��ͼ�������¼�
	 */
	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// surface�ѱ����������ڰ�Ԥ�������λ��֪ͨ����ͷ
        try { 
        	
        	//����Ԥ��
            mCamera.setPreviewDisplay(mHolder); 
            //��ʼԤ��
            mCamera.startPreview(); 
        
        } catch (IOException e) { 
            Log.d(TAG, "Error setting camera preview: " + e.getMessage()); 
        } 
	}
	
	
	/**
	 * ��ͼ�ı�ʱ���¼�
	 */
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// ���Ԥ���޷���Ļ���ת��ע��˴����¼�
        // ȷ�������Ż�����ʱֹͣԤ��
        if (mHolder.getSurface() == null){ 
            // Ԥ��surface������
            return; 
          } 
          // ���ʱֹͣԤ�� 
          try { 
              mCamera.stopPreview(); 
          } catch (Exception e){ 
            // ���ԣ���ͼֹͣ�����ڵ�Ԥ��
          } 
          // �ڴ˽������š���ת��������֯��ʽ
          // ���µ���������Ԥ��
          try { 
        	  //����Ԥ��
              mCamera.setPreviewDisplay(mHolder); 
              //��ʼԤ��
              mCamera.startPreview(); 
              
          } catch (Exception e){ 
              Log.d(TAG, "Error starting camera preview: " + e.getMessage()); 
          } 
	}

	/**
	 * ��ͼ���ʱ���¼�
	 */
	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.e("Linker", "sufaceDestroyed---------camera release");
		 
	}

}
