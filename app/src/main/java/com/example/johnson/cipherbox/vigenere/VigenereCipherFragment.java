package com.example.johnson.cipherbox.vigenere;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.List;

import com.example.johnson.cipherbox.R;
import com.example.johnson.cipherbox.Utils;

/**
 * Created by johnson on 17-12-14.
 */

public class VigenereCipherFragment extends Fragment implements View.OnClickListener {

    private InputMethodManager imm;

    private EditText vigenereOriginText;
    private EditText vigenereKeyText;
    private EditText vigenereResultText;
    private LinearLayout vigenereEncryptLayout;
    private LinearLayout vigenereDecryptLayout;

    private List<Character> alphabets;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.vigenere_cipher_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);;

        vigenereOriginText = view.findViewById(R.id.vigenereOriginText);
        vigenereKeyText = view.findViewById(R.id.vigenereKeyText);
        vigenereResultText = view.findViewById(R.id.vigenereResultText);
        vigenereEncryptLayout = view.findViewById(R.id.vigenereEncryptLayout);
        vigenereDecryptLayout = view.findViewById(R.id.vigenereDecryptLayout);

        vigenereEncryptLayout.setOnClickListener(this);
        vigenereDecryptLayout.setOnClickListener(this);

        alphabets = new ArrayList<Character>();
        for(int i = (int)'A'; i < 'A' + 26; i++) {
            alphabets.add((char)i);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vigenereEncryptLayout:
                vigenereEncrypt(vigenereOriginText.getText().toString(), vigenereKeyText.getText().toString());
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                break;

            case R.id.vigenereDecryptLayout:
                vigenereDecrypt(vigenereOriginText.getText().toString(), vigenereKeyText.getText().toString());
                // 隐藏软键盘
                imm.hideSoftInputFromWindow(getActivity().getWindow().getDecorView().getWindowToken(), 0);
                break;
        }

    }

    /**
     *
     * @param plainText
     * @param key
     */
    private void vigenereEncrypt(String plainText, String key) {

        if(plainText.equals("")) {
            Utils.sendAlert(getActivity(),"Input error", "Please Enter The Plaintext First!");
            return;
        }
        if(key.equals("")) {
            Utils.sendAlert(getActivity(),"Input error", "Please Enter The Key First!");
            return;
        }

        plainText = plainText.toUpperCase();
        key = key.toUpperCase();

        String resultText = "";
        char[] plainTextArray = plainText.toCharArray();
        char[] keyArray = key.toCharArray();
        List<Character> plainTextList = new ArrayList<Character>();
        List<Character> keyList = new ArrayList<Character>();
        List<Character> resultList = new ArrayList<Character>();

        for(int i = 0; i < plainTextArray.length; i++) {
            if(plainTextArray[i] != ' ')
                plainTextList.add(plainTextArray[i]);
        }
        for(int i = 0; i < keyArray.length; i++) {
            if(keyArray[i] != ' ')
                keyList.add(keyArray[i]);
        }

        int keySize = keyList.size();

        for (int i = 0; i < plainTextList.size(); i++) {
            char p = plainTextList.get(i);
            char k = keyList.get(i % keySize);
            char c = alphabets.get((alphabets.indexOf(p) + alphabets.indexOf(k)) % 26);

            resultList.add(c);
        }

        for(int j = 0; j < resultList.size(); j++)
            resultText += resultList.get(j);

        vigenereResultText.setText(resultText);
    }

    /**
     *
     * @param cipherText
     * @param key
     */
    private void vigenereDecrypt(String cipherText, String key) {
        if(cipherText.equals("")) {
            Utils.sendAlert(getActivity(),"Input error", "Please Enter The Ciphertext First!");
            return;
        }
        if(key.equals("")) {
            Utils.sendAlert(getActivity(),"Input error", "Please Enter The Key First!");
            return;
        }

        cipherText = cipherText.toUpperCase();
        key = key.toUpperCase();

        String resultText = "";
        char[] cipherTextArray = cipherText.toCharArray();
        char[] keyArray = key.toCharArray();
        List<Character> cipherTextList = new ArrayList<Character>();
        List<Character> keyList = new ArrayList<Character>();
        List<Character> resultList = new ArrayList<Character>();

        for(int i = 0; i < cipherTextArray.length; i++) {
            if(cipherTextArray[i] != ' ')
                cipherTextList.add(cipherTextArray[i]);
        }
        for(int i = 0; i < keyArray.length; i++) {
            if(keyArray[i] != ' ')
                keyList.add(keyArray[i]);
        }
        int keySize = keyList.size();

        for (int i = 0; i < cipherTextList.size(); i++) {
            char c = cipherTextList.get(i);
            char k = keyList.get(i % keySize);

//            Log.d("---TEST---", Integer.toString(alphabets.indexOf(c)) + ": " + Integer.toString(alphabets.indexOf(k)));
            char p;
            if (alphabets.indexOf(c) < alphabets.indexOf(k))
                p = alphabets.get((alphabets.indexOf(c) - alphabets.indexOf(k) + 26) % 26);
            else
                p = alphabets.get((alphabets.indexOf(c) - alphabets.indexOf(k)) % 26);

            resultList.add(p);
        }

        for(int j = 0; j < resultList.size(); j++)
            resultText += resultList.get(j);

        vigenereResultText.setText(resultText);
    }
}
