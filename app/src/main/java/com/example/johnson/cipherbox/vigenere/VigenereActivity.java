package com.example.johnson.cipherbox.vigenere;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.johnson.cipherbox.MainActivity;
import com.example.johnson.cipherbox.R;

/**
 * Created by johnson on 17-12-13.
 */

public class VigenereActivity extends AppCompatActivity implements View.OnClickListener {
    private VigenereCipherFragment cipherFragment;
    private VigenereIntroFragment introFragment;

    private FrameLayout vigenereActivityLayout;
    private RadioButton cipherRadioBtn;
    private RadioButton introRadioBtn;
    private Button vigenereBackBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.vigenere_main);

        init();
        // 设置默认的Fragment
        setDefaultFragment();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.vigenereCipherRadioBtn:
                if(cipherFragment == null) {
                    cipherFragment = new VigenereCipherFragment();
                    transaction.add(R.id.vigenereActivityLayout, cipherFragment);
                }

                hideFragment(transaction);
                transaction.show(cipherFragment);
                break;

            case R.id.vigenereIntroRadioBtn:
                if(introFragment == null) {
                    introFragment = new VigenereIntroFragment();
                    transaction.add(R.id.vigenereActivityLayout, introFragment);
                }

                hideFragment(transaction);
                transaction.show(introFragment);
                break;

            case R.id.vigenereBackBtn:
                Intent backIntent = new Intent(VigenereActivity.this, MainActivity.class);
                startActivity(backIntent);
                finish();
                break;
        }
        transaction.commit();
    }

    private void init() {
        vigenereActivityLayout = findViewById(R.id.vigenereActivityLayout);

        cipherRadioBtn = findViewById(R.id.vigenereCipherRadioBtn);
        introRadioBtn = findViewById(R.id.vigenereIntroRadioBtn);
        vigenereBackBtn = findViewById(R.id.vigenereBackBtn);

        cipherRadioBtn.setOnClickListener(this);
        introRadioBtn.setOnClickListener(this);
        vigenereBackBtn.setOnClickListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        cipherFragment = new VigenereCipherFragment();
        transaction.add(R.id.vigenereActivityLayout, cipherFragment);
        transaction.commit();
    }

    // Hide all fragments
    private void hideFragment(FragmentTransaction transaction) {
        if(cipherFragment != null)
            transaction.hide(cipherFragment);
        if(introFragment != null)
            transaction.hide(introFragment);
    }


}
