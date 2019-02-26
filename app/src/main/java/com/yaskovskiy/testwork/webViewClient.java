package com.yaskovskiy.testwork;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webViewClient extends WebViewClient {

//    @Override
//    public void onPageStarted(WebView view, String url, Bitmap fav) {
//        super.onPageStarted(view, url, fav);
//    }

    private Context context;

    public webViewClient(Context context){
        this.context = context;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url){
        view.loadUrl(url);
//        return true;
//        Intent intent = new Intent(context, PostShowActivity.class);
//        context.startActivity(intent);
        return true;
    }

}
