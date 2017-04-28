package com.carson.player.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by heng.cao on 17-4-28.
 */

public class VideoPager extends BasePager {
    TextView textView;
    public VideoPager(Context context) {
        super(context);
        initView();
    }

    @Override
    public View initView() {
        textView = new TextView(mContext);
        mPagerView = textView;
        return mPagerView;
    }

    @Override
    public void initData() {
        textView.setText("VideoPager");
        textView.setTextSize(15);
    }
}
