package com.example.snacktime;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.snacktime.Common.Common;
import com.example.snacktime.Users.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private Button userLogin, adminLogin, storeLogin;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loading = new ProgressDialog(this);

        userLogin = (Button) findViewById(R.id.main_user_login);
        adminLogin = (Button) findViewById(R.id.main_admin_login);
        storeLogin = (Button) findViewById(R.id.main_store_login);

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                Toast.makeText(MainActivity.this, "Please in as user :D", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, UserLoginActivity.class);
                startActivity(intent);
                loading.dismiss();
            }
        });

        adminLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                Toast.makeText(MainActivity.this, "Please log in as administrator :D", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AdminLoginActivity.class);
                startActivity(intent);
                loading.dismiss();
            }
        });

        storeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loading.show();
                Toast.makeText(MainActivity.this, "Please log in as administrator :D", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, RestaurantsLoginActivity.class);
                startActivity(intent);
                loading.dismiss();
            }
        });
    }
}
