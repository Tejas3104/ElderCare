package com.example.finalproject;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class AffInfoEntry extends AppCompatActivity {
    Calendar myCalender;
    private static final int PICK_IMAGE_REQUEST = 1;
    CircleImageView UploadPhoto;
    EditText etDate,NameText;
    RadioButton male, female, other;
    Uri imageUri;
    Button SubmitButton;
    public static String affname,affDOB,affimageURL,affgender;
    DatabaseReference databaseReference;
    ProgressDialog dialog;
    private FirebaseAuth firebaseAuth;
    public static String resultemail;
    public static String finaluser;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aff_info_entry);

        NameText = findViewById(R.id.name);
        etDate = findViewById(R.id.DOB);
        female = findViewById(R.id.rb1);
        male = findViewById(R.id.rb2);
        other = findViewById(R.id.rb3);
        SubmitButton = findViewById(R.id.next);
        UploadPhoto = findViewById(R.id.UpPhoto);
        myCalender = Calendar.getInstance();
        String gmale="Male";
        String gfemale="Female";
        String gother="Other";

        databaseReference = FirebaseDatabase.getInstance().getReference("ElderCare Android Application");

        mStorageRef = FirebaseStorage.getInstance().getReference("ElderCare Application").child("Affiliates");
        UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        SubmitButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View v) {
                        if(female.isChecked()){
                            affgender=gfemale;
                        }else if(male.isChecked()){
                            affgender=gmale;
                        }else{
                            affgender=gother;
                        }
                        SubmitButton.setVisibility(View.INVISIBLE);
                        uploadFile();
                    }
                }
        );

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }
        };
        etDate.setOnClickListener(view -> {
            new DatePickerDialog(AffInfoEntry.this, date, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            imageUri = data.getData();

            UploadPhoto.setImageURI(imageUri);
        }
    }

    private void uploadFile() {
        if (imageUri != null) {
            StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(imageUri));

            UploadTask uploadTask = fileReference.putFile(imageUri);
            uploadTask.addOnSuccessListener(taskSnapshot -> {

                        Toast.makeText(AffInfoEntry.this, "Saved", Toast.LENGTH_LONG).show();
                        Intent intent2 = new Intent(AffInfoEntry.this,AffMySignup.class);
                        startActivity(intent2);
                        finish();

                        Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                        while (!urlTask.isSuccessful());
                        Uri downloadUrl = urlTask.getResult();
                        affimageURL = downloadUrl.toString();
                        affname = NameText.getText().toString();
                        affDOB = etDate.getText().toString();
                        AffDataClass affDataClass=new AffDataClass(affname,affDOB,affimageURL,affgender);

                    }).addOnFailureListener(e -> Toast.makeText(AffInfoEntry.this, e.getMessage(), Toast.LENGTH_SHORT).show())
                    .addOnProgressListener(taskSnapshot -> {

                    });
        } else
        {
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        etDate.setText(dateFormat.format(myCalender.getTime()));
    }
    private String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}