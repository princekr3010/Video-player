package com.example.videoplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<VideoHolder>  {

    private Context context;
    ArrayList<File> videoArrayList;

    public MyAdapter(Context context, ArrayList<File> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @Override
    public VideoHolder onCreateViewHolder( ViewGroup viewGroup, int viewType) {
        View mview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.video_item,viewGroup,false);
        return new VideoHolder(mview);
    }

    @Override
    public void onBindViewHolder( final VideoHolder holder, int i) {

       holder.txtFileName.setText(PlayList.fileArrayList.get(i).getName());
        Bitmap bitmapThumbnail = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(i).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        holder.imageThumbnail.setImageBitmap(bitmapThumbnail);


        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Player.class);
                intent.putExtra("position",holder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
       if(videoArrayList.size()>0){
           return  videoArrayList.size();
       }
       else{
           return 1;
       }
    }
}

class VideoHolder extends  RecyclerView.ViewHolder{

    TextView txtFileName;
    ImageView imageThumbnail;
    CardView mCardView;

    VideoHolder(View view){
        super(view);

        txtFileName = view.findViewById(R.id.txt_videoFileName);
        imageThumbnail = view.findViewById(R.id.iv_thmnail);
        mCardView = view.findViewById(R.id.myCardView);

    }
}