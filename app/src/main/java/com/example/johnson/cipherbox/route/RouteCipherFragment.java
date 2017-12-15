package com.example.johnson.cipherbox.route;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.INotificationSideChannel;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.johnson.cipherbox.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by johnson on 17-12-14.
 */

public class RouteCipherFragment extends Fragment implements View.OnClickListener{
    private InputMethodManager imm;

    private EditText routeOriginText;
    private EditText routeKeyText;
    private EditText routeResultText;
    private LinearLayout routeEncryptLayout;
    private LinearLayout routeDecryptLayout;

    private List<Character> alphabets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.route_cipher_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);;

        routeOriginText = view.findViewById(R.id.routeOriginText);
        routeKeyText = view.findViewById(R.id.routeKeyText);
        routeResultText = view.findViewById(R.id.routeResultText);
        routeEncryptLayout = view.findViewById(R.id.routeEncryptLayout);
        routeDecryptLayout = view.findViewById(R.id.routeDecryptLayout);

        routeEncryptLayout.setOnClickListener(this);
        routeDecryptLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.routeEncryptLayout:
                routeEncrypt(routeOriginText.getText().toString(), routeKeyText.getText().toString());
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                break;

            case R.id.routeDecryptLayout:
                routeDecrypt(routeOriginText.getText().toString(), routeKeyText.getText().toString());
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                break;
        }
    }

    private void routeEncrypt(String plainText, String key) {
        String resultText = "";
        int totalLength = plainText.length();
        int column = key.length();
        int row = totalLength / column;
        if(totalLength % column != 0)
            row++;

        char[] plainTextArray = plainText.toCharArray();
        char[] keyArray = key.toCharArray();
        char[][] plainTextMatrix = new char[row][column];
        List<Character> keyList = new ArrayList<Character>();
        List<Character> resultTextList = new ArrayList<Character>();

        for(int i = 0; i < column; i++)
            keyList.add(keyArray[i]);

        Arrays.sort(keyArray);
        for(int cnt = 1; cnt <= column; cnt++) {
            // Change number with type char to type int
            if((keyArray[cnt - 1] - '0') != cnt) {
                routeResultText.setText("Please enter the key which is a continuous number string " +
                        "beginning from 1 with a arbitrary sequence.");
                Log.d("Ready to return", Character.toString(keyArray[cnt - 1]));
                return;
            }
        }


        for(int i = 0; i < row; i++) {
            for(int j = 0; j < column; j++) {
                if(i * column + j < totalLength)
                    plainTextMatrix[i][j] = plainTextArray[i * column + j];
                else
                    plainTextMatrix[i][j] = '$';
            }
        }

        for(int j = 1; j <= column; j++) {
            for(int i = 0 ; i < row; i++)
                resultTextList.add(plainTextMatrix[i][keyList.indexOf((char)(j + 48))]);
        }

        for(int i = 0; i < resultTextList.size(); i++)
            resultText += resultTextList.get(i);

        Log.d("HHHHHHH", resultText);

        routeResultText.setText(resultText);
    }

    private void routeDecrypt(String cipherText, String key) {
        String resultText = "";
        int totalLength = cipherText.length();
        int column = key.length();
        int row = totalLength / column;

        Log.d("ROW!!!!!!", Integer.toString(row));
        Log.d("COLUMN!!!", Integer.toString(column));

        char[] cipherTextArray = cipherText.toCharArray();
        char[] keyArray = key.toCharArray();
        char[][] plainTextMatrix = new char[row][column];
        List<Character> keyList = new ArrayList<Character>();

        for(int i = 0; i < column; i++)
            keyList.add(keyArray[i]);

        Arrays.sort(keyArray);
        for(int cnt = 1; cnt <= column; cnt++) {
            // Change number with type char to type int
            if((keyArray[cnt - 1] - '0') != cnt) {
                routeResultText.setText("Please enter the key which is a continuous number string " +
                        "beginning from 1 with a arbitrary sequence.");
                Log.d("Ready to return", Character.toString(keyArray[cnt - 1]));
                return;
            }
        }

        for(int j = 0; j < column; j++) {
            for (int i = 0; i < row; i++)
                plainTextMatrix[i][j] = cipherTextArray[(keyList.get(j) - '0' - 1) * row + i];
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0 ; j < column; j++)
                resultText += plainTextMatrix[i][j];
        }

        routeResultText.setText(resultText);
    }
}
