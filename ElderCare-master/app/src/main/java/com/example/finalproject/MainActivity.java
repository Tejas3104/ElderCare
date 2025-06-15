package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    private FirebaseAuth firebaseAuth;
//    public static String resultemail;
//    public DatabaseReference databaseReference;
//    public static String finaluser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
//        databaseReference = FirebaseDatabase.getInstance().getReference("ElderCare Application");
//        final FirebaseUser users = firebaseAuth.getCurrentUser();
//        if (users != null) {
//            finaluser = users.getEmail();
//            resultemail = finaluser.replace(".", "");
//
//        Query query = databaseReference.orderByChild("Users").equalTo(resultemail);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    Intent intent1=new Intent(MainActivity.this,NavigationDrawer.class);
//                    startActivity(intent1);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
////                throw databaseError.toException();
//            }
//        });
//
//        Query query1 = databaseReference.orderByChild("Admin").equalTo(resultemail);
//        query1.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if(snapshot.exists()){
//                    Intent intent2=new Intent(MainActivity.this,AdminHome.class);
//                    startActivity(intent2);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
////                throw databaseError.toException();
//            }
//        });
//        }
//        else {
//            Intent intent3=new Intent(this,Option.class);
//            startActivity(intent3);
//        }
    }
    public void Option(View view)
    {
        Intent intent=new Intent(this,Option.class);
        startActivity(intent);
    }
    public void send_otp(View view)
    {
        Intent intent=new Intent(this,send_otp.class);
        startActivity(intent);
    }



}