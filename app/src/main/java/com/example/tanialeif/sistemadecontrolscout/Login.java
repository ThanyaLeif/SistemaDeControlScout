package com.example.tanialeif.sistemadecontrolscout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Login extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones_usuarios, android.R.layout.simple_spinner_item);
        spnUsuarios.setAdapter(adapter);*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CustomItemsUsers items = (CustomItemsUsers) parent.getSelectedItem();
        Toast.makeText(this, items.getSpinnerText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
