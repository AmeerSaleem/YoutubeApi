
package com.example.youtubeapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("id")
    @Expose
    private Id id;
    @SerializedName("snippet")
    @Expose
    private Snippet snippet;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }


    public Item(Id id, Snippet snippet) {
        this.id = id;
        this.snippet = snippet;
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

    public Snippet getSnippet() {
        return snippet;
    }

    public void setSnippet(Snippet snippet) {
        this.snippet = snippet;
    }

}