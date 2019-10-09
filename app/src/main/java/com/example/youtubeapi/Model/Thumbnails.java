
package com.example.youtubeapi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thumbnails {

    @SerializedName("medium")
    @Expose
    private Medium medium;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Thumbnails() {
    }

    public Thumbnails(Medium medium) {
        this.medium = medium;
    }

    /**
     * 
//     * @param _default
//     * @param high
//     * @param medium
     */

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

}
