package com.example.snacktime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.snacktime.Common.Common;
import com.example.snacktime.Users.Admins;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;
//todo: different admin login (doesn't make sense that user can get here)

public class AdminLoginActivity extends AppCompatActivity {
    private Button adminLoginBtn;
    private TextView nonAdminLink;
    private EditText adminID, password;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        adminLoginBtn = (Button) findViewById(R.id.adminLogin);
        nonAdminLink = (TextView) findViewById(R.id.non_admin_link);
        adminID = (EditText) findViewById(R.id.admin_edit);
        password = (EditText) findViewById(R.id.password_admin_edit);
        loading = new ProgressDialog(this);

        nonAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        adminLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setTitle("Logging in");
                loading.setMessage("Welcome administor");
                loading.setCanceledOnTouchOutside(false);
                loading.show();
                adminLogin();
            }
        });
    }

    private void adminLogin() {
        final String adminLogin = adminID.getText().toString();
        final String adminPassword = password.getText().toString();

        if(TextUtils.isEmpty(adminLogin)) {
            loading.dismiss();
            Toast.makeText(AdminLoginActivity.this, "AdminID can't be empty...", Toast.LENGTH_SHORT).show();
        } else if(TextUtils.isEmpty(adminPassword)) {
            loading.dismiss();
            Toast.makeText(AdminLoginActivity.this, "Password can't be empty...", Toast.LENGTH_SHORT).show();
        } else {
            DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
            dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(Common.ADMINS_COL).child(adminLogin).exists()) {
                        Admins admin = dataSnapshot.child(Common.ADMINS_COL).child(adminLogin).getValue(Admins.class);
                        System.out.println(admin.getAdminID());
                        if(admin.getAdminID().equals(adminLogin) && admin.getPassword().equals(adminPassword)) {
                            loading.dismiss();
                            Toast.makeText(AdminLoginActivity.this, "Logged in as admin", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AdminLoginActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        } else {
                            loading.dismiss();
                            Toast.makeText(AdminLoginActivity.this, "Your password is incorrect...", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        loading.dismiss();
                        Toast.makeText(AdminLoginActivity.this, "AdminID does not exist...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

    }
}

