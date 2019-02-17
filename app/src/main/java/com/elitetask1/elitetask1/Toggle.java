package com.elitetask1.elitetask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.mahfa.dnswitch.DayNightSwitch;

public class Toggle extends AppCompatActivity {

    private DayNightSwitch day_night_switch;
    public LinearLayout l1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toggle);

        ToggleButton toggle = (ToggleButton) findViewById(R.id.t1);
        Switch aswitch = (Switch) findViewById(R.id.s1);
        l1 = (LinearLayout) findViewById(R.id.tog1);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplication(), "On", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplication(), "OFF", Toast.LENGTH_LONG).show();
                }
            }
        });

        aswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getApplication(), "On", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplication(), "OFF", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
