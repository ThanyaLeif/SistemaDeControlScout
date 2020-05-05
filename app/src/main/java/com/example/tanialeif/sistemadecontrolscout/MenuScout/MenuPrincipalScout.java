package com.example.tanialeif.sistemadecontrolscout.MenuScout;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.Instant;

public class MenuPrincipalScout extends AppCompatActivity {

    private TabLayout tabLayout;
    private Scout scout;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal_scout);

        Bundle bundle = getIntent().getExtras();
        scout = (Scout) bundle.getSerializable("scout");

        inicializarFirebase();

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPagerScout);
        loadViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.tabsScout);
        tabLayout.setupWithViewPager(viewPager);
        tabNames();
    }

    private void inicializarFirebase(){
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void tabNames(){
        tabLayout.getTabAt(0).setText("Información");
        tabLayout.getTabAt(1).setText("Insignias");
    }

    private void loadViewPager(ViewPager viewPager){
        ViewPagerAdapterScout adapterScout = new ViewPagerAdapterScout(getSupportFragmentManager());
        adapterScout.addFragment(newInstanceInfo("info"));
        adapterScout.addFragment(newInstanceInsig("insignias"));
        viewPager.setAdapter(adapterScout);
    }

    private TabInfoFragment newInstanceInfo(String title){
        Bundle bundle = new Bundle();
        bundle.putSerializable("scout", scout);
        TabInfoFragment tabInfoFragment = new TabInfoFragment();
        tabInfoFragment.setArguments(bundle);

        return tabInfoFragment;
    }

    private TabInsigniasFragment newInstanceInsig(String title){
        Bundle bundle = new Bundle();
        bundle.putSerializable("scout", scout);
        TabInsigniasFragment tabInsigniasFragment = new TabInsigniasFragment();
        tabInsigniasFragment.setArguments(bundle);

        return tabInsigniasFragment;
    }

}
