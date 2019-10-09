package com.example.youtubeapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.youtubeapi.Adapters.YoutubeUserAdapter;
import com.example.youtubeapi.Model.Item;
import com.example.youtubeapi.Model.YoutubeUserModel;
import com.example.youtubeapi.Model.YoutubeVideoModel;
import com.example.youtubeapi.Model2.YoutubeVideoMd;
import com.joooonho.SelectableRoundedImageView;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerVideos;
    private static final String CHANNEL_ID = "UC_A--fhX5gea0i4UtpD99Gg";
    private static final String YOUTUBE_KEY = "AIzaSyBIQRGLEp-QjHRi4JV7x92skOusPC_0Itk";
    ArrayList<Item> userList;
    YoutubeUserAdapter adapter;
    LinearLayoutManager manager;
    Boolean isScrolling = false;
    SelectableRoundedImageView detailImage;
    TextView detailTitle, detailPublishDate, detailDescription, detailDuration;
    int currentItems, totalItems, scrolledOutItems;
    YoutubeService service;
    YoutubeUserModel model;
    ProgressBar progress;
    RetrofitClient client;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding Views and initializing fields
        initValues();

        //initializing retrofit client
        client = RetrofitClient.getInstance();

        fetchFirstData();

        // initializing scrollListener to carry out pagination
        setPagination4RecyclerView();

    }

    //Pagination for continuous scrolling
    private void setPagination4RecyclerView() {
        recyclerVideos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrolledOutItems = manager.findFirstVisibleItemPosition();

                if (isScrolling == true && (currentItems + scrolledOutItems == totalItems)) {
                    isScrolling = false;
                    fetchData();
                }
            }
        });

    }

    private void initValues() {

        progress = findViewById(R.id.progress);
        recyclerVideos = findViewById(R.id.recycler_videos);
        userList = new ArrayList<>();
        manager = new LinearLayoutManager(this);

        //initializing retrofit client
        client = RetrofitClient.getInstance();
    }

    private void fetchFirstData() {
        Call<YoutubeUserModel> call = client.service.getData("snippet", "date",
                CHANNEL_ID, YOUTUBE_KEY, "50");

        call.enqueue(new Callback<YoutubeUserModel>() {
            @Override
            public void onResponse(Call<YoutubeUserModel> call, Response<YoutubeUserModel> response) {

                model = ((YoutubeUserModel) response.body());
                userList = ((ArrayList) model.getItems());
                adapter = new YoutubeUserAdapter(getBaseContext(), userList);
                recyclerVideos.setAdapter(adapter);
                recyclerVideos.setLayoutManager(manager);
                adapter.setOnItemClickListener(new YoutubeUserAdapter.onItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                        alertDialog = dialogBuilder.create();
                        View dialog_view = getLayoutInflater().inflate(R.layout.detail_view_dialog, null);
                        alertDialog.setView(dialog_view);
                        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                        setdialogViews(dialog_view);
                        alertDialog.show();
                        setDetailValues(userList.get(position));
                    }
                });
            }

            @Override
            public void onFailure(Call<YoutubeUserModel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setdialogViews(View dialog_view) {
        detailTitle = dialog_view.findViewById(R.id.detail_title);
        detailPublishDate = dialog_view.findViewById(R.id.detail_publish_date);
        detailDescription = dialog_view.findViewById(R.id.detail_description);
        detailImage = dialog_view.findViewById(R.id.detail_thumbnail);
        detailDuration = dialog_view.findViewById(R.id.detail_duration);
    }

    private void setDetailValues(Item item) {

        detailTitle.setText(htmlToReadable(item.getSnippet().getTitle()));
        detailPublishDate.setText(item.getSnippet().getPublishedAt().substring(0, 10));
        detailDescription.setText(htmlToReadable(item.getSnippet().getDescription()));
        Glide.with(getApplicationContext())
                .load(item.getSnippet().getThumbnails().getMedium().getUrl())
                .into(detailImage);
        String videoId = item.getId().getVideoId();
        getDuration(videoId, detailDuration);


    }

    private void getDuration(String videoId, final TextView durationDetail) {
        final String[] duration = new String[1];
        Call<YoutubeVideoMd> callDuration = client.service.getVideoDetails("snippet,contentDetails,statistics", videoId, YOUTUBE_KEY);
        callDuration.enqueue(new Callback<YoutubeVideoMd>() {
            @Override
            public void onResponse(Call<YoutubeVideoMd> call, Response<YoutubeVideoMd> response) {
                YoutubeVideoMd modelVideo = ((YoutubeVideoMd) response.body());
                String dur = modelVideo.getItems().get(0).getContentDetails().getDuration();
                String minutes =
                        dur = dur.replace("PT", "").replace("H", "hr ").replace("M", "min ").replace("S", "sec");
                durationDetail.setText(dur);
            }

            @Override
            public void onFailure(Call<YoutubeVideoMd> call, Throwable t) {

            }
        });
    }


    private void fetchData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.VISIBLE);
                String nextPageToken = model.getNextPageToken();
                Call<YoutubeUserModel> call = client.service.getNextData("snippet", "date",
                        CHANNEL_ID, YOUTUBE_KEY, "50", nextPageToken);

                call.enqueue(new Callback<YoutubeUserModel>() {
                    @Override
                    public void onResponse(Call<YoutubeUserModel> call, Response<YoutubeUserModel> response) {
                        try {
                            model = ((YoutubeUserModel) response.body());
                            userList.addAll(((ArrayList) model.getItems()));
                        } catch (Exception e) {
                            Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                        progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<YoutubeUserModel> call, Throwable t) {
                    }
                });
            }
        }, 2000);
    }

    private String htmlToReadable(String htmlString) {

        htmlString = htmlString.replaceAll("&#39;", "'");
        return htmlString.replaceAll("&amp;", "&");

    }
}