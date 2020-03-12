package com.example.tanialeif.sistemadecontrolscout.MenuScouter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Adapters.AdaptadorListaInsigniasScout;
import com.example.tanialeif.sistemadecontrolscout.Adapters.ApadadorListaScout;
import com.example.tanialeif.sistemadecontrolscout.Models.Insignia;
import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AgregarInsigniaScout extends AppCompatActivity {

    ArrayList<Insignia> listaInsignias;
    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_insignia_scout);

        inicializarFirebase();
        construirRecycler();
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    ArrayList<Insignia> llenarLista(){
        final ArrayList <Insignia> insignias = new ArrayList<Insignia>();
        databaseReference.child("Insignias").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Insignia insignia = snapshot.getValue(Insignia.class);
                    //Toast.makeText(MenuPrincipalScouter.this, "Se agregó a " + scout.nombre, Toast.LENGTH_SHORT).show();
                    insignias.add(insignia);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AgregarInsigniaScout.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(AgregarInsigniaScout.this, "Conexion con la base de datos establecida", Toast.LENGTH_SHORT).show();
        return insignias;
    }

    private void construirRecycler(){
        listaInsignias = llenarLista();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerListaInsigniasScout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        AdaptadorListaInsigniasScout adaptador = new AdaptadorListaInsigniasScout(listaInsignias); //Aquí
        adaptador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                CharSequence[] opciones = new CharSequence[]{
                        "Agregar insignia"
                };
                AlertDialog.Builder menu = new AlertDialog.Builder(AgregarInsigniaScout.this);
                menu.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:{
                                Toast.makeText(AgregarInsigniaScout.this, "hacer que agrege insignia", Toast.LENGTH_SHORT).show();
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
