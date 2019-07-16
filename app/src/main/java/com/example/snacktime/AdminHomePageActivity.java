package com.example.snacktime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;


//todo: dashboard
//log in with other accounts
//user: weather, search bar at top, recommended (sliding), little user icon, my profile page, food vlog, store list, rate food/comment, shopping cart, save store/order, speech
//seller: should display distance, rating, price; add item, sales, take pictures(vision api?), receive order, confirm order, set open time
//driver: receive notification, track location, timer
//admin: view all stores(transaction history), put up promotions, blacklist user/seller/driver, view transaction history, notification(store requests)


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
