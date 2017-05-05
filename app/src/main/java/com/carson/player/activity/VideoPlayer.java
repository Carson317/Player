package com.carson.player.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.carson.player.R;
import com.carson.player.utils.PlayerLogger;

/**
 * Created by heng.cao on 17-5-2.
 */
public class VideoPlayer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
        PlayerLogger.Debug("Url: " + getIntent().getData());

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){//4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


    }
}
