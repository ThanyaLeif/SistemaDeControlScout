package com.example.tanialeif.sistemadecontrolscout;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuPrincipalAdmin extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;

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
}
