package com.carson.player.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carson.player.R;
import com.carson.player.utils.PlayerLogger;

/**
 * Created by heng.cao on 17-4-30.
 */

public class TitleBar extends LinearLayout implements View.OnClickListener {
    private View mTvSearch;
    private View mIvMore;
    private Context mContext;

    /**
     * 代码中实例化TitleBar
     * @param context
     */
    public TitleBar(Context context) {
        this(context,null);
    }

    /**
     * 当在布局中使用该类的时候，通过这个方法实例化
     * @param context
     * @param attrs
     */
    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);

    }

    /**
     * 需要设置样式的时候实例化
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    /**
     * 加载完成布局后回调，可在此获取布局当中的控件
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTvSearch = getChildAt(2);
        mIvMore = getChildAt(3);
        mTvSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_search:
                PlayerLogger.Debug("Enter Search Activity");
                //进入搜索界面
                break;
            case R.id.iv_more:
                PlayerLogger.Debug("Show more menu");
                //更多操作
                break;
        }
    }

    /**
     * 重写onDraw 对原生控件进行扩展，重写View实现全新的控件，通过重写onDraw()，onMeasure()实现绘制逻辑，重写onTouchEvent()实现交互逻辑
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
