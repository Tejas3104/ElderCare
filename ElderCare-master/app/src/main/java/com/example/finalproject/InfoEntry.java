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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class InfoEntry extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    Calendar myCalender;
    CircleImageView UploadPhoto;
    EditText etDate, NameText, WeightText, HeightText;
    RadioButton male, female, other;
    Button SubmitButton;

    public static String name, weight, height, DOB, imageURL, gender;
    Uri imageUri;
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
        setContentView(R.layout.activity_info_entry);
        NameText = findViewById(R.id.name);
        WeightText = findViewById(R.id.weight);
        HeightText = findViewById(R.id.height);
        etDate = findViewById(R.id.date);
        female = findViewById(R.id.rb1);
        male = findViewById(R.id.rb2);
        other = findViewById(R.id.rb3);
        SubmitButton = findViewById(R.id.next);
        UploadPhoto = findViewById(R.id.UpPhoto);
        myCalender = Calendar.getInstance();
        String gmale = "Male";
        String gfemale = "Female";
        String gother = "Other";

        mStorageRef = FirebaseStorage.getInstance().getReference("ElderCare Application").child("Users");
        UploadPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });
        SubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (female.isChecked()) {
                    gender = gfemale;
                } else if (male.isChecked()) {
                    gender = gmale;
                } else {
                    gender = gother;
                }
                SubmitButton.setVisibility(View.INVISIBLE);
                uploadFile();
            }
        });
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
            new DatePickerDialog(InfoEntry.this, date, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
        });
        HeightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HeightCustomNumberPickerDialog dialog = new HeightCustomNumberPickerDialog(InfoEntry.this, 150, 100, 250, new HeightCustomNumberPickerDialog.OnNumberPickerClickListener() {
                    @Override
                    public void onNumberPickerClick(int value) {
                        // Handle the selected value from NumberPicker
//                        etDate.setText(dateFormat.format(myCalender.getTime()));
                        String height = Integer.toString(value);
                        HeightText.setText(height);
//                       Toast.makeText(InfoEntry.this, "Selected value: " + value, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });

        WeightText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeightCustomNumberPickerDialog dialog = new WeightCustomNumberPickerDialog(InfoEntry.this, 40, 20, 150, new WeightCustomNumberPickerDialog.OnNumberPickerClickListener() {
                    @Override
                    public void onNumberPickerClick(int value) {
                        // Handle the selected value from NumberPicker
                        String weight = Integer.toString(value);
                        WeightText.setText(weight);
//                        Toast.makeText(InfoEntry.this, "Selected value: " + value, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
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
    private void updateLabel() {
        String myFormat = "dd/MM/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        etDate.setText(dateFormat.format(myCalender.getTime()));
    }
private void uploadFile() {
    if (imageUri != null) {
        StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                + "." + getFileExtension(imageUri));

        UploadTask uploadTask = fileReference.putFile(imageUri);
        uploadTask.addOnSuccessListener(taskSnapshot -> {

                    Toast.makeText(InfoEntry.this, "Upload successful !", Toast.LENGTH_LONG).show();
                    Intent intent2 = new Intent(InfoEntry.this, MySignupApp.class);
                    startActivity(intent2);
                    finish();

                    Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                    while (!urlTask.isSuccessful());
                    Uri downloadUrl = urlTask.getResult();

                    imageURL = downloadUrl.toString();
                    name = NameText.getText().toString();
                    weight = WeightText.getText().toString();
                    height = HeightText.getText().toString();
                    DOB = etDate.getText().toString();
                    DataClass dataClass = new DataClass(name, DOB, weight, height, imageURL, gender);
                });
    } else
    {
        Toast.makeText(this, "Please select image !", Toast.LENGTH_SHORT).show();
    }
}
    private String getFileExtension(Uri uri)
    {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }
}
