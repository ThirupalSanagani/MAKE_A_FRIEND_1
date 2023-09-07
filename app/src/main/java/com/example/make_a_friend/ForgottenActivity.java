package com.example.make_a_friend;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.awt.font.NumericShaper;

public class ForgottenActivity extends AppCompatActivity {
    EditText EnterEmailid, EnterPassword, ConfirmPassword;
    Button btn_confirm;
    DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);

        EnterPassword = findViewById(R.id.EnterPassword);
        ConfirmPassword = findViewById(R.id.ConfirmPassword);
        EnterEmailid = findViewById(R.id.EnterEmailid);
        btn_confirm = findViewById(R.id.btn_confirm);

        dbHelper = new DatabaseHelper(this);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EnterEmailid.getText().toString().trim();
                String password = EnterPassword.getText().toString().trim();
                String confirmPassword = ConfirmPassword.getText().toString().trim();

                Object confirmNewPassword = new Object();
                Object newPassword = new Object();
                if (newPassword.equals(confirmNewPassword)) {
                    // Check if the email exists in the database
                    if (dbHelper.checkEmailExists(email)) {
                        // Update the user's password
                        if (dbHelper.updatePassword(email, newPassword)) {
                            Toast.makeText(ForgottenActivity.this, "Password reset successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgottenActivity.this, LoginActivity.class));
                            finish();
                        } else {
                            Toast.makeText(ForgottenActivity.this, "Password reset failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ForgottenActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgottenActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isValid(String email, String password) {
        // Implement the logic to validate user credentials from the database
        // You can use dbHelper.checkUser(email, password) method from your previous code
        // and return its result here
        return dbHelper.checkUser(email, password);
    }
}
