package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminLogin extends AppCompatActivity {

    EditText inputEmail, inputPassword, inputConfirmPassword;
    Button btnLogin;
    String emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    public static final String SHARED_PREFS = "sharedPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        inputEmail = findViewById(R.id.emailad);
        inputPassword = findViewById(R.id.passwordad);
        btnLogin = findViewById(R.id.button8ad);
        progressDialog = new ProgressDialog(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforLogin();
            }
        });
    }

    private void perforLogin() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter correct email");
        }
        else if(password.isEmpty()  || password.length()<6)
        {
            inputPassword.setText("Enter proper password");
        }
        else
        {   if(email.equals("admin@eldercare.com")&&password.equals("ADMIN@ELDERCARE"))
        {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            progressDialog.dismiss();
            sendUserToNextActivity();
//            Toast.makeText(AdminLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.dismiss();
            Toast.makeText(AdminLogin.this, "Not able to log in", Toast.LENGTH_SHORT).show();
        } };
    }
    private void sendUserToNextActivity() {
        Intent intent = new Intent(AdminLogin.this,AdminHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void AffSendOTP(View view)
    {
        Intent intent=new Intent(this,AffSendOTP.class);
        startActivity(intent);
    }
}