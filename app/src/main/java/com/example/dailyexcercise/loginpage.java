package com.example.dailyexcercise;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitnesstracker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginpage extends AppCompatActivity {

    Button move, GotoSign;
    TextInputLayout inputEmail, inputPassword;
    TextInputLayout eml;
    String emailPattern = "[a-zA-A0-9._-]+@[a-z]+\\.+[a-z]+";
    ProgressDialog progressDialog;
    FirebaseUser mUser;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginpage);

        move = findViewById(R.id.signupLink);
        GotoSign = findViewById(R.id.new_user);
        inputEmail = findViewById(R.id.usernameLayout);
        inputPassword = findViewById(R.id.passwordLayout);
        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        GotoSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginpage.this, sign_up_page.class);
                startActivity(intent);
                finish();
            }
        });

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = inputEmail.getEditText().getText().toString();
                password = inputPassword.getEditText().getText().toString();

                if (!email.matches(emailPattern)) {
                    inputEmail.setError("Enter correct Email");
                    return; // Exit the method early if email is invalid
                }

                if (TextUtils.isEmpty(password) || password.length() < 6) {
                    inputPassword.setError("Password is incorrect");
                    return; // Exit the method early if password is invalid
                }

                progressDialog.setMessage("Please wait while logging in...");
                progressDialog.setTitle("Login");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(loginpage.this, dashboard.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(loginpage.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(loginpage.this, "Login Failed: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
