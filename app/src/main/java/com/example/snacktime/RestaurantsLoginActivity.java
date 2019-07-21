package com.example.snacktime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.RelativeLayout.LayoutParams;

public class RestaurantsLoginActivity extends AppCompatActivity {
    private RelativeLayout passwordLayout;
    private Button restIdBtn, passwordBtn;
    private TextView signupLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_login);

        passwordLayout = (RelativeLayout) findViewById(R.id.rest_login_password_layout);
        restIdBtn = (Button)findViewById(R.id.rest_id_continue);
        passwordBtn = (Button)findViewById(R.id.password_continue);
        signupLink = (TextView)findViewById(R.id.rest_login_signup_link);

        restIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordLayout.setVisibility(view.VISIBLE);
                restIdBtn.setVisibility(view.INVISIBLE);
                RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.rest_login_password_layout);
                params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                signupLink.setLayoutParams(params);
            }
        });

        passwordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


}
