package com.example.finalproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class NavigationDrawer extends AppCompatActivity{

    FirebaseAuth mAuth;
    DatabaseReference database;
    String nameuser="";
    FirebaseUser currentUser;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    //    SharedPrefManager sharedPrefManager;
    ImageButton NavigationIcon;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        mAuth=FirebaseAuth.getInstance();
        currentUser= mAuth.getCurrentUser();
        database= FirebaseDatabase.getInstance().getReference("ElderCare Application").child("Users");
//        sharedPrefManager=new SharedPreferenceManager(getApplicationContext());
        // Navigation Drawer
        if(savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        }
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        NavigationIcon=findViewById(R.id.navigation_icon);
        NavigationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
        //Step 1
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                if (id == R.id.optHome) {
                    Toast.makeText(NavigationDrawer.this, "Home", Toast.LENGTH_SHORT).show();
                    loadFragment(new HomeFragment());
                } else if (id == R.id.optAbout) {
                    Toast.makeText(NavigationDrawer.this, "About", Toast.LENGTH_SHORT).show();
                    loadFragment(new AboutFragment());
                } else if (id == R.id.optServices) {
                    Toast.makeText(NavigationDrawer.this, "Services", Toast.LENGTH_SHORT).show();
                    loadFragment(new ServicesFragment());
                } else if (id == R.id.optMedia) {
                    Toast.makeText(NavigationDrawer.this, "Media", Toast.LENGTH_SHORT).show();
                    loadFragment(new MediaFragment());
                } else if (id == R.id.optGallery) {
                    Toast.makeText(NavigationDrawer.this, "Gallery", Toast.LENGTH_SHORT).show();
                    loadFragment(new GalleryFragment());
                } else if (id == R.id.optContactUs){
                    Toast.makeText(NavigationDrawer.this, "Contact Us", Toast.LENGTH_SHORT).show();
                    loadFragment(new ContactUsFragment());
                } else if (id == R.id.optAccsetting){
                    Toast.makeText(NavigationDrawer.this, "Account Settings", Toast.LENGTH_SHORT).show();
                    loadFragment(new AccSettings());
                }else {
                    Toast.makeText(NavigationDrawer.this, "Logout", Toast.LENGTH_SHORT).show();
                    logout(item.getActionView());
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        updateNavHeader();
    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.add(R.id.container,fragment);
        ft.replace(R.id.container,fragment);
        fm.popBackStack();
        ft.commit();
    }

    private void logout(View view) {
        logoutMenu(NavigationDrawer.this);
    }

    private void logoutMenu(NavigationDrawer navigationDrawer) {
        AlertDialog.Builder builder=new AlertDialog.Builder(navigationDrawer);
        builder.setTitle("Logout");
        builder.setMessage("Are you sure you want to logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                FirebaseAuth.getInstance().signOut();
                // Navigate to the login activity
                Intent intent = new Intent(NavigationDrawer.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    public void updateNavHeader()
    {
        navigationView = findViewById(R.id.navigationView);
        View headerView=navigationView.getHeaderView(0);
        TextView navUsername=headerView.findViewById(R.id.nav_username);
        TextView navUserMail=headerView.findViewById(R.id.nav_user_mail);
        ImageView navUserPhot=headerView.findViewById(R.id.nav_user_photo);
        navUserMail.setText(currentUser.getEmail());
        navUsername.setText(currentUser.getDisplayName());
        FirebaseStorage storage = FirebaseStorage.getInstance();

        Uri photoUrl = currentUser.getPhotoUrl();
        if (photoUrl != null) {
            StorageReference storageReference = storage.getReferenceFromUrl((currentUser.getPhotoUrl()).toString());
                Glide.with(this)
                        .load(storageReference)
                        .error(R.drawable.hand)
                        .into(navUserPhot);
        }
        else
        {
            Glide.with(this).load(ContextCompat.getDrawable(this, R.drawable.hand)).into(navUserPhot);
        }

    }

}