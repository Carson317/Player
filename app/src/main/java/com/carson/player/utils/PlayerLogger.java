package com.carson.player.utils;

import android.util.Log;

/**
 * Created by heng.cao on 17-3-1.
 */

public class PlayerLogger {
    public static boolean DEBUG = true;
    private static String TAG = "PlayerLogger";

    public PlayerLogger(){
    }

    public static void Info(String strMsg) {

    }

    public static void Warning(String strMsg) {
        if(DEBUG){
            Log.w(TAG, getFunctionName() + strMsg);
        }
    }

    public static void Debug(String strMsg) {
        if(DEBUG){
            Log.d(TAG, getFunctionName() + strMsg);
        }
    }

    public static void Verbose(String strMsg) {
        if(DEBUG){

        }
    }

    public static void Error(String strMsg) {
        if(DEBUG){
            Log.e(TAG, Log.getStackTraceString(new Exception()).toString() + strMsg);
        }
    }

    public static void Error(Exception e) {
        if(DEBUG){
            Log.e(TAG, Log.getStackTraceString(new Exception()).toString() + e.toString() + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }

    private static String getFunctionName() {
        StackTraceElement[] var0 = Thread.currentThread().getStackTrace();
        return var0[4].getClassName() + ":" + var0[4].getMethodName() + ":";
    }
}
