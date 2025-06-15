package com.example.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class LogReg extends AppCompatActivity {
    EditText inputEmail, inputPassword;
    TextView txtForgotPassword;
    Button btnLogin;
    String emailPattern = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    public static final String SHARED_PREFS = "sharedPrefs";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_reg);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        inputEmail = findViewById(R.id.email);
        inputPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.button8);
        txtForgotPassword=findViewById(R.id.txtForgotPassword);
        progressDialog = new ProgressDialog(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforLogin();
            }
        });
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(LogReg.this,ForgotPasswordActivity.class));
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
        {
            progressDialog.setMessage("Please wait while Login...");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("name", "true");
                        editor.apply();
                        progressDialog.dismiss();
                        Toast.makeText(LogReg.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                    }
                    else
                    {
                        progressDialog.dismiss();
                        Toast.makeText(LogReg.this, "Invalid !"+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
    };
}
    private void sendUserToNextActivity() {
        Intent intent=new Intent(LogReg.this,NavigationDrawer.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void send_otp(View view)
    {
        Intent intent2=new Intent(this,send_otp.class);
        startActivity(intent2);
    }
}