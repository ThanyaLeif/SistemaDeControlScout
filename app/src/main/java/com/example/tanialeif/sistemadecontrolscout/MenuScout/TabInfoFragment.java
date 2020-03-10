package com.example.tanialeif.sistemadecontrolscout.MenuScout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.R;

public class TabInfoFragment extends Fragment {

    View view;

    //Aqui vamos a poner todos los componentes de la vista
    TextView txtTitle;
    String title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_info_scout, container, false);

        //Aqui vamos a inicializar todos los componentes de la vista
        txtTitle = (TextView)view.findViewById(R.id.txtInfoScout);

        if(getArguments() != null){
            title = getArguments().getString("title");
            txtTitle.setText(title);
        }
        else{
            Toast.makeText(view.getContext(), "No se envieron datos", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
}
