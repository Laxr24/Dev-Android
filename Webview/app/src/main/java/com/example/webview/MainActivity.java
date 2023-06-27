package com.example.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    WebView viewContainer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewContainer = findViewById(R.id.viewContainer);
        WebSettings ws = viewContainer.getSettings();
        viewContainer.loadUrl("https://ishwardy.com");
        ws.setJavaScriptEnabled(true);
        viewContainer.canGoBack();
        viewContainer.clearCache(true);
        viewContainer.isPrivateBrowsingEnabled();
        viewContainer.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(viewContainer.canGoBack()){
            viewContainer.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}