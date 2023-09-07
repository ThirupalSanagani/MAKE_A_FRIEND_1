package com.example.make_a_friend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
     EditText TxtName, TextEmail, TextPassword;
     Button btn_Signup;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        TxtName = findViewById(R.id.TxtName);
        TextEmail = findViewById(R.id.TextEmail);
        TextPassword = findViewById(R.id.TextPassword);
        btn_Signup = findViewById(R.id.btn_Signup);

        dbHelper = new DatabaseHelper(this);

        btn_Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = TxtName.getText().toString().trim();
                String email = TextEmail.getText().toString().trim();
                String password = TextPassword.getText().toString().trim();

                // Check if any of the fields are empty
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    // Handle empty fields
                } else {
                    // Add user to the database
                    long newRowId = dbHelper.addUser(name, email, password);
                    if (newRowId != -1) {
                        // Registration successful, navigate to login activity
                        startActivity(new Intent(SignupActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this,"SignUp is not done",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}