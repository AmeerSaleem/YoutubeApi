package com.example.youtubeapi;

import com.example.youtubeapi.Model.ContentDetails;
import com.example.youtubeapi.Model.YoutubeUserModel;
import com.example.youtubeapi.Model.YoutubeVideoModel;
import com.example.youtubeapi.Model2.YoutubeVideoMd;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YoutubeService {

    //Call to fetch initial batch of Data
    @GET("search")
    Call<YoutubeUserModel> getData(
            @Query("part") String snippet,
            @Query("order") String date,
            @Query("channelId") String channelId,
            @Query("key") String ApiKey,
            @Query("maxResults") String maxResults);

    //call to fetch subsequent data
    @GET("search")
    Call<YoutubeUserModel> getNextData(
            @Query("part") String snippet,
            @Query("order") String date,
            @Query("channelId") String channelId,
            @Query("key") String ApiKey,
            @Query("maxResults") String maxResults,
            @Query("pageToken") String nextPageToken);

    //Call to fetch the video duration info
    @GET("videos")
    Call<YoutubeVideoMd> getVideoDetails(
                            @Query("part") String contentDetails,
                            @Query("id") String videoId,
                            @Query("key") String apiKey);

}