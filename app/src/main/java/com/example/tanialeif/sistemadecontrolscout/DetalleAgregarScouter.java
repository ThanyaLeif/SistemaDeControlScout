package com.example.tanialeif.sistemadecontrolscout;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.Models.Scouter;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetalleAgregarScouter extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Spinner spnNivel;
    FloatingActionButton btnAgregarScouter;
    EditText txtNombre, txtApellidoPat, txtApellidoMat, txtDireccion, txtTelefono, txtCum, txtContrasenia;

    Boolean isEdit;

    final Context self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_agregar_scouter);
        inicializarFirebase();

        spnNivel = (Spinner)findViewById(R.id.spnNivelScouter);
        btnAgregarScouter = (FloatingActionButton) findViewById(R.id.btnAgregarScouter);
        txtNombre = (EditText)findViewById(R.id.txtNombreScouter);
        txtApellidoPat = (EditText)findViewById(R.id.txtApellidoPatScouter);
        txtApellidoMat = (EditText)findViewById(R.id.txtApellidoMatScouter);
        txtDireccion = (EditText)findViewById(R.id.txtDireccionScouter);
        txtTelefono = (EditText)findViewById(R.id.txtTelefonoScouter);
        txtCum = (EditText)findViewById(R.id.txtCumScouter);
        txtContrasenia = (EditText)findViewById(R.id.txtContraseniaScouter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNivel.setAdapter(adapter);
        spnNivel.setOnItemSelectedListener(this);

        btnAgregarScouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scouter scouter = new Scouter();
                scouter.nombre = txtNombre.getText().toString();
                scouter.apellidoPat = txtApellidoPat.getText().toString();
                scouter.apellidoMat = txtApellidoMat.getText().toString();
                scouter.direccion = txtDireccion.getText().toString();
                scouter.telefono = txtTelefono.getText().toString();
                scouter.cum = txtCum.getText().toString();
                scouter.contrasenia = txtContrasenia.getText().toString();
                scouter.nivel = spnNivel.getSelectedItem().toString();

                if(validar()) {
                    insertarScouter(scouter);
                }
                else {
                    Toast.makeText(DetalleAgregarScouter.this, "Datos faltantes", Toast.LENGTH_SHORT).show();
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
        if( txtNombre.getText().toString().trim().length() == 0
                || txtApellidoPat.getText().toString().trim().length() == 0
                || txtApellidoMat.getText().toString().trim().length() == 0
                || txtDireccion.getText().toString().trim().length() == 0
                || txtTelefono.getText().toString().trim().length() == 0
                || txtCum.getText().toString().trim().length() == 0
                || txtContrasenia.getText().toString().trim().length() == 0){
            correcto = false;
        }
        return correcto;
    }

    private void llenarDatos(){
        Bundle bundle = getIntent().getExtras();
        Scouter scouter = (Scouter) bundle.getSerializable("scouter");
        txtNombre.setText(scouter.getNombre());
        txtApellidoPat.setText(scouter.getApellidoPat());
        txtApellidoMat.setText(scouter.getApellidoMat());
        txtDireccion.setText(scouter.getDireccion());
        txtTelefono.setText(scouter.getTelefono());
        txtCum.setText(scouter.getCUM());
        txtContrasenia.setText(scouter.getContrasenia());
        txtCum.setEnabled(false);
    }

    private void insertarScouter(Scouter scouter){
        databaseReference.child("Scouters").child(scouter.cum).setValue(scouter);
        txtNombre.setText("");
        txtApellidoPat.setText("");
        txtApellidoMat.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        txtCum.setText("");
        txtContrasenia.setText("");
        Toast.makeText(this, "Se " + (isEdit ? "actualizó" : "agregó") +" a " + scouter.nombre + " exitosamente.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
