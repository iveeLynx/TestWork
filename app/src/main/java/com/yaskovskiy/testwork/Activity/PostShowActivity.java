package com.yaskovskiy.testwork.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yaskovskiy.testwork.Activity.MainActivity;
import com.yaskovskiy.testwork.R;
import com.yaskovskiy.testwork.webViewClient;

public class PostShowActivity extends AppCompatActivity {

    private WebViewClient client;
    private WebView webView;

    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_show);

        Intent intent = getIntent();
        url = intent.getStringExtra(MainActivity.URL);

        webView = (WebView) findViewById(R.id.webViewPostShow);
        webView.setWebViewClient(new webViewClient(this));
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);


    }
}
