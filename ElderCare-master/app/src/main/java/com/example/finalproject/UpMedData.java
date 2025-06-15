package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpMedData extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Spinner s1,s2,s3,s4;
    EditText dname1,dname2;
    EditText mname1,mname2,mname3,mname4;
    String st1,st2,st3,st4;
    String item1,item2,item3,item4;
    Button button;
    DatabaseReference databaseReference;
    MedData medData;
    String[] num={"Select frequency","One","Two","Three"};
    public static String docname1;
    public static String medicines1;
    public static String docname2;
    public static String medicines2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_up_med_data);
        s1=findViewById(R.id.spinner1);
        s2=findViewById(R.id.spinner2);
        s3=findViewById(R.id.spinner3);
        s4=findViewById(R.id.spinner4);
        dname1=findViewById(R.id.docname1);
        dname2=findViewById(R.id.adddoc2);
        mname1=findViewById(R.id.mednametext1);
        mname2=findViewById(R.id.mednametext2);
        mname3=findViewById(R.id.mednametext3);
        mname4=findViewById(R.id.mednametext4);
        button=findViewById(R.id.subbutton);
        databaseReference = FirebaseDatabase.getInstance().getReference("ElderCare Android Application");
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.numbers,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);
        s2.setAdapter(adapter);
        s2.setOnItemSelectedListener(this);
        s3.setAdapter(adapter);
        s3.setOnItemSelectedListener(this);
        s4.setAdapter(adapter);
        s4.setOnItemSelectedListener(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveValue(item1,item2,item3,item4);

            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item1=s1.getSelectedItem().toString();
        st1=item1;
        item2=s2.getSelectedItem().toString();
        st2=item2;
        item3=s3.getSelectedItem().toString();
        st3=item3;
        item4=s4.getSelectedItem().toString();
        st4=item4;
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this,"Please select frequency !",Toast.LENGTH_SHORT).show();
    }
    void SaveValue(String item1,String item2,String item3,String item4)
    {
        medicines1=mname1.getText().toString()+" - "+st1+" , "+mname2.getText().toString()+" - "+st2;
        docname1=dname1.getText().toString()+" - Medicines = "+medicines1;
        medicines2=mname3.getText().toString()+" - "+st3+" , "+mname4.getText().toString()+" - "+st4;
        docname2=dname2.getText().toString()+" - Medicines = "+medicines2;
        if(item1=="Select frequency" || item2=="Select frequency" || item3=="Select frequency" || item4=="Select frequency")
        {
            Toast.makeText(this, "Please select frequency !", Toast.LENGTH_SHORT).show();
        }
        else{
            medData=new MedData(docname1,docname2,medicines1,medicines2);
            Toast.makeText(this,"Saved !",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UpMedData.this,Patho.class));
        }
    }
}