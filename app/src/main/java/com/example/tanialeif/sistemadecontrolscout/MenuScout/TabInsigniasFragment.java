package com.example.tanialeif.sistemadecontrolscout.MenuScout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Insignia;
import com.example.tanialeif.sistemadecontrolscout.Models.InsigniaScout;
import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TabInsigniasFragment extends Fragment {

    View view;
    Scout scout;

    ArrayList<Insignia> listaInsignias;


    //Aqui vamos a poner todos los componentes de la vista


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_insignias_scout, container, false);

        //Aqui vamos a inicializar todos los componentes de la vista


        return view;
    }


}
