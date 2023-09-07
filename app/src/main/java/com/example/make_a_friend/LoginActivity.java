package com.example.make_a_friend;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 2;
    EditText EnterPassword, EnterEmailid;
    Button btn_Login;
    private static DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        checkPermissions();
        // Set click listeners for buttons
        Button facebookButton = findViewById(R.id.facebook);
        Button googleButton = findViewById(R.id.Google);
        TextView newUserTextView = findViewById(R.id.txt_newuser);
        TextView forgotPassTextView = findViewById(R.id.txt_forgetpass);
        ImageView facebookLogoImageView = findViewById(R.id.facebooklogo);
        ImageView googleLogoImageView = findViewById(R.id.googlelogo);
        EnterEmailid = findViewById(R.id.EnterEmailid);
        EnterPassword = findViewById(R.id.EnterPassword);
        btn_Login = findViewById(R.id.btn_Login);

        dbHelper = new DatabaseHelper(this);

        facebookButton.setOnClickListener(this);
        googleButton.setOnClickListener(this);
        newUserTextView.setOnClickListener(this);
        forgotPassTextView.setOnClickListener(this);
        facebookLogoImageView.setOnClickListener(this);
        googleLogoImageView.setOnClickListener(this);

        // Set a click listener for the login button
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EnterEmailid.getText().toString().trim();
                String password = EnterPassword.getText().toString().trim();

                // Check user credentials
                if (dbHelper.checkUser(email, password)) {
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login is Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.facebook || view.getId() == R.id.facebooklogo) {
            navigateToUrl("https://www.facebook.com");
        } else if (view.getId() == R.id.Google || view.getId() == R.id.googlelogo) {
            navigateToUrl("https://www.google.com");
        } else if (view.getId() == R.id.txt_newuser) {
            // Handle the "New User" text click and navigate to the signup activity
            startActivity(new Intent(LoginActivity.this, SignupActivity.class));
        } else if (view.getId() == R.id.txt_forgetpass) {
            // Handle the "Forgot Password" text click (Add your logic here)
            startActivity(new Intent(LoginActivity.this, ForgottenActivity.class));
        }
    }

    private void navigateToUrl(String url) {
        // Open a web page using an Intent
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private boolean checkPermissions() {
        try {

            int readexternal = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int readinternal = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);
            int callPermition = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE);
            int location = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            int phonereadpermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_PHONE_STATE);


            List<String> permissionString = new ArrayList<String>();

            if (readexternal != PackageManager.PERMISSION_GRANTED) {
                permissionString.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }

            if (readinternal != PackageManager.PERMISSION_GRANTED) {
                permissionString.add(android.Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (callPermition != PackageManager.PERMISSION_GRANTED) {
             permissionString.add(android.Manifest.permission.CALL_PHONE);
             }

             if (phonereadpermission != PackageManager.PERMISSION_GRANTED) {
             permissionString.add(android.Manifest.permission.READ_PHONE_STATE);
             }
            if (location != PackageManager.PERMISSION_GRANTED) {
                permissionString.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
            }

            if (!permissionString.isEmpty()) {
                ActivityCompat.requestPermissions(this, permissionString.toArray(new String[permissionString.size()]), REQUEST_ID_MULTIPLE_PERMISSIONS);
                return false;
            }
        } catch (Exception e) {
            e.getMessage();
            Log.d("Exception in Login", "" + e.getMessage());
            return false;
        }
        return true;
    }
}
