package com.siyal.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import constants.Constants;

public class SearchedURLActivity extends AppCompatActivity {

    private Button homeButton;
    private EditText searchEditText;
    private Button searchButton;
    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched_u_r_l);

        homeButton = (Button) findViewById(R.id.searched_url_home_button);
        searchEditText = (EditText) findViewById(R.id.searched_url_search_edit_text);
        searchButton = (Button) findViewById(R.id.searched_url_search_button);
        webView = (WebView) findViewById(R.id.searched_url_web_view);

        url = getIntent().getExtras().getString(Constants.URL_ADDRESS);
        searchEditText.setText(url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchedText = searchEditText.getText().toString();
                if(TextUtils.isEmpty(searchedText))
                    Toast.makeText(SearchedURLActivity.this, "Please enter url first",
                            Toast.LENGTH_LONG).show();
                else{
                    String https = "https://";
                    String www = "www.";
                    if(searchedText.startsWith("https://www."))
                        webView.loadUrl(searchedText);
                    else if(searchedText.startsWith("www."))
                        webView.loadUrl(https+searchedText);
                    else
                        webView.loadUrl(https+www+searchedText);

                    searchEditText.setText("");
                    searchEditText.requestFocus();
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchedURLActivity.this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
            webView.goBack();
        else
            super.onBackPressed();
    }
}