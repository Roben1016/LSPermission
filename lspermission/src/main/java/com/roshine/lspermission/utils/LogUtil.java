package com.roshine.lspermission.utils;

import android.util.Log;

/**
 *
 * @author Roshine
 * @desc 日志处理工具类
 *
 */
public class LogUtil {

	public static boolean open = true;
	public static void show(String s){
		if (open) {
			Log.e("LSPermission", s);
		}
	}
	
	public static void show(String TAG,String content){
		if (open) {
			Log.e(TAG, content);
		}
	}
	
	public static void showI(String TAG,String content){
		if (open) {
			Log.i(TAG, content);
		}
	}
	
	public static void showW(String TAG,String content){
		if (open) {
			Log.w(TAG, content);
		}
	}
	
	public static void showD(String TAG,String content){
		if (open) {
			Log.d(TAG, content);
		}
	}
}
