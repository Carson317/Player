package com.carson.player;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.carson.player.utils.PermissionHelper;
import com.carson.player.utils.PlayerLogger;

public class SplashActivity extends Activity {

    private PermissionHelper mPermissionHelper;
    private static final int WRITE_RESULT_CODE = 100;
    private static String READ_EXTERNAL_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;

    private boolean startMainActivity = false;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideStatusBar();
        setContentView(R.layout.activity_splash);

        mPermissionHelper = new PermissionHelper(this);
    }

    private void hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void startMainActivity() {
        PlayerLogger.Debug("start activity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onResume() {
        super.onResume();
        //可以才启动过程做一些其他事情
        if(mPermissionHelper.checkPermission(READ_EXTERNAL_PERMISSION)){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                        startMainActivity();
                }
            }, 3000);
        }else {
            mPermissionHelper.permissionsCheck(READ_EXTERNAL_PERMISSION,WRITE_RESULT_CODE);
        }
    }

    @Override
    protected void onDestroy() {
        //移除所有的消息和回调
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PlayerLogger.Debug("requestCode:" + requestCode + "grantResults[0]:"+grantResults[0]);
        switch (requestCode){
            case WRITE_RESULT_CODE:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    startMainActivity();
                }else {
                    finish();
                    mPermissionHelper.startAppSettings();
                }
                break;
        }
    }
}
