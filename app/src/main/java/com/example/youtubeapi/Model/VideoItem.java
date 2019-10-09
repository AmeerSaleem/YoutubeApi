package com.example.youtubeapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoItem {

    @SerializedName("id")
    @Expose
    private Id id;
    @SerializedName("contentDetails")
    @Expose
    private ContentDetails contentDetails;

    /**
     * No args constructor for use in serialization
     *
     */
    public VideoItem() {
    }


    public VideoItem(Id id, ContentDetails snippet) {
        this.id = id;
        this.contentDetails = snippet;
    }

    /**
     * 
//     * @param id
//     * @param etag
//     * @param snippet
//     * @param kind
     */

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public ContentDetails getContentDetails() {
        return contentDetails;
    }

    public void setContentDetails(ContentDetails contentDetails) {
        this.contentDetails = contentDetails;
    }
}