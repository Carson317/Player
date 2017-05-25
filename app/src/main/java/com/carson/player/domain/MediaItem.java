package com.carson.player.domain;

import java.io.Serializable;

/**
 * Created by heng.cao on 17-4-29.
 */

public class MediaItem implements Serializable {
    private String name;
    private long duration;
    private long size;
    private String artist;
    private long id;

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    private String thumbPath;
    private String filePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return filePath;
    }

    public void setData(String data) {
        this.filePath = data;
    }

    @Override
    public String toString() {
        return "MediaItem{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", size=" + size +
                ", artist='" + artist + '\'' +
                ", id=" + id +
                ", thumbPath='" + thumbPath + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
