package com.example.finalproject;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Option extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    public static String resultemail;
    public DatabaseReference databaseReference;
    public FirebaseDatabase usersRef;
    Button user, aff, admin;
    private static final String USERS_PATH = "/ElderCare Application/Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        firebaseAuth = FirebaseAuth.getInstance();

        user = findViewById(R.id.userbutt);
        aff = findViewById(R.id.affbutt);
        admin = findViewById(R.id.adminbutt);
        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, LogReg.class);
                startActivity(intent);
            }
        });
        aff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, AffSendOTP.class);
                startActivity(intent);
            }
        });
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Option.this, AdminLogin.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (firebaseAuth.getCurrentUser() != null) {
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser currentUser = mAuth.getCurrentUser();
            String userEmail = currentUser.getEmail();
            resultemail = userEmail.replace(".", "");
            usersRef = FirebaseDatabase.getInstance();

            if (currentUser.getEmail().equals("admin@eldercare.com")) {
                Toast.makeText(Option.this, "Admin already logged-in!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Option.this, AdminHome.class));
                finish();
            }
            else {
                checkLogin();
            }
        } else {
            Toast.makeText(Option.this, "Welcome !", Toast.LENGTH_SHORT).show();
        }
    }
    private void checkLogin() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userEmail = currentUser.getEmail();
            String formattedEmail = userEmail.replace(".", "");
            DatabaseReference userReference = usersRef.getReference("ElderCare Application/Users").child(formattedEmail);
            userReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        // User exists in the "Users" node
                        Toast.makeText(Option.this, "Welcome User!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Option.this, NavigationDrawer.class));
                    } else {
                        // User doesn't exist in the "Users" node

                        Toast.makeText(Option.this, "Welcome !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), Option.class));
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                    }
                    finish();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, databaseError.getMessage());
                }
            });
        }
    }}