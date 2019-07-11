package com.example.tanialeif.sistemadecontrolscout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    Spinner spnUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        spnUsuarios = (Spinner)findViewById(R.id.spnUsuarios);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_usuarios, android.R.layout.simple_spinner_item);
        spnUsuarios.setAdapter(adapter);
    }
}
