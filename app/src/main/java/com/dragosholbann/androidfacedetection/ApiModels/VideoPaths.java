package com.dragosholbann.androidfacedetection.ApiModels;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoPaths implements Serializable
{
    @SerializedName("video_name")
    private String videoName;

    @SerializedName("video_path")
    private String videoPath;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    @Override
    public String toString(){
        return
                "VideoPaths{" +
                        "video_name = '" + videoName + '\'' +
                        ",video_path = '" + videoPath + '\'' +
                        "}";
    }
}
