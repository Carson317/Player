package com.carson.player.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.carson.player.R;
import com.carson.player.domain.MediaItem;
import com.carson.player.utils.PlayerLogger;
import com.carson.player.utils.TimeUtils;


import java.util.ArrayList;

/**
 * Created by heng.cao on 17-4-28.
 */

public class VideoPager extends BasePager {
    private ListView mVideolistView;
    private TextView mNoVideo;
    private ProgressBar mVideoProgress;
    private ArrayList<MediaItem> mMediaItems;

    private TimeUtils mTimeUtils;
    private VideoAdapter mVideoAddapter;

    public VideoPager(Context context) {
        super(context);
        initView();
        mTimeUtils = new TimeUtils();
    }

    @Override
    public View initView() {
        PlayerLogger.Debug("init video view without data");
        View mInflateView = View.inflate(mContext, R.layout.video_view, null);
        mVideolistView = (ListView) mInflateView.findViewById(R.id.lv_video);
        mNoVideo = (TextView) mInflateView.findViewById(R.id.tv_novideo);
        mVideoProgress = (ProgressBar) mInflateView.findViewById(R.id.pb_Video);
        mPagerView = mInflateView;

        //设置listview的item点击事件
        mVideolistView.setOnItemClickListener(new MyOnItemClickListener());
        return mPagerView;
    }
    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MediaItem mediaItem = mMediaItems.get(position);
            PlayerLogger.Debug("data:" + mediaItem.getData());
            PlayerLogger.Debug("Uri:" + Uri.parse(mediaItem.getData()));
            Intent intent = new Intent();
            intent.setDataAndType(Uri.parse("file://"+ mediaItem.getData()),"video/*");
            mContext.startActivity(intent);
        }
    }

    @Override
    public void initData() {
        //获取本地视频列表
        getLocalVideo();
        PlayerLogger.Debug("init Data");
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (mMediaItems != null && mMediaItems.size() > 0) {
                mVideoAddapter = new VideoAdapter();
                mVideolistView.setAdapter(mVideoAddapter);
                mNoVideo.setVisibility(View.GONE);
            } else {
                mNoVideo.setVisibility(View.VISIBLE);
            }

            mVideoProgress.setVisibility(View.GONE);


        }
    };

    private void getLocalVideo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentResolver mContentResolver = mContext.getContentResolver();
                String[] meidaProjection = {
                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.ARTIST,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media._ID
                };

                Cursor mCursor = mContentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, meidaProjection, null, null, MediaStore.Video.Media.DEFAULT_SORT_ORDER);
                mMediaItems = new ArrayList<MediaItem>();
                if (mCursor != null && mCursor.moveToFirst()) {

                    do {
                        MediaItem meidaItem = new MediaItem();
                        mMediaItems.add(meidaItem);

                        String dispalyName = mCursor.getString(0);
                        meidaItem.setName(dispalyName);

                        long duration = mCursor.getLong(1);
                        meidaItem.setDuration(duration);

                        long size = mCursor.getLong(2);
                        meidaItem.setSize(size);

                        String data = mCursor.getString(4);
                        meidaItem.setData(data);


                        long id = mCursor.getInt(mCursor.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                        meidaItem.setId(id);

//                        String[] thumbProjection = {
//                                MediaStore.Video.Thumbnails.VIDEO_ID,
//                                MediaStore.Video.Thumbnails.DATA
//                        };
//                        String selection = MediaStore.Video.Thumbnails.VIDEO_ID + " = " + id;
//                        Cursor thumbCursor = mContext.getContentResolver().query(MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, thumbProjection, selection, null, null);
//                        if (thumbCursor != null) {
//                            String thumbPath = thumbCursor.getString(thumbCursor.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));
//                            String test = thumbPath;
//                            meidaItem.setThumbPath(thumbPath);
//                        }

                    } while (mCursor.moveToNext());
                }
                mCursor.close();
                mHandler.sendEmptyMessage(1000);//???
            }
        }).start();
    }

    class VideoAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mMediaItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mMediaItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(mContext, R.layout.item_video, null);
                viewHolder.thumbImage = (ImageView) convertView.findViewById(R.id.iv_video);
                viewHolder.dispayName = (TextView) convertView.findViewById(R.id.tv_videoName);
                viewHolder.duration = (TextView) convertView.findViewById(R.id.tv_videoDuration);
                viewHolder.size = (TextView) convertView.findViewById(R.id.tv_videoSize);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            MediaItem mediaItem = mMediaItems.get(position);
            //set data
            viewHolder.dispayName.setText(mediaItem.getName());
            viewHolder.size.setText(Formatter.formatFileSize(mContext, mediaItem.getSize()));
            viewHolder.duration.setText(mTimeUtils.stringForTime((int) mediaItem.getSize()));
            //null判断
            //viewHolder.thumbImage.setImageURI(Uri.parse(mMediaItems.get(position).getThumbPath()));

            return convertView;
        }
    }

    static class ViewHolder {
        ImageView thumbImage;
        TextView dispayName;
        TextView duration;
        TextView size;
    }
}
