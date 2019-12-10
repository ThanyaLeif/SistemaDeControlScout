package com.example.tanialeif.sistemadecontrolscout.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanialeif.sistemadecontrolscout.Models.Scouter;
import com.example.tanialeif.sistemadecontrolscout.R;

import java.util.ArrayList;

public class AdaptadorListaScouter
        extends RecyclerView.Adapter<AdaptadorListaScouter.MyViewHolder>
        implements View.OnLongClickListener{

        ArrayList <Scouter> listaScouters;
        View.OnLongClickListener listener;

        public AdaptadorListaScouter(ArrayList<Scouter> listaScouters){
            this.listaScouters = listaScouters;
        }


    @NonNull
    @Override
    public AdaptadorListaScouter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_scouter, null, false);
        view.setOnLongClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListaScouter.MyViewHolder myViewHolder, int i) {
        myViewHolder.asignarDatos(listaScouters.get(i));
    }

    @Override
    public int getItemCount() {
        return listaScouters.size();
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

    public class MyViewHolder extends RecyclerView.ViewHolder{
            TextView txtNombre, txtCum;

            public MyViewHolder(@NonNull View itemView){
                super(itemView);
                txtNombre = (TextView)itemView.findViewById(R.id.txtNombreScouterItem);
                txtCum = (TextView) itemView.findViewById(R.id.txtCumScouterItem);
            }

            public void asignarDatos(Scouter scouter){
                txtNombre.setText(scouter.nombre + " " + scouter.apellidoPat + " " + scouter.apellidoMat);
                txtCum.setText(scouter.cum);
            }
    }
}
