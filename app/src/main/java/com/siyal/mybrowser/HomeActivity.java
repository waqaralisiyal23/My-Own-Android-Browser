package com.siyal.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import constants.Constants;

public class HomeActivity extends AppCompatActivity {

    private EditText searchEditText;
    private Button searchButton;
    private Button facebookButton;
    private Button youtubeButton;
    private Button googleButton;
    private Button instagramButton;
    private Button githubButton;
    private Button yahooButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        searchEditText = (EditText) findViewById(R.id.home_search_edit_text);
        searchButton = (Button) findViewById(R.id.home_search_button);
        facebookButton = (Button) findViewById(R.id.home_facebook_button);
        youtubeButton = (Button) findViewById(R.id.home_youtube_button);
        googleButton = (Button) findViewById(R.id.home_google_button);
        instagramButton = (Button) findViewById(R.id.home_instagram_button);
        githubButton = (Button) findViewById(R.id.home_github_button);
        yahooButton = (Button) findViewById(R.id.home_yahoo_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchedText = searchEditText.getText().toString();
                if(TextUtils.isEmpty(searchedText))
                    Toast.makeText(HomeActivity.this, "Please enter url first",
                            Toast.LENGTH_LONG).show();
                else{
                    String https = "https://";
                    String www = "www.";
                    if(searchedText.startsWith("https://www."))
                        goToSearchedURLActivity(searchedText);
                    else if(searchedText.startsWith("www."))
                        goToSearchedURLActivity(https+searchedText);
                    else
                        goToSearchedURLActivity(https+www+searchedText);

                    searchEditText.setText("");
                    searchEditText.requestFocus();
                }
            }
        });

        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchedURLActivity("https://www.facebook.com");
            }
        });

        youtubeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchedURLActivity("https://www.youtube.com");
            }
        });

        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchedURLActivity("https://www.google.com");
            }
        });

        instagramButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchedURLActivity("https://www.instagram.com");
            }
        });

        githubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchedURLActivity("https://www.github.com");
            }
        });

        yahooButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchedURLActivity("https://www.yahoo.com");
            }
        });
    }

    private void goToSearchedURLActivity(String url){
        Intent intent = new Intent(HomeActivity.this, SearchedURLActivity.class);
        intent.putExtra(Constants.URL_ADDRESS, url);
        startActivity(intent);
    }
}