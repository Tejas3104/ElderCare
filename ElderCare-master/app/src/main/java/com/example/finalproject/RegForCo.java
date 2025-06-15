package com.example.finalproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class
RegForCo extends AppCompatActivity {
    EditText name, mobile, email, qual, exp, license;
    Button SubmitButton;
    DatabaseReference databaseReference;
    DatabaseReference databaseReference1;
    FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    public static String resultemail;
    public static String finaluser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_for_co);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        finaluser = users.getEmail();
        resultemail = finaluser.replace(".", "");

        databaseReference1 = FirebaseDatabase.getInstance().getReference("ElderCare Application").child("Affiliate").child("Counsellor").child(resultemail).child("Professional Details");
        databaseReference = FirebaseDatabase.getInstance().getReference("ElderCare Application");

        name = findViewById(R.id.coname);
        mobile = findViewById(R.id.comob);
        email = findViewById(R.id.comail);
        qual = findViewById(R.id.coqual);
        exp = findViewById(R.id.coexp);
        license = findViewById(R.id.clicense);
        SubmitButton=findViewById(R.id.cobutt);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addvalues();
            }
        });
    }
    private void addvalues() {
        String n=name.getText().toString();
        String e=email.getText().toString().trim();
        String m=mobile.getText().toString();
        String q=qual.getText().toString();
        String ex=exp.getText().toString();
        String l=license.getText().toString();
        if (!e.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(e).matches() && m.length()!=10) {
            // Invalid email address
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
        }
        else {
            AffDataClass affDataClass = new AffDataClass(AffInfoEntry.affname, AffInfoEntry.affDOB, AffInfoEntry.affimageURL, AffInfoEntry.affgender);
            CoClass coClass = new CoClass(n, m, e, q, ex, l);
            databaseReference.child("Affiliate").child("Counsellor").child(resultemail).child("Personal Details").setValue(affDataClass);
            databaseReference1.setValue(coClass);
            showRegistrationSuccessDialog();
        }
    }
    private void showRegistrationSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Registration Successful !")
                .setMessage("Congratulations, you have successfully registered!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signOut();
                        startActivity(new Intent(RegForCo.this,Option.class));
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}