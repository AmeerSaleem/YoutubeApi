package com.example.youtubeapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeVideoModel {

    @SerializedName("items")
    @Expose
    private List<VideoItem> items = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public YoutubeVideoModel() {
    }


    public YoutubeVideoModel(List<VideoItem> items) {
        this.items = items;
    }

    public List<VideoItem> getItems() {
        return items;
    }

    public void setItems(List<VideoItem> items) {
        this.items = items;
    }

}
