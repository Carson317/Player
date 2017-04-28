package com.carson.player.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by heng.cao on 17-4-28.
 */

public class OnlineMusicPager extends BasePager {
    public OnlineMusicPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        TextView textView = new TextView(mContext);
        textView.setText("VideoPager");
        textView.setTextSize(15);
        return textView;
    }

    @Override
    public void initData() {

    }
}
