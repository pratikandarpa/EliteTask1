package com.elitetask1.elitetask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class List extends AppCompatActivity {

    String[] countryNames = {"India", "Canada", "Colombia", "England", "Singapore", "Ukraine", "Monaco",
            "Morocco", "Myanmar", "Poland", "Sri_lanka", "United_arab_emirates", "United_states"};

    int flags[] = {R.drawable.india, R.drawable.canada, R.drawable.colombia, R.drawable.england, R.drawable.singapore,
            R.drawable.ukraine, R.drawable.monaco, R.drawable.morocco, R.drawable.myanmar, R.drawable.poland, R.drawable.srilanka,
            R.drawable.unitedarabemirates, R.drawable.unitedstates};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ListView listView =findViewById(R.id.list);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), flags, countryNames);
        listView.setAdapter(customAdapter);
    }
}
