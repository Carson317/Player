package com.carson.player.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by heng.cao on 17-4-28.
 */

public class MusicPager extends BasePager {

    public MusicPager(Context context) {
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
