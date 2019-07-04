package com.example.snacktime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

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
    }
}
