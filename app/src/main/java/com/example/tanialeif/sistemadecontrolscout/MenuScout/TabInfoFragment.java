package com.example.tanialeif.sistemadecontrolscout.MenuScout;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.R;

public class TabInfoFragment extends Fragment {

    View view;
    Scout scout;

    EditText txtNombre, txtApellidoPat, txtApellidoMat, txtDireccion, txtTelefono, txtCum, txtNivel;
    TextView txtFechaNac;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_info_scout, container, false);

        txtNombre = (EditText)view.findViewById(R.id.txtInfoScoutNombre);
        txtApellidoPat = (EditText)view.findViewById(R.id.txtInfoScoutApellidoPat);
        txtApellidoMat   = (EditText)view.findViewById(R.id.txtInfoScoutApellidoMat);
        txtDireccion = (EditText)view.findViewById(R.id.txtInfoScoutDireccion);
        txtTelefono = (EditText)view.findViewById(R.id.txtInfoScoutTelefono);
        txtFechaNac = (TextView)view.findViewById(R.id.txtInfoScoutFechaNac);
        txtCum = (EditText)view.findViewById(R.id.txtInfoScoutCum);
        txtNivel = (EditText)view.findViewById(R.id.txtInfoScoutNivel);


        scout = (Scout) getArguments().getSerializable("scout");
        llenarDatos();
        //Toast.makeText(view.getContext(), scout.getNombre(), Toast.LENGTH_SHORT).show();


        return view;
    }

    private void llenarDatos(){
        txtNombre.setText(scout.getNombre());
        txtApellidoPat.setText(scout.getApellidoPat());
        txtApellidoMat.setText(scout.getApellidoMat());
        txtDireccion.setText(scout.getDireccion());
        txtTelefono.setText(scout.getTelefono());
        txtFechaNac.setText(scout.getFechaNac());
        txtCum.setText(scout.getCum());
        txtNivel.setText(scout.getNivel());
    }
}
