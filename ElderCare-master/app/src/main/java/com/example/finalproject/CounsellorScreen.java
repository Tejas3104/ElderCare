package com.example.finalproject;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class CounsellorScreen extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private String resultemail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counsellor_screen);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            resultemail = user.getEmail().replace(".", "");
        }
        mDatabase = FirebaseDatabase.getInstance().getReference("ElderCare Application");
        // Set the click listener for the send button
        Button sendButton = findViewById(R.id.send_button);
        TextView msg = findViewById(R.id.reqestmsg);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference requestsRef = mDatabase.child("Admin").child("Requests").child("Counsellor Requests").child(resultemail);
                requestsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            Request request = snapshot.getValue(Request.class);
                            String status = request.getStatus();
                            switch (status) {
                                case "Pending":
                                    msg.setText(R.string.pending_msg);
                                    msg.setTextSize(25);
                                    sendButton.setVisibility(View.INVISIBLE);
                                    break;
                                case "Approved":
                                    msg.setText(R.string.approvedmessage);
                                    msg.setTextSize(25);
                                    sendButton.setText("Request for counsellor again");
                                    break;
                                case "Denied":
                                    msg.setText(R.string.denied_msg);
                                    msg.setTextSize(25);
                                    sendButton.setText("Request for counsellor again");
                                    break;
                            }
                        } else {
                            String des = resultemail + " is requesting for a Counsellor!";
                            Request request = new Request(firebaseAuth.getCurrentUser().getUid(), "Request for a counsellor", des, "Pending");
                            mDatabase.child("Admin").child("Requests").child("Counsellor Requests").child(resultemail).setValue(request);
                            Toast.makeText(CounsellorScreen.this, "Request sent !", Toast.LENGTH_SHORT).show();
                            TextView msg = findViewById(R.id.reqestmsg);
                            msg.setText(R.string.sent);
                            msg.setTextSize(25);
                            sendButton.setVisibility(View.INVISIBLE);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // TODO: Handle the error
                    }
                });
            }
        });
    }
}