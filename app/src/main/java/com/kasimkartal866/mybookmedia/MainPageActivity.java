package com.kasimkartal866.mybookmedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainPageActivity extends AppCompatActivity {

    TextView tName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        tName = findViewById(R.id.tName);
        String name = getIntent().getStringExtra("name");
        tName.setText(name);
    }
}
