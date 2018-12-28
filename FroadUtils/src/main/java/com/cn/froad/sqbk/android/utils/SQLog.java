package com.cn.froad.sqbk.android.utils;

import android.util.Log;

public class SQLog {
    
    public static final String LOG_WARNING = "waring";

	private static boolean isDebug = false;
    
    public static boolean isDebug(){
        return isDebug;
    }
    
    public static void setDebug(boolean debug){
		isDebug = debug;
	}
	
	public static void d(String tag,String msg) {
		if(isDebug){
			Log.d(tag, msg);
		}
	}
	public static void e(String tag,String msg) {
		if(isDebug){
			Log.e(tag, msg);
		}
	}
	public static void w(String tag,String msg) {
		if(isDebug){
			Log.w(tag, msg);
		}
	}
	public static void i(String tag,String msg) {
		if(isDebug){
			Log.i(tag, msg);
		}
	}
	public static void v(String tag,String msg) {
		if(isDebug){
			Log.v(tag, msg);
		}
	}
}
