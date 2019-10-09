package com.example.youtubeapi.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class YoutubeUserModel {

    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public YoutubeUserModel() {
    }

    /**
     *
//     * @param regionCode
//     * @param etag
     * @param items
//     * @param pageInfo
     * @param nextPageToken
//     * @param kind
     */

    public YoutubeUserModel(String nextPageToken, List<Item> items) {
        this.nextPageToken = nextPageToken;
        this.items = items;
    }


    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

}
