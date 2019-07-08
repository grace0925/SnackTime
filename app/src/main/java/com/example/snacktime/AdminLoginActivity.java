package com.example.snacktime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;
//todo: different admin login (doesn't make sense that user can get here)

public class AdminLoginActivity extends AppCompatActivity {
    private Button adminLoginBtn;
    private TextView nonAdminLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminLoginBtn = (Button) findViewById(R.id.adminLogin);
        nonAdminLink = (TextView) findViewById(R.id.non_admin_link);

        nonAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
