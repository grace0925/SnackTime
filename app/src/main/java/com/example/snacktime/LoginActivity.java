package com.example.snacktime;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputUsername = (EditText) findViewById(R.id.username_edit);
        inputPassword = (EditText) findViewById(R.id.password_edit);
        loginBtn = (Button) findViewById(R.id.login);
        loading = new ProgressDialog(this);

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
                    System.out.println("haha");
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
}
