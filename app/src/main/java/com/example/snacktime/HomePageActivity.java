package com.example.snacktime;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.snacktime.Common.Common;

public class HomePageActivity extends AppCompatActivity {
    private Button logoutBtn;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        logoutBtn = (Button) findViewById(R.id.logout_btn_home);
        loading = new ProgressDialog(this);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void cleanUpInfo() {
        SharedPreferences sharedPref = getSharedPreferences(Common.REMEMBER_USER_SHARED_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Common.REMEMBER_PASSWORD, "");
        editor.putString(Common.REMEMBER_USERNAME, "");
        editor.putBoolean(Common.REMEMBER_ME, false);
        editor.commit();

        loading.dismiss();
        Toast.makeText(HomePageActivity.this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(HomePageActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void logout() {
        loading.setTitle("Logging out");
        loading.setMessage("This will only take a second...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();
        cleanUpInfo();
    }
}
