package com.elitetask1.elitetask1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Forspinner extends Activity implements AdapterView.OnItemSelectedListener {

    String[] countryNames = {"India", "Canada", "Colombia", "England", "Singapore", "Ukraine"};
    int flags[] = {R.drawable.india, R.drawable.canada, R.drawable.colombia, R.drawable.england, R.drawable.singapore, R.drawable.ukraine};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forspinner);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);

        spinner2.setOnItemSelectedListener(this);
        spinner.setOnItemSelectedListener(this);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryNames);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), flags, countryNames);
        spinner2.setAdapter(customAdapter);

//        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
//        {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
//            {
//                Toast.makeText(parent.getContext(), "Selected: ", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent)
//            {
//                // can leave this empty
//            }
//        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "Selected: " + countryNames[position], Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }
}