package com.example.tanialeif.sistemadecontrolscout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.tanialeif.sistemadecontrolscout.Models.Admin;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatabaseReference mDatabase;
    Spinner spnUsuarios;

    private long maxId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        spnUsuarios = (Spinner)findViewById(R.id.spnUsuarios);

        // create spinnerItemList for spinner
        ArrayList<CustomItemsUsers> customList = new ArrayList<>();
        customList.add(new CustomItemsUsers("Scout", R.drawable.ic_scout_24dp));
        customList.add(new CustomItemsUsers("Padre", R.drawable.ic_padre_24dp));
        customList.add(new CustomItemsUsers("Scouter", R.drawable.ic_scouter_24dp));
        customList.add(new CustomItemsUsers("Asesor", R.drawable.ic_asesor_24dp));
        customList.add(new CustomItemsUsers("Admin", R.drawable.ic_admin_24dp));

        // create Adapter for spinner
        CustomUserAdapter customAdapter = new CustomUserAdapter(this, customList);

        if(spnUsuarios != null){
            spnUsuarios.setAdapter(customAdapter);
            spnUsuarios.setOnItemSelectedListener(this);
        }

        //inicialice the database transactions
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Admins");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxId = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomItemsUsers items = (CustomItemsUsers) parent.getSelectedItem();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void writeNewUser(String username, String password){
        Admin admin = new Admin(username, password);
        mDatabase.child(String.valueOf(maxId + 1)).setValue(admin);
        Toast.makeText(this, "Data inserted succesfully", Toast.LENGTH_SHORT).show();
    }

    private void validate(final String username, final String password){
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean finded = false;
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String cUsername = snapshot.child("username").getValue(String.class);
                    String cPassword = snapshot.child("password").getValue(String.class);

                    if(username == cUsername && password == cPassword){
                        finded = true;
                        break;
                    }
                }
                if(finded){
                    Intent intent = new Intent(Login.this, MenuAdmin.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
