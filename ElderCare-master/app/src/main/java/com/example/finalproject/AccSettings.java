package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

//import com.example.finalproject.EditUser;
import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class AccSettings extends Fragment {

    private EditText nameEditText, heightEditText, weightEditText, dobEditText, genderEditText;
    private EditText Doc1EditText, Med1EditText, Doc2EditText, Med2EditText;
    private EditText MedicineEditText, MobilityEditText;
    private EditText PathoEditText, PhysioEditText;
    private Button saveButton;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    public static String finaluser;
    public static String resultemail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acc_settings, container, false);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("ElderCare Application");

        nameEditText = view.findViewById(R.id.editName);
        heightEditText = view.findViewById(R.id.editHeight);
        weightEditText = view.findViewById(R.id.editWeight);
        dobEditText = view.findViewById(R.id.editdob);
        genderEditText = view.findViewById(R.id.editGender);

        Doc1EditText = view.findViewById(R.id.editDocName1);
        Med1EditText = view.findViewById(R.id.editMed1);
        Doc2EditText = view.findViewById(R.id.editDocName2);
        Med2EditText = view.findViewById(R.id.editMed2);

        MedicineEditText = view.findViewById(R.id.editMedicine);
        MobilityEditText = view.findViewById(R.id.editMobility);

        PathoEditText = view.findViewById(R.id.editPatho);
        PhysioEditText = view.findViewById(R.id.editPhysio);

        saveButton = view.findViewById(R.id.saveButton);

        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser users = firebaseAuth.getCurrentUser();
        finaluser = users.getEmail();
        resultemail = finaluser.replace(".", "");

        mDatabase.child("Users").child(resultemail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
//                    EditUser user = snapshot.getValue(EditUser.class);
                    User user = snapshot.getValue(User.class);
//                    nameEditText.setText(user.getName());
//                    heightEditText.setText(user.getHeight());
//                    weightEditText.setText(user.getWeight());
//                    dobEditText.setText(user.getDob());
//                    genderEditText.setText(user.getGender());
                    String doc1 = snapshot.child("Doctor Details").child("docname1").getValue(String.class);
                    String med1 = snapshot.child("Doctor Details").child("medicines1").getValue(String.class);
                    String medicalprob = snapshot.child("Medical Details").child("medical").getValue(String.class);
                    String mobility = snapshot.child("Medical Details").child("mod").getValue(String.class);
                    String patho = snapshot.child("Pathology").child("patho").getValue(String.class);
                    String physio = snapshot.child("Pathology").child("physio").getValue(String.class);
                    String dob = snapshot.child("User Details").child("dob").getValue(String.class);
                    String gender = snapshot.child("User Details").child("gender").getValue(String.class);
                    String heightuser = snapshot.child("User Details").child("height").getValue(String.class);
                    String imageurl = snapshot.child("User Details").child("imageURL").getValue(String.class);
                    String nameuser = snapshot.child("User Details").child("name").getValue(String.class);
                    String weight = snapshot.child("User Details").child("weight").getValue(String.class);

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

                    nameEditText.setText(userDetails.getName());
                    heightEditText.setText(userDetails.getHeight());
                    weightEditText.setText(userDetails.getWeight());
                    dobEditText.setText(userDetails.getDob());
                    genderEditText.setText(userDetails.getGender());
                    Doc1EditText.setText(doctorDetails.getDoc1());
                    Med1EditText.setText(doctorDetails.getDoc1());
                    Doc2EditText.setText(doctorDetails.getMed1());
                    Med2EditText.setText(doctorDetails.getDoc1());
                    MedicineEditText.setText(medDetails.getMedical());
                    MobilityEditText.setText(medDetails.getMod());
                    PathoEditText.setText(pathology.getPatho());
                    PhysioEditText.setText(pathology.getPhysio());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString().trim();
                String height = heightEditText.getText().toString().trim();
                String weight = weightEditText.getText().toString().trim();
                String dob = dobEditText.getText().toString().trim();
                String gender = genderEditText.getText().toString().trim();
                String doc1 = Doc1EditText.getText().toString().trim();
                String med1 = Med1EditText.getText().toString().trim();
                String doc2 = Doc2EditText.getText().toString().trim();
                String med2 = Med2EditText.getText().toString().trim();
                String medicine = MedicineEditText.getText().toString().trim();
                String mobility = MobilityEditText.getText().toString().trim();
                String patho = PathoEditText.getText().toString().trim();
                String physio = PhysioEditText.getText().toString().trim();
                Map<String, Object> userUpdates1 = new HashMap<>();
                Map<String, Object> userUpdates2 = new HashMap<>();
                Map<String, Object> userUpdates3 = new HashMap<>();
                Map<String, Object> userUpdates4 = new HashMap<>();
//                userUpdates.equals("Doctor Details");
                userUpdates1.put("name", name);
                userUpdates1.put("height", height);
                userUpdates1.put("weight", weight);
                userUpdates1.put("dob", dob);
                userUpdates1.put("gender", gender);
                userUpdates2.put("docname1", doc1);
                userUpdates2.put("medicines1", med1);
                userUpdates2.put("docname2", doc2);
                userUpdates2.put("medicines2", med2);
                userUpdates3.put("medical", medicine);
                userUpdates3.put("mob", mobility);
                userUpdates4.put("patho", patho);
                userUpdates4.put("physio", physio);

                mDatabase.child("Users").child(resultemail).child("User Details").updateChildren(userUpdates1)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
//                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                                    transaction.replace(R.id.AccSettings, new ProfileFragment());
//                                    transaction.addToBackStack(null);
//                                    transaction.commit();
                                } else {
                                    Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                mDatabase.child("Users").child(resultemail).child("Doctor Details").updateChildren(userUpdates2)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
//                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                                    transaction.replace(R.id.AccSettings, new ProfileFragment());
//                                    transaction.addToBackStack(null);
//                                    transaction.commit();
                                } else {
                                    Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                mDatabase.child("Users").child(resultemail).child("Medical Details").updateChildren(userUpdates3)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
//                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                                    transaction.replace(R.id.AccSettings, new ProfileFragment());
//                                    transaction.addToBackStack(null);
//                                    transaction.commit();
                                } else {
                                    Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                mDatabase.child("Users").child(resultemail).child("Pathology").updateChildren(userUpdates4)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getActivity(), "Profile updated successfully", Toast.LENGTH_SHORT).show();
//                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                                    transaction.replace(R.id.AccSettings, new ProfileFragment());
//                                    transaction.addToBackStack(null);
//                                    transaction.commit();
                                } else {
                                    Toast.makeText(getActivity(), "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        return view;
    }
}

