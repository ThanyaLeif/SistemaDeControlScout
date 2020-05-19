package com.example.tanialeif.sistemadecontrolscout;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.example.tanialeif.sistemadecontrolscout.Adapters.AdaptadorListaInsignias;
import com.example.tanialeif.sistemadecontrolscout.Models.Insignia;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MenuAdministrarInsignias extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    ArrayList<Insignia> listaInsignias;
    RecyclerView recyclerView;

    FirebaseStorage mStorage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FloatingActionButton btnAgregarInsignia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_administrar_insignias);

        inicializarBotones();
        inicializarFirebase();
        construirRecycler();

        mStorage = FirebaseStorage.getInstance();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerInsignias);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view_admin_insignias);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();
        if(id == R.id.itmScouterAdm){
            Intent intent = new Intent(MenuAdministrarInsignias.this, MenuPrincipalAdmin.class);
            startActivity(intent);
        }
        else if(id == R.id.itmAjustesAdm){
            Toast.makeText(this, "Menu administrar ajustes", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MenuAdministrarInsignias.this, MenuAjustesAdmin.class);
            startActivity(intent);
        }
        else if(id == R.id.itmInsigniasAdm){
            Intent intent = new Intent(MenuAdministrarInsignias.this, DetalleAgregarInsignia.class);
            startActivity(intent);
        }
        else if(id == R.id.itmSalirAdm){
            Intent intent = new Intent(MenuAdministrarInsignias.this, Login.class);
            startActivity(intent);
        }
        return false;
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void inicializarBotones(){
        btnAgregarInsignia = (FloatingActionButton)findViewById(R.id.btnNuevaInsignia);
        btnAgregarInsignia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(MenuAdministrarInsignias.this, DetalleAgregarInsignia.class);
                startActivity(detail);
            }
        });
    }

    private void eliminarInsignia(final Insignia insignia){

        StorageReference imageRef = mStorage.getReferenceFromUrl(insignia.urlImagen);
        imageRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                databaseReference.child("Insignias").child(insignia.uid).removeValue();
                Toast.makeText(MenuAdministrarInsignias.this, "Se eliminó correctamente la insignia " + insignia.nombre, Toast.LENGTH_SHORT).show();
                recreate();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MenuAdministrarInsignias.this, "No fue posible eliminar insignia " + insignia.nombre, Toast.LENGTH_SHORT).show();
            }
        });

    }

    ArrayList<Insignia> llenarLista(){
        final ArrayList<Insignia> insignias = new ArrayList<>();
        databaseReference.child("Insignias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Insignia insignia = snapshot.getValue(Insignia.class);
                    insignias.add(insignia);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MenuAdministrarInsignias.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });

        return insignias;
    }

    private void construirRecycler(){
        listaInsignias = llenarLista();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerListaInsignias);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AdaptadorListaInsignias adaptador = new AdaptadorListaInsignias(listaInsignias);
        adaptador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                CharSequence opciones[] = {
                        "Editar",
                        "Eliminar"
                };
                AlertDialog.Builder menu = new AlertDialog.Builder(MenuAdministrarInsignias.this);
                menu.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:
                                Intent detail = new Intent(MenuAdministrarInsignias.this, DetalleAgregarInsignia.class);
                                detail.putExtra("isEdit", true);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("insignia", listaInsignias.get(recyclerView.getChildAdapterPosition(v)));
                                detail.putExtras(bundle);
                                startActivity(detail);
                               break;
                            case 1:
                                eliminarInsignia(listaInsignias.get(recyclerView.getChildAdapterPosition(v)));
                                break;
                        }
                    }
                });
                menu.show();
                return true;
            }
        });
        recyclerView.setAdapter(adaptador);
    }

}
