package com.example.snacktime;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.snacktime.Common.Common;
import com.example.snacktime.Users.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText inputUsername, inputPassword;
    private Button loginBtn;
    private ProgressDialog loading;
    private String dbParentCol = "Users";
    private CheckBox rememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.username_edit);
        inputPassword = (EditText) findViewById(R.id.password_edit);
        loginBtn = (Button) findViewById(R.id.login);
        loading = new ProgressDialog(this);
        rememberMe = (CheckBox) findViewById(R.id.remember_me_checkbox);


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void login() {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();

        //check empty fields
        if(TextUtils.isEmpty(username)){
            Toast.makeText(this, "Username can't be empty...", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Password can't be empty...", Toast.LENGTH_SHORT).show();
        } else {
            loading.setTitle("Login");
            loading.setMessage("Trying really hard to log you in...");
            loading.setCanceledOnTouchOutside(false);
            loading.show();

            validateAccount(username, password);
            //check remember me checkbox
            if(rememberMe.isChecked()) {
                saveRememberMe(true);
            } else {
                saveRememberMe(false);
            }
        }

    }

    //todo: allow login with both username and email address
    private void validateAccount(final String username, final String password) {
        final DatabaseReference dbRef;
        dbRef = FirebaseDatabase.getInstance().getReference();

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(dbParentCol).child(username).exists()) {
                    Users newUser = dataSnapshot.child(dbParentCol).child(username).getValue(Users.class);
                    if(newUser.getUsername().equals(username) && newUser.getPassword().equals(password)) {
                        Toast.makeText(LoginActivity.this, "Hooray! Logged in successfully!", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                        Intent intent = new Intent(LoginActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Incorrect password :(", Toast.LENGTH_SHORT).show();
                        loading.dismiss();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Oops, this account " + username + " doesn't exist...", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void saveRememberMe(Boolean rememberMe) {
        System.out.println("-----Entering saveRememberme-----");
        SharedPreferences sharedPref = getSharedPreferences(Common.REMEMBER_USER_SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(Common.REMEMBER_ME, rememberMe);
        editor.commit();
        //System.out.println("SHARED PREF REMEMEBR USER: " + sharedPref.getBoolean(Common.REMEMBER_ME, true));
    }
}
