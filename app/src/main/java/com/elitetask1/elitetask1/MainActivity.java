package com.elitetask1.elitetask1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.elitetask1.Gallery.GalleryActivity;
import com.elitetask1.Notification.AlarmReceiver;
import com.elitetask1.Notification.NotificationActivity;

import mehdi.sakout.fancybuttons.FancyButton;

import static maes.tech.intentanim.CustomIntent.customType;

public class MainActivity extends AppCompatActivity {

    Button b2, b3, b4, b5, b7, b8, b9, b10, b11, b12,b13,b14;
    FancyButton b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        Intent alarmIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 5000, pendingIntent);


        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (FancyButton) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        b10 = (Button) findViewById(R.id.b10);
        b11 = (Button) findViewById(R.id.b11);
        b12 = (Button) findViewById(R.id.b12);
        b13 = (Button) findViewById(R.id.b13);
        b14 = (Button) findViewById(R.id.b14);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, text.class);
                startActivity(i);
                customType(MainActivity.this, "left-to-right");


            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Edit.class);
                startActivity(i);
                customType(MainActivity.this, "fadein-to-fadeout");
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Forspinner.class);
                startActivity(i);
                customType(MainActivity.this, "rotateout-to-rotatein");
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Checkbo.class);
                startActivity(i);
                customType(MainActivity.this, "up-to-bottom");

            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, List.class);
                startActivity(i);
                customType(MainActivity.this, "bottom-to-up");

            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Grid.class);
                startActivity(i);
                customType(MainActivity.this, "bottom-to-up");

            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Toggle.class);
                startActivity(i);
                customType(MainActivity.this, "right-to-left");

            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Autocomplete.class);
                startActivity(i);
                customType(MainActivity.this, "rotateout-to-rotatein");

            }
        });

        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Recyclerview.class);
                startActivity(i);
                customType(MainActivity.this, "up-to-bottom");

            }
        });

        b11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AutoSizeRecycler.class);
                startActivity(i);
                customType(MainActivity.this, "up-to-bottom");

            }
        });

        b12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, GalleryActivity.class);
                startActivity(i);
                customType(MainActivity.this, "up-to-bottom");

            }
        });
        b13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(i);
                customType(MainActivity.this, "up-to-bottom");

            }
        });
        b14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PDF.class);
                startActivity(i);
                customType(MainActivity.this, "right-to-left");

            }
        });
    }
}
//}
//*left-to-right
//        *right-to-left
//        *bottom-to-up
//        *up-to-bottom
//        *fadein-to-fadeout
//        *rotateout-to-rotatein