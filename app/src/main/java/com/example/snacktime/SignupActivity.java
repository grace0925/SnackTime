package com.example.snacktime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private Button SignupButton;
    private EditText InputUsername, InputFirstname, InputLastname, Inputemail, Inputpw, InputConfirmPw;

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

        SignupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    public void CreateAccount() {
        String username = InputUsername.getText().toString();
        String firstname = InputFirstname.getText().toString();
        String lastname = InputLastname.getText().toString();
        String email = Inputemail.getText().toString();
        String password = Inputpw.getText().toString();
        String confirmPw = InputConfirmPw.getText().toString();

        checkEmptyEditText(username);
    }

    public void checkEmptyEditText(String str) {
        if(TextUtils.isEmpty(str)) {
            Toast.makeText(this, str.substring(0, 1).toUpperCase() + str.substring(1) + " can't be empty", Toast.LENGTH_SHORT).show();
        }
    }
}
