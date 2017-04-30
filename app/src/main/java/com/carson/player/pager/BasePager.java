package com.carson.player.pager;

import android.content.Context;
import android.view.View;

/**
 * Created by heng.cao on 17-4-28.
 */

public abstract class BasePager {
    public Context mContext;
    public View mPagerView;
    public boolean mIsInitData = false;

    public BasePager(Context context) {
        this.mContext = context;
    }

    public abstract View initView();

    public abstract void initData();
}
