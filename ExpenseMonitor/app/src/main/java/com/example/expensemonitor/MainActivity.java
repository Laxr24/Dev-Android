package com.example.expensemonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView = findViewById(R.id.webView);
        webView.setBackgroundColor(getResources().getColor(R.color.statusBarColor));
        String url = "https://docs.google.com/forms/d/e/1FAIpQLSe1LqjbscZs0LVOj47lsLTKYZ_Z3pLhoIZKVFK0R1JZv4IGlA/viewform";
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Sure to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        MainActivity.super.onBackPressed();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing  here
                    }
                });
        alert.show();

    }
}