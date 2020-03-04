package com.example.tanialeif.sistemadecontrolscout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.MenuScout.MenuPrincipalScout;
import com.example.tanialeif.sistemadecontrolscout.MenuScouter.MenuPrincipalScouter;
import com.example.tanialeif.sistemadecontrolscout.Models.Padre;
import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.Models.Scouter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.tanialeif.sistemadecontrolscout.Models.Admin;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    //private DatabaseReference mDatabase;
    Spinner spnUsuarios;
    Button btnLogin;
    EditText txtUsername, txtPassword;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    private String userType = "Admin";

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
        btnLogin = (Button)findViewById(R.id.btnLogin);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtUsername = (EditText)findViewById(R.id.txtUsername);

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

        inicializarFirebase();
        //insertarAdmin("root", "root");
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                validate(txtUsername.getText().toString(), txtPassword.getText().toString(), userType);
            }
        });
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void insertarAdmin(String username, String password){
        Admin admin = new Admin();
        admin.uid = (UUID.randomUUID().toString());
        admin.username = username;
        admin.password = password;
        databaseReference.child("Admin").child(admin.uid).setValue(admin);
        Toast.makeText(this, "Data inserted succesfully", Toast.LENGTH_SHORT).show();
    }

    private void validate(final String username, final String password, final String type){
        Toast.makeText(Login.this, type, Toast.LENGTH_SHORT).show();
        switch (userType){
            case "Admin":
                databaseReference.child("Admin").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean finded = false;
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Admin admin = snapshot.getValue(Admin.class);
                            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                                finded = true;
                                break;
                            }
                        }
                        if (finded) {
                            Intent intent = new Intent(Login.this, MenuPrincipalAdmin.class);
                            //Intent intent = new Intent(Login.this, DetalleAgregarScouter.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(Login.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
                break;
            case "Scout":
                databaseReference.child("Scouts").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean finded = false;
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Scout scout = snapshot.getValue(Scout.class);
                            if(scout.getCum().equals(username) && scout.getContrasenia().equals(password)){
                                finded = true;
                                break;
                            }
                        }
                        if(finded){
                            Intent intent = new Intent(Login.this, MenuPrincipalScout.class);
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
                break;
            case "Padre":
                databaseReference.child("Padre").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean finded = false;
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Padre padre = snapshot.getValue(Padre.class);
                            if(padre.getClave().equals(username) && padre.getContrasenia().equals(password)){
                                finded = true;
                                break;
                            }
                        }
                        if(finded){
                            Intent intent = new Intent(Login.this, MenuPrincipalPadre.class);
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
                break;
            case "Scouter":
                databaseReference.child("Scouters").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        boolean finded = false;
                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                            Scouter scouter = snapshot.getValue(Scouter.class);
                            if(scouter.getCUM().equals(username) && scouter.getContrasenia().equals(password)){
                                finded = true;
                                break;
                            }
                        }
                        if(finded){
                            Intent intent = new Intent(Login.this, MenuPrincipalScouter.class);
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
                break;
            case "Asesor":
                Toast.makeText(Login.this, "Area en proceso", Toast.LENGTH_SHORT).show();
                break;
                default:
                    Toast.makeText(Login.this, "Opción inválida", Toast.LENGTH_SHORT).show();
                    break;
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomItemsUsers items = (CustomItemsUsers) parent.getSelectedItem();
        userType = items.getSpinnerText();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
