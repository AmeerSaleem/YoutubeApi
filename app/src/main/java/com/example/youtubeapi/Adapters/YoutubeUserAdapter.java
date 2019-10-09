package com.example.youtubeapi.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubeapi.Model.Item;
import com.example.youtubeapi.R;
import com.joooonho.SelectableRoundedImageView;

import java.util.ArrayList;

public class YoutubeUserAdapter extends RecyclerView.Adapter<YoutubeUserAdapter.UserViewHolder> {

    Context context;
    ArrayList<Item> userList;
    onItemClickListener mListener;

    public interface onItemClickListener {
        public void onItemClick(int position);
    }

    public void setOnItemClickListener(YoutubeUserAdapter.onItemClickListener listener) {
        mListener = listener;
    }

    public YoutubeUserAdapter(Context context, ArrayList<Item> userList) {
        this.context = context;
        this.userList = userList;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        SelectableRoundedImageView userImage;
        TextView userTitle;
        LinearLayout linearVideo;

        public UserViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            userImage = itemView.findViewById(R.id.row_image);
            userTitle = itemView.findViewById(R.id.textView_title);
            linearVideo = itemView.findViewById(R.id.linear_video);

            linearVideo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_layout_videos, parent, false);
        UserViewHolder uvh = new UserViewHolder(v, mListener);
        return uvh;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        Item model = userList.get(position);
        String corrected = model.getSnippet().getTitle().replaceAll("&#39;", "'");
        corrected = corrected.replaceAll("&amp;", "&");
        holder.userTitle.setText(corrected);

        Glide
                .with(context)
                .load(model.getSnippet().getThumbnails().getMedium().getUrl())
                .into(holder.userImage);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void setData() {

        if (userList != null) {
            userList.clear();
        }

        notifyDataSetChanged();
    }
}
