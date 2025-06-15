package com.example.finalproject;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
public class AffReg extends AppCompatActivity {
Button Docbutton;
Button Cobutton;
Button Physbutton;
Button Medbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff_reg);

        Docbutton = findViewById(R.id.docbutton);
        Cobutton = findViewById(R.id.conbutton);
        Physbutton = findViewById(R.id.physbutton);
        Medbutton = findViewById(R.id.meddevbutton);

        Docbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AffReg.this, RegForDoc.class));
            }
        });

        Cobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AffReg.this, RegForCo.class));
            }
        });

        Physbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AffReg.this, RegForPhys.class));
            }
        });

        Medbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AffReg.this, MedDevSupl.class));
            }
        });
    }
}