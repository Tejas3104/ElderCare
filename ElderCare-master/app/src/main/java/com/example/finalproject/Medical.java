package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Medical extends AppCompatActivity {

    CheckBox cbx1, cbx2, cbx3, cbx4, cbx5, cbx6, cbx7, cbx8, cbx9;
    Button SubmitButton;
    RadioButton indep,dep;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    public static String medical="";
    public static String mob="";
    MedicalDetails medicalDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical);

        cbx1 = findViewById(R.id.cb5);
        cbx2 = findViewById(R.id.cb1);
        cbx3 = findViewById(R.id.cb2);
        cbx4 = findViewById(R.id.cb7);
        cbx5 = findViewById(R.id.cb3);
        cbx6 = findViewById(R.id.cb8);
        cbx7 = findViewById(R.id.cb4);
        cbx8 = findViewById(R.id.cb6);
        cbx9 = findViewById(R.id.cb9);
        SubmitButton = findViewById(R.id.sbutton);
        indep=findViewById(R.id.rb1);
        dep=findViewById(R.id.rb2);

        String cb1="Arthritis";
        String cb2="Diabetes";
        String cb3="Blood Pressure";
        String cb4="Heart Disease";
        String cb5="Dementia";
        String cb6="Respiratory Disease";
        String cb7="Alzheimer's Disease";
        String cb8="Osteoporosis";
        String cb9="Obesity";
        String independant="Independant";
        String dependant="Dependant";

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cbx1.isChecked()) {
//                          pathologyDetails.setCheckBox1(cb1);
                    medical+=cb5+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx2.isChecked()) {
//                          pathologyDetails.setCheckBox2(cb2);
                    medical+=cb1+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx3.isChecked()) {
//                          pathologyDetails.setCheckBox3(cb3);
                    medical+=cb2+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx4.isChecked()) {
//                          pathologyDetails.setCheckBox4(cb4);
                    medical+=cb7+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx5.isChecked()) {
//                          pathologyDetails.setCheckBox5(cb5);
                    medical+=cb3+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx6.isChecked()) {
//                          pathologyDetails.setCheckBox6(cb6);
                    medical+=cb8+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx7.isChecked()) {
//                          pathologyDetails.setCheckBox7(cb7);
                    medical+=cb4+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx8.isChecked()) {
//                          pathologyDetails.setCheckBox8(cb8);
                    medical+=cb6+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }else{}
                if (cbx9.isChecked()) {
//                          pathologyDetails.setCheckBox9(cb9);
                    medical+=cb9+" , ";
//                          reference.child("Pathology").setValue(pathologyDetails);
                }
                else{}

                if(indep.isChecked())
                {
                    mob=independant;
                }else {
                    mob=dependant;
                }
//                Toast.makeText(getApplicationContext(), "Medical details are saved successfully!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Medical.this,UpMedData.class));
            }
        });
    }
}