
package com.example.youtubeapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailSnippet {


    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("thumbnails")
    @Expose
    private DetailThumbnails thumbnails;

    /**
     * No args constructor for use in serialization
     *
     */
    public DetailSnippet() {
    }

    public DetailSnippet(String title, DetailThumbnails thumbnails) {
        this.title = title;
        this.thumbnails = thumbnails;
    }


    /**
     *
     //     * @param publishedAt
     //     * @param title
     //     * @param channelId
     //     * @param description
     //     * @param channelTitle
     //     * @param thumbnails
     //     * @param liveBroadcastContent
     */

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public DetailThumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(DetailThumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

}
