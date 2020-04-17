package com.tannerjones.simon;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameInfo extends AppCompatActivity {

    String title;
    String info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_layout);
        Intent intent = getIntent();

        title = intent.getStringExtra("title");

        Log.i("title", title);
        TextView title_tv = (TextView) findViewById(R.id.info_title);
        title_tv.setText(title);

        info = intent.getStringExtra("info");
        TextView info_tv = (TextView) findViewById(R.id.info);
        info_tv.setText(info);

        findViewById(R.id.menu_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
