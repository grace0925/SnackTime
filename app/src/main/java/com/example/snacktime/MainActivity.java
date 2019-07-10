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
    private static String savedUsername, savedPassword;

    private Button signUpBtn, loginBtn;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signUpBtn = (Button) findViewById(R.id.signup_btn);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loading = new ProgressDialog(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        if (checkRememberMe()) {
            System.out.println("Check rememebr me is true");
            reLogin(savedUsername, savedPassword);
        }
    }

    private Boolean checkRememberMe() {
        SharedPreferences sharedPref = getSharedPreferences(Common.REMEMBER_USER_SHARED_PREF, MODE_PRIVATE);
        savedPassword = sharedPref.getString(Common.REMEMBER_PASSWORD, "");
        savedUsername = sharedPref.getString(Common.REMEMBER_USERNAME, "");
        return sharedPref.getBoolean(Common.REMEMBER_ME, true);
    }

    private void reLogin(final String username, final String password) {
        final DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.child(Common.USERS_COL).child(username).getValue(Users.class);
                System.out.println("user: " + user.getUsername());
                if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                    Toast.makeText(MainActivity.this, "Welcome back! You were already logged in!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                    startActivity(intent);
                } else {
                    cleanUpInfo();
                    showAlert();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Error").setIcon(R.drawable.ic_launcher_foreground).setMessage("Your password is incorrect... Please re-login to continue!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
        builder.create().show();
    }

    private void cleanUpInfo() {
        SharedPreferences sharedPref = getSharedPreferences(Common.REMEMBER_USER_SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Common.REMEMBER_PASSWORD, "");
        editor.putString(Common.REMEMBER_USERNAME, "");
        editor.putBoolean(Common.REMEMBER_ME, false);
        editor.commit();
    }
}
