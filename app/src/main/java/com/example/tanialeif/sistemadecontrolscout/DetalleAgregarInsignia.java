package com.example.tanialeif.sistemadecontrolscout;

import android.content.Context;
import android.support.constraint.solver.widgets.ChainHead;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Insignia;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class DetalleAgregarInsignia extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Spinner spnNivel;
    FloatingActionButton btnAgregarInsignia;
    EditText txtNombre, txtDetalle;
    CheckBox txbCorporalidad, txbCreatividad, txbCaracter, txbAfectividad, txbSociabilidad, txbEspiritualidad;

    Boolean isEdit;

    final Context self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_agregar_insignia);
        inicializarFirebase();

        spnNivel = (Spinner) findViewById(R.id.spnNivelInsignia);
        btnAgregarInsignia = (FloatingActionButton) findViewById(R.id.btnAgregarInsignia);
        txtNombre = (EditText) findViewById(R.id.txtNombreInsignia);
        txtDetalle = (EditText) findViewById(R.id.txtDescripcionInsignia);
        txbCorporalidad = (CheckBox) findViewById(R.id.txbInsigniaCorporalidad);
        txbCreatividad = (CheckBox) findViewById(R.id.txbInsigniaCreatividad);
        txbCaracter = (CheckBox) findViewById(R.id.txbInsigniaCaracter);
        txbAfectividad = (CheckBox) findViewById(R.id.txbInsigniaAfectividad);
        txbSociabilidad = (CheckBox) findViewById(R.id.txbInsigniaSociabilidad);
        txbEspiritualidad = (CheckBox) findViewById(R.id.txbInsigniaEspiritualidad);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNivel.setAdapter(adapter);
        spnNivel.setOnItemSelectedListener(this);

        btnAgregarInsignia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Insignia insignia = new Insignia();
                insignia.uid = UUID.randomUUID().toString();
                insignia.nombre = txtNombre.getText().toString();
                insignia.descripcion = txtDetalle.getText().toString();
                insignia.corporalidad = txbCorporalidad.isChecked();
                insignia.creatividad = txbCreatividad.isChecked();
                insignia.caracter = txbCaracter.isChecked();
                insignia.afectividad = txbAfectividad.isChecked();
                insignia.sociabilidad = txbSociabilidad.isChecked();
                insignia.espiritualidad = txbEspiritualidad.isChecked();
                insignia.nivel = spnNivel.getSelectedItem().toString();

                if(validar()){
                    insertarInsignia(insignia);
                }
                else {
                    Toast.makeText(DetalleAgregarInsignia.this, "Datos faltantes", Toast.LENGTH_SHORT).show();
                }
            }
        });

        isEdit = getIntent().getBooleanExtra("isEdit",false);
        if(isEdit){
            llenarDatos();
        }
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private boolean validar(){
        boolean correcto = true;
        if(txtNombre.getText().toString().trim().length() == 0 || txtDetalle.getText().toString().trim().length() == 0){
            correcto = false;
        }
        return correcto;
    }

    private void llenarDatos(){
        Bundle bundle = getIntent().getExtras();
        Insignia insignia = (Insignia) bundle.getSerializable("insignia");
        txtNombre.setText(insignia.nombre);
        txtDetalle.setText(insignia.descripcion);
        txbCorporalidad.setChecked(insignia.corporalidad);
        txbCreatividad.setChecked(insignia.creatividad);
        txbCaracter.setChecked(insignia.caracter);
        txbEspiritualidad.setChecked(insignia.espiritualidad);
        txbAfectividad.setChecked(insignia.espiritualidad);
        txbSociabilidad.setChecked(insignia.sociabilidad);
    }

    private void insertarInsignia(Insignia insignia){
        databaseReference.child("Insignias").child(insignia.uid).setValue(insignia);
        txtNombre.setText("");
        txtDetalle.setText("");
        txbCorporalidad.setChecked(false);
        txbCreatividad.setChecked(false);
        txbCaracter.setChecked(false);
        txbAfectividad.setChecked(false);
        txbSociabilidad.setChecked(false);
        txbEspiritualidad.setChecked(false);
        Toast.makeText(this, "Se " + (isEdit ? "actualizó" : "agregó") +" a " + insignia.nombre + " exitosamente.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
