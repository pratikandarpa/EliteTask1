package com.elitetask1.elitetask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class Autocomplete extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autocomplete);

        String[] countryNames = {"India", "canada", "colombia", "england", "singapore", "ukraine", "monaco",
                "morocco", "myanmar", "Poland", "Sri_lanka", "United_arab_emirates", "United_states","argentina"};

        AutoCompleteTextView editText = findViewById(R.id.auto);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,countryNames);
        editText.setAdapter(adapter);
    }
}
