package com.elitetask1.Gallery;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.elitetask1.elitetask1.BuildConfig;
import com.elitetask1.elitetask1.MainActivity;
import com.elitetask1.elitetask1.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Viewpagergallery extends Activity {

    public ImageView imge, cross, sharebutton;

    int position;
    private String[] images = Utils.IMAGES;
    int myposition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpagergallery);


        Intent p = getIntent();
        position = p.getExtras().getInt("id");

        PageAdapter pageAdapter = new PageAdapter(images, getBaseContext());
        WormDotsIndicator wormDotsIndicator = (WormDotsIndicator) findViewById(R.id.worm_dots_indicator);
        ViewPager viewpager = (ViewPager) findViewById(R.id.viewPager_gallery);
        viewpager.setAdapter(pageAdapter);
        wormDotsIndicator.setViewPager(viewpager);
        viewpager.setCurrentItem(position);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                myposition = i;
                Log.i("data", "OnPageScrolled" + myposition);
            }

            @Override
            public void onPageSelected(int i) {
                myposition = i;
                Log.i("data", "OnPageSelected" + myposition);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        cross = findViewById(R.id.id_cross);
        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Viewpagergallery.this, GalleryDetailActivity.class);
                startActivity(i);
            }
        });
        sharebutton = findViewById(R.id.share);
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String img = images[myposition];
//                Log.i("data","Activity position"+getposition);
                shareImage(img, Viewpagergallery.this);
                Log.i("data", "URL " + img);
            }
        });
    }


    static public void shareImage(String url, final Context context) {
        Picasso.with(context).load(url).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/*");
                i.putExtra(Intent.EXTRA_STREAM, getLocalBitmapUri(bitmap, context));
                context.startActivity(Intent.createChooser(i, "Share Image"));
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }

    static public Uri getLocalBitmapUri(Bitmap bmp, Context context) {
        Uri bmpUri = null;
        try {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
            //https://developer.android.com/reference/android/support/v4/content/FileProvider

        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
