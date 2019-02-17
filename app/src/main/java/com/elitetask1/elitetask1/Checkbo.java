package com.elitetask1.elitetask1;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Checkbo extends AppCompatActivity {

    private RadioGroup radioGroup;
    CheckBox c1, c2, c3;
    public Button b1,b2;
    RadioButton r1, r2, r3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbo);
        addListenerOnButtonClick();

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);
                if (null != rb) {
                    Toast.makeText(Checkbo.this, rb.getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void addListenerOnButtonClick() {
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        c3 = findViewById(R.id.c3);
        b1 = findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringBuilder result = new StringBuilder();
                if (c1.isChecked()) {
                    result.append("Song");
                }
                if (c2.isChecked()) {
                    result.append("\nFootball");
                }
                if (c3.isChecked()) {
                    result.append("\nDance");
                }
                Toast.makeText(getApplicationContext(), result.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

//    public void onRadioButtonClicked() {
//        r1 = findViewById(R.id.r1);
//        r2 = findViewById(R.id.r2);
//        r3 = findViewById(R.id.r3);
//        b2 = findViewById(R.id.b2);
//
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (r1.isChecked()){
//                    Toast.makeText(Checkbo.this,"A",Toast.LENGTH_LONG).show();
//                }
//                if (r2.isChecked()){
//                    Toast.makeText(Checkbo.this,"B",Toast.LENGTH_LONG).show();
//                }
//                if (r3.isChecked()){
//                    Toast.makeText(Checkbo.this,"C",Toast.LENGTH_LONG).show();
//
//                }
//            }
//        });
//    }
}
