package com.example.tanialeif.sistemadecontrolscout.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanialeif.sistemadecontrolscout.Models.Insignia;
import com.example.tanialeif.sistemadecontrolscout.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdaptadorListaInsignias
        extends RecyclerView.Adapter <AdaptadorListaInsignias.MyViewHolder>
        implements View.OnLongClickListener {

    ArrayList <Insignia> listaInsignias;
    View.OnLongClickListener listener;

    public AdaptadorListaInsignias(ArrayList<Insignia> listaInsignias) {
        this.listaInsignias = listaInsignias;
    }

    @NonNull
    @Override
    public AdaptadorListaInsignias.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list_insignia, null, false);
        view.setOnLongClickListener(this);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorListaInsignias.MyViewHolder myViewHolder, int i) {
        myViewHolder.asignarDatos(listaInsignias.get(i));
    }

    @Override
    public int getItemCount() {
        return listaInsignias.size();
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
        TextView txtNombre, txtDescripcion;
        ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = (TextView) itemView.findViewById(R.id.txtNombreItemInsignia);
            txtDescripcion = (TextView) itemView.findViewById(R.id.txtItemDescripcionInsignia);
            imageView = (ImageView) itemView.findViewById(R.id.imgItemInsignia);
        }
        public void asignarDatos(Insignia insignia) {
            txtNombre.setText(insignia.nombre);
            txtDescripcion.setText(insignia.descripcion);
            Picasso.get().load(insignia.urlImagen).into(imageView);
        }


    }

}
