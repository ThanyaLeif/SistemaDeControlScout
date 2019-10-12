package com.example.tanialeif.sistemadecontrolscout;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class DetalleScout extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    Spinner spnNivel;
    Button btnFechaNac;
    TextView lblFechaNac;

    final Context self = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_scout);
        inicializarFirebase();

        spnNivel = (Spinner)findViewById(R.id.spnNivelScout);
        btnFechaNac = (Button)findViewById(R.id.btnFechaNacScout);
        lblFechaNac = (TextView)findViewById(R.id.lblFechaNacScout);

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
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void insertarScout(Scout scout){
        databaseReference.child("Scouts").child(scout.CUM).setValue(scout);
        Toast.makeText(this, "Se agregó a " + scout.nombre, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
