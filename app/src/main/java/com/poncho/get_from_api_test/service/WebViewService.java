package com.poncho.get_from_api_test.service;

import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebViewService {

    public static void startWebView(WebView webView, String url, ProgressBar progressBar) {
        WebSettings webSettings = webView.getSettings();
        webView.post(new Runnable() {
            @SuppressLint("SetJavaScriptEnabled")
            @Override
            public void run() {

                webSettings.setAppCacheEnabled(true);
                webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);

                webSettings.setJavaScriptEnabled(true);
                webSettings.setBuiltInZoomControls(false);

                webView.setInitialScale(1);

                webSettings.setLoadWithOverviewMode(true);
                webSettings.setUseWideViewPort(true);

                webView.loadUrl(url);

                webView.setWebViewClient(new WebViewClient() {
                    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {
                        return false;
                    }

                    public void onPageFinished(WebView view, String url) {
                        progressBar.setVisibility(View.GONE);
                        webView.setVisibility(View.VISIBLE);
                    }

                });
            }
        });
    }
}
