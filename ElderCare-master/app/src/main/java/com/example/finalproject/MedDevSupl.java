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
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MedDevSupl extends AppCompatActivity {
    EditText firm,owner,mobile,email,registration,gst;
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
        setContentView(R.layout.activity_med_dev_supl);
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        finaluser = users.getEmail();
        resultemail = finaluser.replace(".", "");

        databaseReference1 = FirebaseDatabase.getInstance().getReference("ElderCare Application").child("Affiliate").child("Medical Device Supplier").child(resultemail).child("Professional Details");
        databaseReference = FirebaseDatabase.getInstance().getReference("ElderCare Application");

        firm=findViewById(R.id.firmname);
        owner=findViewById(R.id.ownername);
        mobile=findViewById(R.id.mno);
        email=findViewById(R.id.mmail);
        registration=findViewById(R.id.regno);
        gst=findViewById(R.id.gstno);
            SubmitButton=findViewById(R.id.button);
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addvalues();

            }
        });
    }
    private void addvalues() {

        String f=firm.getText().toString();
        String o=owner.getText().toString();
        String m=mobile.getText().toString();
        String em=email.getText().toString().trim();
        String r=registration.getText().toString();
        String g=gst.getText().toString();

        if (!em.isEmpty() && !Patterns.EMAIL_ADDRESS.matcher(em).matches() && m.length()!=10) {

            // Invalid email address
            Toast.makeText(this, "Invalid email address or phone number !", Toast.LENGTH_SHORT).show();
        }
        else {
            AffDataClass affDataClass = new AffDataClass(AffInfoEntry.affname, AffInfoEntry.affDOB, AffInfoEntry.affimageURL, AffInfoEntry.affgender);
            MedClass medClass = new MedClass(f, o, m, em, r, g);
            databaseReference.child("Affiliate").child("Medical Device Supplier").child(resultemail).child("Personal Details").setValue(affDataClass);
            databaseReference1.setValue(medClass);
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
                        startActivity(new Intent(MedDevSupl.this,Option.class));
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}