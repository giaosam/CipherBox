package com.example.johnson.cipherbox.route;

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

public class RouteIntroFragment extends Fragment {
    private WebView routeIntroWebView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.intro_main, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        routeIntroWebView = view.findViewById(R.id.vigenereIntroWebView);
        routeIntroWebView.loadUrl("http://crypto.interactive-maths.com/route-cipher.html");
    }
}
