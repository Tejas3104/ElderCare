package com.example.finalproject;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class userlist extends AppCompatActivity {
    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myAdapter;
    ArrayList<User> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlist);
try {
    recyclerView = findViewById(R.id.userList);
    database = FirebaseDatabase.getInstance().getReference("ElderCare Application").child("Users");
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        myAdapter=new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

//                    String docname1 = dataSnapshot.child("users/user1/address/city").getValue(String.class);
                    User user = dataSnapshot.getValue(User.class);

                    String doc1 = dataSnapshot.child("Doctor Details").child("docname1").getValue(String.class);
                    String med1 = dataSnapshot.child("Doctor Details").child("medicines1").getValue(String.class);
                    String medicalprob = dataSnapshot.child("Medical Details").child("medical").getValue(String.class);
                    String mobility = dataSnapshot.child("Medical Details").child("mod").getValue(String.class);
                    String patho = dataSnapshot.child("Pathology").child("patho").getValue(String.class);
                    String physio = dataSnapshot.child("Pathology").child("physio").getValue(String.class);
                    String dob = dataSnapshot.child("User Details").child("dob").getValue(String.class);
                    String gender = dataSnapshot.child("User Details").child("gender").getValue(String.class);
                    String heightuser = dataSnapshot.child("User Details").child("height").getValue(String.class);
                    String imageurl = dataSnapshot.child("User Details").child("imageURL").getValue(String.class);
                    String nameuser = dataSnapshot.child("User Details").child("name").getValue(String.class);
                    String weight = dataSnapshot.child("User Details").child("weight").getValue(String.class);

                    User.DoctorDetails doctorDetails = new User.DoctorDetails();
                    doctorDetails.setDoc1(doc1);
                    doctorDetails.setMed1(med1);
                    user.setDocd(doctorDetails);
                    User.MedDetails medDetails = new User.MedDetails();
                    medDetails.setMedical(medicalprob);
                    medDetails.setMod(mobility);
                    user.setMedd(medDetails);
                    User.Pathology pathology = new User.Pathology();
                    pathology.setPatho(patho);
                    pathology.setPhysio(physio);
                    user.setPathd(pathology);
                    User.UserDetails userDetails = new User.UserDetails();
                    userDetails.setDob(dob);
                    userDetails.setGender(gender);
                    userDetails.setHeight(heightuser);
                    userDetails.setImageURL(imageurl);
                    userDetails.setName(nameuser);
                    userDetails.setWeight(weight);
                    user.setUserd(userDetails);

                    list.add(user);
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error retrieving data: ");
            }
        });
}catch(Exception e)
{
    Toast.makeText(this, "Failed to retrieved data ! Please try again !", Toast.LENGTH_SHORT).show();
}
}
}