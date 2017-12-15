package com.example.johnson.cipherbox.route;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.johnson.cipherbox.MainActivity;
import com.example.johnson.cipherbox.R;
/**
 * Created by johnson on 17-12-13.
 */

public class RouteActivity extends AppCompatActivity implements View.OnClickListener {
    
    private RouteCipherFragment cipherFragment;
    private RouteOthersFragment othersFragment;
    private RouteIntroFragment introFragment;

    private RadioButton cipherRadioBtn;
    private RadioButton othersRadioBtn;
    private RadioButton introRadioBtn;
    private Button routeBackBtn;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.route_main);

        init();
        // 设置默认的Fragment
        setDefaultFragment();
    }

    @Override
    public void onClick(View view) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.routeCipherRadioBtn:
                if(cipherFragment == null) {
                    cipherFragment = new RouteCipherFragment();
                    transaction.add(R.id.routeActivityLayout, cipherFragment);
                }

                hideFragment(transaction);
                transaction.show(cipherFragment);
                break;

            case R.id.routeOthersRadioBtn:
                if(othersFragment == null) {
                    othersFragment = new RouteOthersFragment();
                    transaction.add(R.id.routeActivityLayout, othersFragment);
                }

                hideFragment(transaction);
                transaction.show(othersFragment);
                break;

            case R.id.routeIntroRadioBtn:
                if(introFragment == null) {
                    introFragment = new RouteIntroFragment();
                    transaction.add(R.id.routeActivityLayout, introFragment);
                }

                hideFragment(transaction);
                transaction.show(introFragment);
                break;

            case R.id.routeBackBtn:
                Intent backIntent = new Intent(RouteActivity.this, MainActivity.class);
                startActivity(backIntent);
                finish();
                break;
        }
        transaction.commit();
    }

    private void init() {
        cipherRadioBtn = findViewById(R.id.routeCipherRadioBtn);
        othersRadioBtn = findViewById(R.id.routeOthersRadioBtn);
        introRadioBtn = findViewById(R.id.routeIntroRadioBtn);
        routeBackBtn = findViewById(R.id.routeBackBtn);

        cipherRadioBtn.setOnClickListener(this);
        othersRadioBtn.setOnClickListener(this);
        introRadioBtn.setOnClickListener(this);
        routeBackBtn.setOnClickListener(this);
    }

    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        cipherFragment = new RouteCipherFragment();
        transaction.add(R.id.routeActivityLayout, cipherFragment);
        transaction.commit();
    }

    // Hide all fragments
    private void hideFragment(FragmentTransaction transaction) {
        if(cipherFragment != null)
            transaction.hide(cipherFragment);
        if(othersFragment != null)
            transaction.hide(othersFragment);
        if(introFragment != null)
            transaction.hide(introFragment);
    }
}
