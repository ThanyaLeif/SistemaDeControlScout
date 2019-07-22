package com.example.tanialeif.sistemadecontrolscout;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.tanialeif.sistemadecontrolscout.TabsAdmin.TabLineas;
import com.example.tanialeif.sistemadecontrolscout.TabsAdmin.TabObjetivos;
import com.example.tanialeif.sistemadecontrolscout.TabsAdmin.TabSecciones;

public class MenuAjustesAdmin extends AppCompatActivity implements TabLineas.OnFragmentInteractionListener,
        TabObjetivos.OnFragmentInteractionListener, TabSecciones.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_ajustes_admin);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayoutAdmin);
        tabLayout.addTab(tabLayout.newTab().setText("Lineas"));
        tabLayout.addTab(tabLayout.newTab().setText("Objetivos"));
        tabLayout.addTab(tabLayout.newTab().setText("Secciones"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pagerAdmin);
        final TabAdapterAdmin adapter = new TabAdapterAdmin(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
