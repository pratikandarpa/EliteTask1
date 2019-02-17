package com.elitetask1.elitetask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    EditText e2;
    TextView t1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        e2=findViewById(R.id.e2);
        t1=findViewById(R.id.t1);
        findViewById(R.id.b1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = e2.getText().toString();
                t1.setText(str);
            }
        });
    }
}
