package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Patho extends AppCompatActivity
{
    CheckBox c1, c2, c3, c4, c5, c6, c7, c8, c9;
    EditText otherText;
    RadioButton yes,no;
    Button SubmitButton;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    public static String patho="";
    public static String physio="";
    PathologyDetails pathologyDetails;
    int i = 0;
    RadioGroup radiogroup;
    private FirebaseAuth firebaseAuth;
    public static String resultemail;
    public static String finaluser;
    String pathology;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patho);
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("ElderCare Application");
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        finaluser = users.getEmail();
        resultemail = finaluser.replace(".", "");

        c1 = findViewById(R.id.cb1);
        c2 = findViewById(R.id.cb2);
        c3 = findViewById(R.id.cb3);
        c4 = findViewById(R.id.cb4);
        c5 = findViewById(R.id.cb5);
        c6 = findViewById(R.id.cb6);
        c7 = findViewById(R.id.cb7);
        c8 = findViewById(R.id.cb8);
        c9 = findViewById(R.id.cb9);
        otherText = findViewById(R.id.other);
        yes = findViewById(R.id.radiobut1);
        no = findViewById(R.id.radiobut2);
        SubmitButton = findViewById(R.id.PhysButton);

        String cb1="Blood Sugar (FASTING)";
        String cb2="Blood Sugar (POST MEAL)";
        String cb3="Hb1ac";
        String cb4="Lipid Profile";
        String cb5="Thyroid";
        String cb6="Kidney Function Test";
        String cb7="Liver Function Test";
        String cb8="ECG";
        String cb9="Stress Test";
        String other=otherText.getText().toString().trim();
        String radio1="Yes";
        String radio2="No";
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {           }
        });
              SubmitButton.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      if (c1.isChecked()) {
                          patho+=cb1+" , ";
                      }else{}
                      if (c2.isChecked()) {
                          patho+=cb2+" , ";
                      }else{}
                      if (c3.isChecked()) {
                          patho+=cb3+" , ";
                      }else{}
                      if (c4.isChecked()) {
                          patho+=cb4+" , ";
                      }else{}
                      if (c5.isChecked()) {
                          patho+=cb5+" , ";
                      }else{}
                      if (c6.isChecked()) {
                          patho+=cb6+" , ";
                      }else{}
                      if (c7.isChecked()) {
                          patho+=cb7+" , ";
                      }else{}
                      if (c8.isChecked()) {
                          patho+=cb8+" , ";
                      }else{}
                      if (c9.isChecked()) {
                          patho+=cb9+" , ";
                      }else{}
                      if (!other.isEmpty()) {
                          patho+=other;
                      }else{}

                      if(yes.isChecked()){
                          physio=radio1;
                      }
                      else {
                          physio=radio2;
                      }
                      addvalues();
                      startActivity(new Intent(Patho.this,LogReg.class));
                      Toast.makeText(Patho.this,"Details Added to the Database!",Toast.LENGTH_SHORT).show();
                  }
              });
    }
    private void addvalues()
    {
      DataClass dataclass=new DataClass(InfoEntry.name,InfoEntry.DOB,InfoEntry.weight,InfoEntry.height,InfoEntry.imageURL,InfoEntry.gender);
      PathologyDetails pathologyDetails1=new PathologyDetails(Patho.patho,Patho.physio);
      MedicalDetails medicalDetails= new MedicalDetails(Medical.medical,Medical.mob);
      MedData medData=new MedData(UpMedData.docname1,UpMedData.docname2,UpMedData.medicines1,UpMedData.medicines2);
        databaseReference.child("Users").child(resultemail).child("Doctor Details").setValue(medData);
        databaseReference.child("Users").child(resultemail).child("Medical Details").setValue(medicalDetails);
        databaseReference.child("Users").child(resultemail).child("Pathology").setValue(pathologyDetails1);
        databaseReference.child("Users").child(resultemail).child("User Details").setValue(dataclass);
    }

}
