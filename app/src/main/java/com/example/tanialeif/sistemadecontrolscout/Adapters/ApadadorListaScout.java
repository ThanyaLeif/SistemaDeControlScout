package com.example.tanialeif.sistemadecontrolscout.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanialeif.sistemadecontrolscout.Models.Scout;
import com.example.tanialeif.sistemadecontrolscout.R;

import java.util.ArrayList;

public class ApadadorListaScout
        extends RecyclerView.Adapter<ApadadorListaScout.MyViewHolder>
        implements View.OnLongClickListener{

    ArrayList <Scout> listaScouts;
    View.OnLongClickListener listener;

    public ApadadorListaScout(ArrayList <Scout> listaScouts){
        this.listaScouts = listaScouts;
    }

    @NonNull
    @Override
    public ApadadorListaScout.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_scout, null, false);
        view.setOnLongClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ApadadorListaScout.MyViewHolder myViewHolder, int i) {
        myViewHolder.asignarDatos(listaScouts.get(i));
    }

    @Override
    public int getItemCount() {
        return listaScouts.size();
    }

    public void setOnLongClickListener(View.OnLongClickListener listener){
        this.listener = listener;
    }

    @Override
    public boolean onLongClick(View v) {
        if(listener != null){
            listener.onLongClick(v);
        }
        return false;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtCum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombreScoutItem);
            txtCum = (TextView) itemView.findViewById(R.id.txtCumScoutItem);
        }


        public void asignarDatos(Scout scout) {
            txtNombre.setText(scout.nombre + " " + scout.apellidoPat + " " + scout.apellidoMat);
            txtCum.setText(scout.cum);
        }
    }
}
