package com.example.deno_camera;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

public class LFragmentMannager {
	
	private  FragmentTransaction transaction;
	private Context mContext;
	private  FragmentManager manager;
	
	
	public LFragmentMannager(Context context) {
		this.mContext=context;
		manager =((Activity)mContext).getFragmentManager();
		
	}
	
	public  void replaceFragment(int layoutID,Fragment fragment) {
		// TODO Auto-generated method stub
		transaction=manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (transaction!=null) {
			transaction.replace(layoutID, fragment);
			transaction.addToBackStack(null);
			transaction.commitAllowingStateLoss();
		}
	}
	
	
	public  void replaceFragment(int layoutID,Fragment fragment,String TAG) {
		// TODO Auto-generated method stub
		transaction=manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (transaction!=null) {
			transaction.replace(layoutID, fragment,TAG);
			transaction.addToBackStack(TAG);
			transaction.commitAllowingStateLoss();
		}
	}
	
	
	public void addFragement(int layoutID,Fragment fragment){
		transaction=manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (transaction!=null) {
			transaction.add(layoutID, fragment);
			transaction.commitAllowingStateLoss();
		}
	}
	
	
	public void showFragement(Fragment fragment){
		transaction=manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (transaction!=null) {
			transaction.show(fragment);
			transaction.commitAllowingStateLoss();
		}
	}
	
	public void hideFragement(Fragment fragment){
		transaction=manager.beginTransaction();
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		if (transaction!=null) {
			transaction.hide(fragment);
//			transaction.remove(fragment);
			transaction.commitAllowingStateLoss();
		}
	}
	
	public void back(){
//		manager.popBackStack();
		manager.popBackStackImmediate();
	}
	


}
