package com.elitetask1.Gallery;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.elitetask1.elitetask1.CustomAdapter2;
import com.elitetask1.elitetask1.R;
import com.elitetask1.elitetask1.Recyclerview;


public class GalleryActivity extends AppCompatActivity {

    String[] namefolder = {"Camera", "Instagram", "Whatsapp", "Facebook"};
    int photo[] = {R.drawable.india, R.drawable.singapore, R.drawable.unitedarabemirates, R.drawable.ukraine};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);


        RecyclerView recyclerView = findViewById(R.id.id_gallery_recycleview);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);

        GalleryActivityAdapter customAdapter = new GalleryActivityAdapter(GalleryActivity.this, photo, namefolder);
        recyclerView.setAdapter(customAdapter);
    }
}
