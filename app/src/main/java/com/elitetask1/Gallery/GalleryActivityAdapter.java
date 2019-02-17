package com.elitetask1.Gallery;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elitetask1.elitetask1.CustomAdapter3;
import com.elitetask1.elitetask1.R;

import static android.content.ContentValues.TAG;

public class GalleryActivityAdapter extends RecyclerView.Adapter<GalleryActivityAdapter.MyViewHolder> {

    private Context context;
    private int photo[];
    private String[] namefolder;

    public GalleryActivityAdapter(Context applicationContext, int[] photo, String[] namefolder) {
        this.context = applicationContext;
        this.photo = photo;
        this.namefolder = namefolder;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallerydesign, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.icon.setImageResource(photo[i]);
        myViewHolder.names.setText(namefolder[i]);
        myViewHolder.cardclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,GalleryDetailActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return photo.length;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView names;
        ImageView icon;
        CardView cardclick;

        public MyViewHolder(View itemView) {
            super(itemView);
            names = (TextView) itemView.findViewById(R.id.namefolder);
            icon = (ImageView) itemView.findViewById(R.id.photo);
            cardclick = (CardView) itemView.findViewById(R.id.cardclick);
        }
    }
}
