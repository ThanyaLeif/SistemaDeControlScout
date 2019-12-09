package com.example.tanialeif.sistemadecontrolscout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DetalleAgregarScout extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Spinner spnNivel;
    Button btnFechaNac;
    TextView lblFechaNac;
    FloatingActionButton btnAgregarScout;
    EditText txtNombre, txtApellidoPat, txtApellidoMat, txtDireccion, txtTelefono, txtCum, txtContrasenia;

    Boolean isEdit;

    final Context self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_scout);
        inicializarFirebase();

        spnNivel = (Spinner)findViewById(R.id.spnNivelScout);
        btnFechaNac = (Button)findViewById(R.id.btnFechaNacScout);
        btnAgregarScout = (FloatingActionButton) findViewById(R.id.btnAgregarScout);
        lblFechaNac = (TextView)findViewById(R.id.lblFechaNacScout);
        txtNombre = (EditText)findViewById(R.id.txtNombreScout);
        txtApellidoPat = (EditText)findViewById(R.id.txtApellidoPatScout);
        txtApellidoMat = (EditText)findViewById(R.id.txtApellidoMatScout);
        txtDireccion = (EditText)findViewById(R.id.txtDireccionScout);
        txtTelefono = (EditText)findViewById(R.id.txtTelefonoScout);
        txtCum = (EditText)findViewById(R.id.txtCumScout);
        txtContrasenia = (EditText)findViewById(R.id.txtContraseniaScout);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.niveles, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNivel.setAdapter(adapter);
        spnNivel.setOnItemSelectedListener(this);

        btnFechaNac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int dia = calendar.get(Calendar.DAY_OF_MONTH);
                int mes = calendar.get(Calendar.MONTH);
                int anio = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(self, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        lblFechaNac.setText((dayOfMonth < 10 ? "0" : "") + dayOfMonth + "/" + (month + 1 < 10 ? "0" : "") + (month + 1) + "/" + year);
                    }
                }, anio, mes, dia
                );
                datePickerDialog.show();
            }
        });

        btnAgregarScout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scout scout = new Scout();
                scout.nombre = txtNombre.getText().toString();
                scout.apellidoPat = txtApellidoPat.getText().toString();
                scout.apellidoMat = txtApellidoMat.getText().toString();
                scout.direccion = txtDireccion.getText().toString();
                scout.telefono = txtTelefono.getText().toString();
                scout.fechaNac = lblFechaNac.getText().toString();
                scout.cum = txtCum.getText().toString();
                scout.contrasenia = txtContrasenia.getText().toString();
                scout.nivel = spnNivel.getSelectedItem().toString();

                if(validar()) {
                    insertarScout(scout);
                }
                else {
                    Toast.makeText(DetalleAgregarScout.this, "Datos faltantes", Toast.LENGTH_SHORT).show();
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
                || lblFechaNac.getText().toString().trim().length() == 0
                || txtCum.getText().toString().trim().length() == 0
                || txtContrasenia.getText().toString().trim().length() == 0){
            correcto = false;
        }
        return correcto;
    }

    private void llenarDatos(){
        Bundle bundle = getIntent().getExtras();
        Scout scout = (Scout) bundle.getSerializable("scout");
        txtNombre.setText(scout.getNombre());
        txtApellidoPat.setText(scout.getApellidoPat());
        txtApellidoMat.setText(scout.getApellidoMat());
        txtDireccion.setText(scout.getDireccion());
        txtTelefono.setText(scout.getTelefono());
        lblFechaNac.setText(scout.getFechaNac());
        txtCum.setText(scout.getCum());
        txtContrasenia.setText(scout.getContrasenia());
        txtCum.setEnabled(false);
    }

    private void insertarScout(Scout scout){
        databaseReference.child("Scouts").child(scout.cum).setValue(scout);
        txtNombre.setText("");
        txtApellidoPat.setText("");
        txtApellidoMat.setText("");
        txtDireccion.setText("");
        txtTelefono.setText("");
        lblFechaNac.setText("--/--/--");
        txtCum.setText("");
        txtContrasenia.setText("");
        Toast.makeText(this, "Se " + (isEdit ? "actualizó" : "agregó") +" a " + scout.nombre + " exitosamente.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
