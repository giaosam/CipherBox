package com.example.johnson.cipherbox;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.johnson.cipherbox.route.RouteActivity;
import com.example.johnson.cipherbox.vigenere.VigenereActivity;

public class MainActivity extends AppCompatActivity {
    private Button vigenereEntryBtn;
    private Button routeEntryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        vigenereEntryBtn = findViewById(R.id.vigenereEntryBtn);
        routeEntryBtn = findViewById(R.id.routeEntryBtn);

        vigenereEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, VigenereActivity.class);
                startActivity(intent);
            }
        });

        routeEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RouteActivity.class);
                startActivity(intent);
            }
        });
    }
}
