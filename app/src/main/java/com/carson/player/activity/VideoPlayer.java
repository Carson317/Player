package com.carson.player.activity;

import android.app.Activity;
import android.os.Bundle;

import com.carson.player.R;

/**
 * Created by heng.cao on 17-5-2.
 */
public class VideoPlayer extends Activity {

    public VideoPlayer() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_player);
    }
}
