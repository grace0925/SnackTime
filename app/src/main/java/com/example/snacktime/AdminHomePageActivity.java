package com.example.snacktime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


public class AdminHomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);
        Toolbar toolbar = findViewById(R.id.action_bar_admin);
        this.setSupportActionBar(toolbar);
        toolbar.setTitle("THIS IS A TOOLBAR");
    }
}
