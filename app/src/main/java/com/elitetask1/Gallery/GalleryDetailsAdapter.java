package com.elitetask1.Gallery;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.elitetask1.elitetask1.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

import static com.elitetask1.Gallery.GalleryDetailActivity.itemposition;

public class GalleryDetailsAdapter extends RecyclerView.Adapter<GalleryDetailsAdapter.MyViewHolder> implements RecyclerView.OnItemTouchListener {

    public Context context;
    public String photo[];

    public static String[] IMAGE_URLS;
    private LayoutInflater inflater;
    private DisplayImageOptions options;
    public static ArrayList<Integer> abc = new ArrayList<Integer>();
    private ClickListener clicklistener;
    private GestureDetector gestureDetector;

    public static interface ClickListener {
        public void onClick(View view, int position);

        public void onLongClick(View view, int position);
    }

    public GalleryDetailsAdapter(Context context, final RecyclerView recycleView, final ClickListener clicklistener) {

        this.clicklistener = clicklistener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child = recycleView.findChildViewUnder(e.getX(), e.getY());
                if (child != null && clicklistener != null) {
                    clicklistener.onLongClick(child, recycleView.getChildAdapterPosition(child));

                }
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        if (child != null && clicklistener != null && gestureDetector.onTouchEvent(motionEvent)) {
            clicklistener.onClick(child, recyclerView.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    GalleryDetailsAdapter(Context context, String[] images) {
        this.context = context;
        this.IMAGE_URLS = images;
        inflater = LayoutInflater.from(context);

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.ic_launcher)
                .showImageForEmptyUri(R.mipmap.ic_launcher)
                .showImageOnFail(R.mipmap.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_oneimageview, viewGroup, false);
        GalleryDetailsAdapter.MyViewHolder vh = new GalleryDetailsAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int position) {
        if (abc.contains(position)) {
            myViewHolder.checkBox.setChecked(true);
        } else {
            myViewHolder.checkBox.setChecked(false);
        }
        if (itemposition.contains(position)) {
            myViewHolder.imageView2.setVisibility(View.VISIBLE);
            myViewHolder.verified.setVisibility(View.VISIBLE);
        } else {
            myViewHolder.imageView2.setVisibility(View.GONE);
            myViewHolder.verified.setVisibility(View.GONE);
        }


        myViewHolder.checkBox.setId(position);
        myViewHolder.imageView.setId(position);
        myViewHolder.imageView2.setId(position);
        myViewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, Viewpagergallery.class);
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
                i.putExtra("id", position);
                context.startActivity(i);
            }
        });
        myViewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                myViewHolder.checkBox = (CheckBox) v;
                if (myViewHolder.checkBox.isChecked()) {
                    Log.i("Data", "Check" + position);
                    abc.add(position);
                    Log.i("Data", "Check" + abc);
                } else {
                    if (!myViewHolder.checkBox.isChecked())
                        abc.remove((Integer) position);
                    Log.i("Data", "Check" + abc);
                }
            }
        });

        ImageLoader.getInstance()
                .displayImage(IMAGE_URLS[position], myViewHolder.imageView, options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                        myViewHolder.progressBar.setProgress(0);
                        myViewHolder.progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                        myViewHolder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                        myViewHolder.progressBar.setVisibility(View.GONE);
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                        myViewHolder.progressBar.setProgress(Math.round(100.0f * current / total));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return IMAGE_URLS.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        CheckBox checkBox;
        @SuppressLint("StaticFieldLeak")
        ImageView imageView2,verified;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.detailsphoto);
            progressBar = (ProgressBar) itemView.findViewById(R.id.progress);
            checkBox = (CheckBox) itemView.findViewById(R.id.grid_item_checkbox);
            imageView2 = (ImageView)itemView.findViewById(R.id.detailshidephoto);
            verified = (ImageView)itemView.findViewById(R.id.verified);
        }
    }
}
