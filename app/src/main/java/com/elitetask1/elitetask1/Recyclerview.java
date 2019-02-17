package com.elitetask1.elitetask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Arrays;

public class Recyclerview extends AppCompatActivity {

    String[] CountryNames = new ArrayList<>(Arrays.asList("India", "Canada", "Colombia", "England", "Singapore", "Ukraine", "Monaco",
            "Morocco", "Myanmar","Default","Blue","Red", "Poland", "Sri_lanka", "United_arab_emirates", "United_states")).toArray(new String[0]);

    int flags[] = {R.drawable.india, R.drawable.canada, R.drawable.colombia, R.drawable.england, R.drawable.singapore,
            R.drawable.ukraine, R.drawable.monaco, R.drawable.morocco, R.drawable.myanmar, R.drawable.defaultcolor, R.drawable.blue, R.drawable.red, R.drawable.poland, R.drawable.srilanka,
            R.drawable.unitedarabemirates, R.drawable.unitedstates};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        CustomAdapter2 CustomAdapter2 = new CustomAdapter2(Recyclerview.this,CountryNames,flags);
        recyclerView.setAdapter(CustomAdapter2);
    }
}
