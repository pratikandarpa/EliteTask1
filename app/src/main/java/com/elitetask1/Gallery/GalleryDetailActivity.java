package com.elitetask1.Gallery;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.elitetask1.elitetask1.BuildConfig;
import com.elitetask1.elitetask1.MainActivity;
import com.elitetask1.elitetask1.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static com.elitetask1.Gallery.GalleryDetailsAdapter.abc;
import static com.elitetask1.Gallery.Utils.IMAGES;


public class GalleryDetailActivity extends AppCompatActivity {

    ArrayList<Uri> xyz = new ArrayList<Uri>();

    String[] imageUrls;
    DisplayImageOptions options;
    ImageLoader imageLoader;
    ArrayList<String> AllUrl = new ArrayList<String>();
    Context context;
    Bitmap bitmap = null;
    String valu;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    int temp = 0;
    GalleryDetailsAdapter adapter;
    public static ArrayList<Integer> itemposition = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_detail);

        recyclerView = (RecyclerView) findViewById(R.id.id_gallery_details_recycleview);
        ImageView multipalselection = (ImageView) findViewById(R.id.multipalselection);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(new GalleryDetailsAdapter(GalleryDetailActivity.this, IMAGES));

        recyclerView.addOnItemTouchListener(new GalleryDetailsAdapter(this, recyclerView, new GalleryDetailsAdapter.ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
//                Toast.makeText(GalleryDetailActivity.this, "Single Click on position        :" + position,
//                        Toast.LENGTH_SHORT).show();
                if (temp == 1) {
                    if (!itemposition.contains(position)) {
                        itemposition.add(position);
                        recyclerView.getAdapter().notifyDataSetChanged();
                    } else {
                        itemposition.remove((Integer) position);
                        recyclerView.getAdapter().notifyDataSetChanged();
                        if (itemposition.isEmpty()) {
                            temp = 0;
                        }
                    }
                    Log.i("data", "ItemPosition" + itemposition);
                }
            }

            @Override
            public void onLongClick(View view, int position) {
//                Toast.makeText(GalleryDetailActivity.this, "Long press on position :" + position,
//                        Toast.LENGTH_LONG).show();
                if (itemposition.isEmpty()) {
                    if (temp == 0) {
                        if (!itemposition.contains(position)) {
                            itemposition.add(position);
                            recyclerView.getAdapter().notifyDataSetChanged();
                        } else {
                            itemposition.remove((Integer) position);
                            recyclerView.getAdapter().notifyDataSetChanged();
                        }
                        Log.i("data", "ItemPosition" + itemposition);
                        temp = 1;
                    }
                    temp = 1;
                }
            }
        }));


        initImageLoader(getApplicationContext());
        context = GalleryDetailActivity.this;

        multipalselection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFilesTask().execute();
            }
        });
    }

    static class ViewHolder {
        ImageView imageView;
        ProgressBar progressBar;
        CheckBox checkBox;
    }

    public static void initImageLoader(Context context) {

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        ImageLoader.getInstance().init(config.build());
    }

    @SuppressLint("StaticFieldLeak")
    private class DownloadFilesTask extends AsyncTask<URL, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(GalleryDetailActivity.this);
            progressDialog.setMessage("Please Wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        protected String doInBackground(URL... urls) {
            // code that will run in the background

            for (int i = 0; i < itemposition.size(); i++) {
                valu = IMAGES[itemposition.get(i)];
                Log.i("data", "valu " + valu);
                try {
                    bitmap = Glide.with(context).asBitmap().load(valu).submit().get();
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                Uri bmpUri = null;
                try {
                    File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    out.close();
                    bmpUri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
                    //https://developer.android.com/reference/android/support/v4/content/FileProvider
                    xyz.add(bmpUri);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return "xyz";
        }

        protected void onProgressUpdate(Integer... progress) {
            // receive progress updates from doInBackground
        }

        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            Intent i = new Intent(Intent.ACTION_SEND_MULTIPLE);
            i.setType("image/*");
            i.putParcelableArrayListExtra(Intent.EXTRA_STREAM, xyz);
            context.startActivity(Intent.createChooser(i, "Share Image"));
            xyz.clear();
            // update the UI after background processes completes
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i("data", "onBackPressed");
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemposition.clear();
        recyclerView.setAdapter(new GalleryDetailsAdapter(this, IMAGES));
        Log.i("data", "onResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("data", "onDestroy");
    }


    @Override
    protected void onPause() {
        super.onPause();
        itemposition.clear();
        recyclerView.setAdapter(new GalleryDetailsAdapter(this, IMAGES));
        Log.i("data", "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("data", "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("data", "onStop");
    }
}
