//package com.example.finalproject;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.ListAdapter;
//import android.widget.ListView;
//
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//
//public class RequestsActivity extends AppCompatActivity {
//
//    RecyclerView recyclerView;
//    private ListView mRequestsListView;
//    DatabaseReference mDatabase;
//    UserRequestAdapter userRequestAdapter;
//    ArrayList<Request> list;
//    private FirebaseAuth firebaseAuth;
//    public static String finaluser;
//    public static String resultemail;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_requests);
//
//        recyclerView=findViewById(R.id.RequestsActivity);
//        // Initialize the Firebase Authentication and Database objects
//        firebaseAuth = FirebaseAuth.getInstance();
//        final FirebaseUser users = firebaseAuth.getCurrentUser();
//        try{finaluser = users.getEmail();}catch(Exception e){}
//        resultemail = finaluser.replace(".", "");
//        mDatabase = FirebaseDatabase.getInstance().getReference("ElderCare Application").child("Admin").child("Requests");
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        list=new ArrayList<>();
//        userRequestAdapter=new UserRequestAdapter(this,list);
//        recyclerView.setAdapter(userRequestAdapter);
//        // Set up the ListView and adapter
//        mRequestsListView = findViewById(R.id.RequestsActivity);
//        ArrayList<UserRequest> requestsList = new ArrayList<>();
//        UserRequestAdapter adapter = new UserRequestAdapter(this,requestsList);
//        mRequestsListView.setAdapter((ListAdapter) adapter);
//
//        // Add a listener to the Firebase Realtime Database to listen for changes in the Requests node
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                // Clear the adapter before adding new data
////                adapter.clear();
//
//                // Loop through all the child nodes and add them to the adapter
//                for (DataSnapshot requestSnapshot : snapshot.getChildren()) {
//                    Request request = requestSnapshot.getValue(Request.class);
//                    String id = requestSnapshot.child("id").getValue(String.class);
//                    String title = requestSnapshot.child("title").getValue(String.class);
//                    String description = requestSnapshot.child("description").getValue(String.class);
//                    String status = requestSnapshot.child("status").getValue(String.class);
//
//                    request.setId(id);
//                    request.setTitle(title);
//                    request.setDescription(description);
//                    request.setStatus(status);
//
//                    list.add(request);
//                    adapter.add(request);
//                }
//                userRequestAdapter.notifyDataSetChanged();
//
//                // Notify the adapter that the data set has changed
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle the error
//                Log.e("Firebase", "Error retrieving data: ");
//            }
//        });
//    }
//}

package com.example.finalproject;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
public class RequestsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference mDatabase1,mDatabase2,mDatabase3,mDatabase4;
    UserRequestAdapter userRequestAdapter1,userRequestAdapter2,userRequestAdapter3,userRequestAdapter4;
    ArrayList<Request> list;
    private FirebaseAuth firebaseAuth;
    public static String finaluser;
    public static String resultemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requests);
        int i=0;
        recyclerView = findViewById(R.id.RequestsActivity);
        // Initialize the Firebase Authentication and Database objects
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        finaluser = users != null ? users.getEmail() : null;
        resultemail = finaluser != null ? finaluser.replace(".", "") : null;
        mDatabase1 = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                .child("Admin").child("Requests").child("Doctor Requests");
        mDatabase2 = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                .child("Admin").child("Requests").child("Counsellor Requests");
        mDatabase3 = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                .child("Admin").child("Requests").child("Physiotherapist Requests");
        mDatabase4 = FirebaseDatabase.getInstance().getReference("ElderCare Application")
                .child("Admin").child("Requests").child("Medical Device Requests");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        userRequestAdapter1 = new UserRequestAdapter(this,list,1);
        recyclerView.setAdapter(userRequestAdapter1);

        // Add a listener to the Firebase Realtime Database to listen for changes in the Requests node
        mDatabase1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Loop through all the child nodes and add them to the list
                for (DataSnapshot requestSnapshot : snapshot.getChildren()) {
                    Request request = requestSnapshot.getValue(Request.class);
                    if (request != null) {
                        request.setId(requestSnapshot.getKey()); // Set the ID to the snapshot key
                        list.add(request);
                    }
                }
                userRequestAdapter1.notifyDataSetChanged();            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
                Log.e("Firebase", "Error retrieving data: ", error.toException());
            }
        });

        userRequestAdapter2 = new UserRequestAdapter(this,list,2);
        recyclerView.setAdapter(userRequestAdapter2);

        // Add a listener to the Firebase Realtime Database to listen for changes in the Requests node
        mDatabase2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Loop through all the child nodes and add them to the list
                for (DataSnapshot requestSnapshot : snapshot.getChildren()) {
                    Request request = requestSnapshot.getValue(Request.class);
                    if (request != null) {
                        request.setId(requestSnapshot.getKey()); // Set the ID to the snapshot key
                        list.add(request);
                    }
                }
                userRequestAdapter2.notifyDataSetChanged();            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
                Log.e("Firebase", "Error retrieving data: ", error.toException());
            }
        });

        userRequestAdapter3 = new UserRequestAdapter(this,list,3);
        recyclerView.setAdapter(userRequestAdapter3);

        // Add a listener to the Firebase Realtime Database to listen for changes in the Requests node
        mDatabase3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Loop through all the child nodes and add them to the list
                for (DataSnapshot requestSnapshot : snapshot.getChildren()) {
                    Request request = requestSnapshot.getValue(Request.class);
                    if (request != null) {
                        request.setId(requestSnapshot.getKey()); // Set the ID to the snapshot key
                        list.add(request);
                    }
                }
                userRequestAdapter3.notifyDataSetChanged();            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
                Log.e("Firebase", "Error retrieving data: ", error.toException());
            }
        });

        userRequestAdapter4 = new UserRequestAdapter(this,list,4);
        recyclerView.setAdapter(userRequestAdapter4);

        // Add a listener to the Firebase Realtime Database to listen for changes in the Requests node
        mDatabase4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Loop through all the child nodes and add them to the list
                for (DataSnapshot requestSnapshot : snapshot.getChildren()) {
                    Request request = requestSnapshot.getValue(Request.class);
                    if (request != null) {
                        request.setId(requestSnapshot.getKey()); // Set the ID to the snapshot key
                        list.add(request);
                    }
                }
                userRequestAdapter4.notifyDataSetChanged();            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
                Log.e("Firebase", "Error retrieving data: ", error.toException());
            }
        });
    }
}

