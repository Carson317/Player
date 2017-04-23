package com.carson.player;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by heng on 2017/4/22.
 */
public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = new TextView(this,null);
        textView.setText("主页面");
        textView.setTextSize(18);

    }
}
