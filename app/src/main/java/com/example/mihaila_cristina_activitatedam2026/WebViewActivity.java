package com.example.mihaila_cristina_activitatedam2026;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_web_view);

        // 1. luăm link-ul trimis prin Intent
        Intent it = getIntent();
        String link = it.getStringExtra("link");

        WebView wv = findViewById(R.id.webView);

        // WebViewClient face ca paginile să se deschidă în interiorul aplicației,
        // nu în browserul extern
        wv.setWebViewClient(new WebViewClient());

        // 3. îi setăm URL-ul
        wv.loadUrl(link);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.webView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}