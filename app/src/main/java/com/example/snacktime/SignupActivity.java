package com.example.snacktime;

import android.app.ProgressDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

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
        loading.setMessage("Creating your SnackTime account...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();


    }

    private void checkEmptyEditText(String str) {
        if(TextUtils.isEmpty(str)) {
            Toast.makeText(this, str.substring(0, 1).toUpperCase() + str.substring(1) + " can't be empty", Toast.LENGTH_SHORT).show();
        }
    }

    private void validateUsernameEmail () {

    }
}
