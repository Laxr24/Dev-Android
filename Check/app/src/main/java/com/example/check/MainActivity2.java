package com.example.check;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity2 extends AppCompatActivity {

    WebView myWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent data = getIntent();
        String choice = data.getStringExtra("searchParam");

        myWeb = findViewById(R.id.myWebView);
        String url = "https://www.bing.com/search?q="+choice+"&num=1";

        WebSettings settings = myWeb.getSettings();

        myWeb.loadUrl(url);
        settings.setJavaScriptEnabled(true);
        myWeb.setWebViewClient(new WebViewClient());
        Log.d("mymsg", "MyData: "+choice);

    }

    @Override
    public void onBackPressed() {
        if(myWeb.canGoBack()){
            myWeb.goBack();
        }else{
            super.onBackPressed();
        }
    }
}