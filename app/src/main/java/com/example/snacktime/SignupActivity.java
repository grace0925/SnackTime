package com.example.snacktime;

import android.app.ProgressDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SignupActivity extends AppCompatActivity {

    //todo: email/phone verification
    //todo:
    private Button SignupButton;
    private EditText InputUsername, InputFirstname, InputLastname, Inputemail, Inputpw, InputConfirmPw;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignupButton = (Button) findViewById(R.id.signup);
        InputUsername = (EditText) findViewById(R.id.username_edit);
        InputFirstname = (EditText) findViewById(R.id.firstname_edit);
        InputLastname =(EditText) findViewById(R.id.lastname_edit);
        Inputemail = (EditText) findViewById(R.id.email_signup);
        Inputpw = (EditText) findViewById(R.id.password_signup);
        InputConfirmPw = (EditText) findViewById(R.id.confirm_password_signup);
        loading = new ProgressDialog(this);

        SignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    public void createAccount() {

        String username = InputUsername.getText().toString();
        String firstname = InputFirstname.getText().toString();
        String lastname = InputLastname.getText().toString();
        String email = Inputemail.getText().toString();
        String password = Inputpw.getText().toString();
        String confirmPw = InputConfirmPw.getText().toString();

        //checking for empty edit texts
        checkEmptyEditText(username);
        checkEmptyEditText(firstname);
        checkEmptyEditText(lastname);
        checkEmptyEditText(email);
        checkEmptyEditText(password);
        checkEmptyEditText(confirmPw);

        loading.setTitle("Create Account");
        loading.setMessage("Getting your account ready...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        validateUsernameEmail(username, email, firstname, lastname, password);
    }

    private void checkEmptyEditText(String str) {
        if(TextUtils.isEmpty(str)) {
            Toast.makeText(this, "Field can't be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void validateUsernameEmail (final String username, final String email, final String firstname, final String lastname, final String password) {
        final DatabaseReference dbRef;
        //get reference to the firebase db instance
        dbRef = FirebaseDatabase.getInstance().getReference();
        Log.d("DEBUG","Entering validateEmail");

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("DEBUG",  "data change");
                if(!(dataSnapshot.child("Users").child(username).exists())) {
                    //new HashMap to store user update
                    HashMap<String, Object> userDataMap = new HashMap<>();
                    userDataMap.put("Username", username);
                    userDataMap.put("Email", email);
                    userDataMap.put("Firstname", firstname);
                    userDataMap.put("Lastname", lastname);
                    userDataMap.put("Password", password);

                    //update "User" with email address
                    //onCompleteListener to check for completion
                    dbRef.child("Users").child(username).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d("DEBUG",  "successfully storing user");
                                Toast.makeText(SignupActivity.this, "Your account is ready!", Toast.LENGTH_SHORT).show();
                                loading.dismiss();

                                //redirect to login after signing up
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(SignupActivity.this, "Failed to create account... Network Error...", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignupActivity.this, "username" + username + " already exists", Toast.LENGTH_SHORT).show();
                    loading.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
