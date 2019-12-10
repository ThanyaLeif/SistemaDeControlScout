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

import com.example.tanialeif.sistemadecontrolscout.Adapters.AdaptadorListaScouter;
import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.Models.Scouter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuPrincipalAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

    ArrayList <Scouter> listaScouters;
    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FloatingActionButton btnNuevoScouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerAdmin);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = findViewById(R.id.nav_view_admin);
        navigationView.setNavigationItemSelectedListener(this);

        inicializarBotones();
        inicializarFirebase();
        construirRecycler();
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
            Toast.makeText(this, "Menu administrar scouter", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.itmAjustesAdm){
            Toast.makeText(this, "Menu administrar ajustes", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.itmInsigniasAdm){
            Toast.makeText(this, "Menu administrar insignias", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.itmSalirAdm){
            Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void inicializarBotones(){
        btnNuevoScouter = (FloatingActionButton) findViewById(R.id.btnNuevoScouter);
        btnNuevoScouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(MenuPrincipalAdmin.this, DetalleAgregarScouter.class);
                startActivity(detail);
            }
        });
    }

    private void eliminarScouter(Scouter scouter){
        databaseReference.child("Scouters").child(scouter.getCUM()).removeValue();
        Toast.makeText(MenuPrincipalAdmin.this, "Se eliminó correctamente a " + scouter.getNombre(), Toast.LENGTH_SHORT).show();
        recreate();
    }

    ArrayList <Scouter> llenarLista(){
        final ArrayList <Scouter> scouters = new ArrayList<Scouter>();
        databaseReference.child("Scouters").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Scouter scouter = snapshot.getValue(Scouter.class);
                    scouters.add(scouter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MenuPrincipalAdmin.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
        return scouters;
    }

    private void construirRecycler(){
        listaScouters = llenarLista();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerListaScouters);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AdaptadorListaScouter adaptador = new AdaptadorListaScouter(listaScouters);
        adaptador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                 CharSequence[] opciones = new CharSequence[]{
                         "Editar",
                         "Eliminar"
                 };
                AlertDialog.Builder menu = new AlertDialog.Builder(MenuPrincipalAdmin.this);
                menu.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:{
                                Intent detail = new Intent(MenuPrincipalAdmin.this, DetalleAgregarScouter.class);
                                detail.putExtra("isEdit", true);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("scouter", listaScouters.get(recyclerView.getChildAdapterPosition(v)));
                                detail.putExtras(bundle);
                                startActivity(detail);
                                break;
                            }
                            case 1:{
                                eliminarScouter(listaScouters.get(recyclerView.getChildAdapterPosition(v)));
                                break;
                            }
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
