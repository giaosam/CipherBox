package com.example.johnson.cipherbox.vigenere;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.johnson.cipherbox.R;

/**
 * Created by johnson on 17-12-14.
 */

public class VigenereIntroFragment extends Fragment {
    private WebView vigenereIntroWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.intro_main, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        vigenereIntroWebView = view.findViewById(R.id.vigenereIntroWebView);
        vigenereIntroWebView.loadUrl("https://en.wikipedia.org/wiki/Vigen%C3%A8re_cipher");
    }
}
