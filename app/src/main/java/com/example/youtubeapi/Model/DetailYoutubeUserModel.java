package com.example.youtubeapi.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailYoutubeUserModel {

    @SerializedName("nextPageToken")
    @Expose
    private String nextPageToken;
    @SerializedName("items")
    @Expose
    private List<DetailItem> items = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public DetailYoutubeUserModel() {
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

    public DetailYoutubeUserModel(String nextPageToken, List<DetailItem> items) {
        this.nextPageToken = nextPageToken;
        this.items = items;
    }

    public String getNextPageToken() {
        return nextPageToken;
    }

    public void setNextPageToken(String nextPageToken) {
        this.nextPageToken = nextPageToken;
    }

    public List<DetailItem> getItems() {
        return items;
    }

    public void setItems(List<DetailItem> items) {
        this.items = items;
    }

}
