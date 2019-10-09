package com.example.youtubeapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContentDetails {


    @SerializedName("duration")
    @Expose
    private String duration;


    public ContentDetails() {
    }

    public ContentDetails(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
