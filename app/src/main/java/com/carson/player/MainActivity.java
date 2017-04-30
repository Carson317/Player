package com.carson.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.carson.player.pager.BasePager;
import com.carson.player.pager.MusicPager;
import com.carson.player.pager.OnlineMusicPager;
import com.carson.player.pager.OnlineVideoPager;
import com.carson.player.pager.VideoPager;
import com.carson.player.utils.PlayerLogger;

import java.util.ArrayList;

/**
 * Created by heng on 2017/4/22.
 */
public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    /*
     * Exceptions found during parsing
     *
     * References a layout (@layout/custom_titlelbar)
     */

    // Content View Elements
    private FrameLayout mContainer;
    private RadioButton mRb_video;
    private RadioButton mRb_music;
    private RadioButton mRb_online_video;
    private RadioButton mRb_online_music;
    private RadioGroup mRadioGroup;


    private TextView mTv_search;
    private ImageView mIv_msm;
    private TextView mView_dot;
    private ImageView mIv_more;

    BasePager mVideoPager;
    BasePager mMusicPager;
    BasePager mOnlineMusicPager;
    BasePager mOnlineVideoPager;
    BasePager mBasePager;

    private ArrayList<BasePager> mPager = new ArrayList<>() ;
    private int position = 0;


    // End Of Content View Elements
    private void bindViews() {
        mContainer = (FrameLayout) findViewById(R.id.pager_container);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_main);
        mRb_video = (RadioButton) findViewById(R.id.rb_video);
        mRb_music = (RadioButton) findViewById(R.id.rb_music);
        mRb_online_video = (RadioButton) findViewById(R.id.rb_online_video);
        mRb_online_music = (RadioButton) findViewById(R.id.rb_online_music);

        mTv_search = (TextView) findViewById(R.id.tv_search);
        mIv_msm = (ImageView) findViewById(R.id.iv_msm);
        mView_dot = (TextView) findViewById(R.id.view_dot);
        mIv_more = (ImageView) findViewById(R.id.iv_more);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PlayerLogger.Debug("");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.colorPrimary));
        }

        bindViews();

        mVideoPager = new VideoPager(this);
        mMusicPager = new MusicPager(this);
        mOnlineMusicPager = new OnlineMusicPager(this);
        mOnlineVideoPager = new OnlineVideoPager(this);

        mPager.add(mVideoPager);
        mPager.add(mMusicPager);
        mPager.add(mOnlineVideoPager);
        mPager.add(mOnlineMusicPager);

        mRadioGroup.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        mRb_video.setChecked(true);
    }

    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            PlayerLogger.Debug("onCheckedChanged checkedId:"+ checkedId);
            switch (checkedId){
                case R.id.rb_video:
                    position = 0;
                    break;
                case R.id.rb_music:
                    position = 1;
                    break;
                case R.id.rb_online_video:
                    position = 2;
                    break;
                case R.id.rb_online_music:
                    position = 3;
                    break;
                default:
                    position = 0;
                    break;
            }
            setFragment();

        }
    }

    private void setFragment() {
            FragmentManager mFragmentManager = getFragmentManager();
            FragmentTransaction ft = mFragmentManager.beginTransaction();
            ft.replace(R.id.pager_container, new Fragment(){
                @Nullable
                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
                    PlayerLogger.Debug("setFragment");
                    mBasePager = getBasePager();
                    if(mBasePager!= null){
                        return  mBasePager.mPagerView;
                    }
                    return null;
                }
            } );

            ft.commit();
    }

    private BasePager getBasePager() {
            mBasePager =  mPager.get(position);
            PlayerLogger.Debug("mIsInitData"+ mBasePager.mIsInitData);
            if(mBasePager != null && !mBasePager.mIsInitData){
                PlayerLogger.Debug("BEGIN INIT DATA");
                mBasePager.initData();
                mBasePager.mIsInitData = true;
            }
            return mBasePager;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
