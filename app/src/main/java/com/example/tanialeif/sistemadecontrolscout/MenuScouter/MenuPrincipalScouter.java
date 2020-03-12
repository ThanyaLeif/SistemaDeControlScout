package com.example.tanialeif.sistemadecontrolscout.MenuScouter;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Insignia;
import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.Adapters.ApadadorListaScout;
import com.example.tanialeif.sistemadecontrolscout.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuPrincipalScouter extends AppCompatActivity {

    ArrayList <Scout> listaScouts;
    RecyclerView recyclerView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    FloatingActionButton btnNuevoScout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_scouter);

        inicializarBotones();
        inicializarFirebase();
        construidRecycler();
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void inicializarBotones(){
        btnNuevoScout = (FloatingActionButton)findViewById(R.id.btnNuevoScout);
        btnNuevoScout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(MenuPrincipalScouter.this, DetalleAgregarScout.class);
                startActivity(detail);
            }
        });
    }

    private void eliminarScout(Scout scout){
        databaseReference.child("Scouts").child(scout.getCum()).removeValue();
        Toast.makeText(MenuPrincipalScouter.this, "Se elimino correctamente a " + scout.getNombre(), Toast.LENGTH_SHORT).show();
        recreate();
    }

    ArrayList<Scout> llenarLista(){
        final ArrayList <Scout> scouts = new ArrayList<Scout>();
        databaseReference.child("Scouts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Scout scout = snapshot.getValue(Scout.class);
                    //Toast.makeText(MenuPrincipalScouter.this, "Se agregó a " + scout.nombre, Toast.LENGTH_SHORT).show();
                    scouts.add(scout);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MenuPrincipalScouter.this, "Error en la conexión", Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(MenuPrincipalScouter.this, "Conexion con la base de datos establecida", Toast.LENGTH_SHORT).show();
        return scouts;
    }

    private void construidRecycler(){
        listaScouts = llenarLista();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerListaScouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        ApadadorListaScout adaptador = new ApadadorListaScout(listaScouts);
        adaptador.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                CharSequence[] opciones = new CharSequence[]{
                        "Editar",
                        "Eliminar",
                        "Agregar insignia",
                        "Liminar insignia"
                };
                AlertDialog.Builder menu = new AlertDialog.Builder(MenuPrincipalScouter.this);
                menu.setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:{
                                /*Toast.makeText(MenuPrincipalScouter.this,
                                        "Se quiere EDITAR a: " + listaScouts.get(recyclerView.getChildAdapterPosition(v)).getNombre(), Toast.LENGTH_SHORT).show();*/
                                Intent detail = new Intent(MenuPrincipalScouter.this, DetalleAgregarScout.class);
                                detail.putExtra("isEdit", true);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("scout", listaScouts.get(recyclerView.getChildAdapterPosition(v)));
                                detail.putExtras(bundle);
                                startActivity(detail);
                                break;
                            }
                            case 1:{
                                eliminarScout(listaScouts.get(recyclerView.getChildAdapterPosition(v)));
                                break;
                            }
                            case 2:{
                                Intent detail = new Intent(MenuPrincipalScouter.this, AgregarInsigniaScout.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("scout", listaScouts.get(recyclerView.getChildAdapterPosition(v)));
                                detail.putExtras(bundle);
                                startActivity(detail);
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
