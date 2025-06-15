package com.example.finalproject;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
public class MySignupApp extends AppCompatActivity {
    EditText inputEmail,inputPassword,inputConfirmPassword;
    Button btnRegister;
    String emailPattern= "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
   ProgressDialog progressDialog;
   FirebaseAuth mAuth;
   FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_my_signup_app);
        inputEmail=findViewById(R.id.email);
        inputPassword=findViewById(R.id.password);
        inputConfirmPassword=findViewById(R.id.repassword);
        btnRegister=findViewById(R.id.signupbtn);
        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null && user.isEmailVerified()) {
                    // Launch Medical activity
                    Intent intent = new Intent(MySignupApp.this, Medical.class);
                    startActivity(intent);
                }
            }
        });
        mUser=mAuth.getCurrentUser();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }
    private void PerformAuth() {
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();
        String confirmpassword=inputConfirmPassword.getText().toString();
        if(!email.matches(emailPattern))
        {
            inputEmail.setError("Enter valid email !");
            inputEmail.requestFocus();
        }
        else if(email.isEmpty() || email.length()<10)
        {
            inputEmail.setError("Enter valid email !");
            inputEmail.requestFocus();
        }
        else if(password.isEmpty()  || password.length()<8)
        {
            inputPassword.setText("Password should to more than or equal to 8 characters !");
            inputPassword.requestFocus();
        }else if(confirmpassword.isEmpty()  || confirmpassword.length()<8)
        {
            inputPassword.setText("Password should to more than or equal to 8 characters !");
            inputPassword.requestFocus();
        }
        else if(!password.equals(confirmpassword))
        {
            inputPassword.setError("Passwords do not match !");
            inputPassword.requestFocus();
            inputConfirmPassword.setError("Passwords do not match !");
            inputConfirmPassword.requestFocus();
            inputConfirmPassword.setText("");
        }
        else
        {
            progressDialog.setMessage("Please wait while registration...");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            doRegister(email,password);
        }
    }
        private void doRegister(String email, String password)
        {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser mUser1 = mAuth.getCurrentUser();
                    mUser1.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
//                            Toast.makeText(MySignupApp.this,"Email has been sent to your email address !",Toast.LENGTH_SHORT).show();
                            sendVerificationEmail();
                            sendUserToNextActivity();
                            progressDialog.dismiss();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
//                            Toast.makeText(getApplicationContext(),"Oops! Failed to send verification email !",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if(e instanceof FirebaseAuthUserCollisionException)
                {
                    inputEmail.setError("Email already registered !");
                    inputEmail.requestFocus();
                    progressDialog.dismiss();
                }
                else
                {
                    Toast.makeText(MySignupApp.this,"Oops! Something went wrong.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendUserToNextActivity() {
        Intent intent=new Intent(MySignupApp.this,Medical.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void sendVerificationEmail() {
        if(mAuth.getCurrentUser()!=null)
        {
            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
//                        Toast.makeText(MySignupApp.this, "Email has been sent to your email address !", Toast.LENGTH_SHORT).show();
                        if(task.isComplete() && mUser.isEmailVerified())
                        {
                        Toast.makeText(MySignupApp.this, "Registration Successful !", Toast.LENGTH_SHORT).show();
                        sendUserToNextActivity();
                        }
                    }
                    else{
//                        Toast.makeText(getApplicationContext(),"Oops! Failed to send verification email !",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
}}