package com.example.johnson.cipherbox.route;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.johnson.cipherbox.R;
import com.example.johnson.cipherbox.Utils;

/**
 * Created by johnson on 17-12-15.
 */

public class RouteOthersFragment extends Fragment implements View.OnClickListener {
    private InputMethodManager imm;

    private EditText routeOthersOriginText;
    private EditText routeOthersKeyLength;
    private EditText routeOthersResultText;
    private RadioButton routeInwardRadioBtn;
    private RadioButton routeClockRadioBtn;
    private RadioButton routeAntiClockRadioBtn;
    private RadioButton routeTopRadioBtn;
    private RadioButton routeBottomtRadioBtn;
    private RadioButton routeLeftRadioBtn;
    private RadioButton routeRightRadioBtn;
    private LinearLayout routeOthersEncryptLayout;
    private LinearLayout routeOthersDecryptLayout;

    private boolean clockwise;
    private boolean top;
    private boolean left;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.route_others_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        routeOthersOriginText = view.findViewById(R.id.routeOthersOriginText);
        routeOthersKeyLength = view.findViewById(R.id.routeOthersKeyLength);
        routeOthersResultText = view.findViewById(R.id.routeOthersResultText);

        routeInwardRadioBtn = view.findViewById(R.id.routeInwardRadioBtn);
        routeClockRadioBtn = view.findViewById(R.id.routeClockRadioBtn);
        routeAntiClockRadioBtn = view.findViewById(R.id.routeAntiClockRadioBtn);
        routeTopRadioBtn = view.findViewById(R.id.routeTopRadioBtn);
        routeBottomtRadioBtn = view.findViewById(R.id.routeBottomRadioBtn);
        routeLeftRadioBtn = view.findViewById(R.id.routeLeftRadioBtn);
        routeRightRadioBtn = view.findViewById(R.id.routeRightRadioBtn);
        routeOthersEncryptLayout = view.findViewById(R.id.routeOthersEncryptLayout);
        routeOthersDecryptLayout = view.findViewById(R.id.routeOthersDecryptLayout);

        routeInwardRadioBtn.setOnClickListener(this);
        routeClockRadioBtn.setOnClickListener(this);
        routeAntiClockRadioBtn.setOnClickListener(this);
        routeTopRadioBtn.setOnClickListener(this);
        routeBottomtRadioBtn.setOnClickListener(this);
        routeLeftRadioBtn.setOnClickListener(this);
        routeRightRadioBtn.setOnClickListener(this);
        routeOthersEncryptLayout.setOnClickListener(this);
        routeOthersDecryptLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.routeOthersEncryptLayout:
                routeOthersEncrypt(routeOthersOriginText.getText().toString(), routeOthersKeyLength.getText().toString());
                break;

            case R.id.routeOthersDecryptLayout:
                break;

            case R.id.routeClockRadioBtn:
                clockwise = true;
                break;

            case R.id.routeAntiClockRadioBtn:
                clockwise = false;
                break;

            case R.id.routeTopRadioBtn:
                top = true;
                break;

            case R.id.routeBottomRadioBtn:
                top = false;
                break;

            case R.id.routeLeftRadioBtn:
                left = true;
                break;

            case R.id. routeRightRadioBtn:
                left = false;
                break;
        }
    }

    private void routeOthersEncrypt(String plainText, String key) {
        if(plainText.equals("")) {
            Utils.sendAlert(getActivity(),"Input error", "Please Enter The Plaintext First!");
            return;
        }
        int keyLength = Integer.parseInt(key);

        int goLeft = 0;
        int goRight = 0;
        int goDown = 0;
        int goUp = 0;

        clockwise = routeClockRadioBtn.isChecked();
        top = routeTopRadioBtn.isChecked();
        left = routeLeftRadioBtn.isChecked();



    }
}
