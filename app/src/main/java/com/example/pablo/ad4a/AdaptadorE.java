package com.example.pablo.ad4a;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pablo on 20/01/2017.
 */

public class AdaptadorE extends RecyclerView.Adapter <AdaptadorE.ModulosViewHolder> {
    private List<EstudiantesC> listaEstudiantes;


    public AdaptadorE(List<EstudiantesC> estudiantes) {
        listaEstudiantes = estudiantes;

    }

    //Creamos el ViewHolder y creamos y iniciamos cada elemento del card
    public static class ModulosViewHolder extends RecyclerView.ViewHolder {
        public TextView estudiante;


        public ModulosViewHolder(View v) {
            super(v);
            estudiante = (TextView) v.findViewById(R.id.estudiante);


        }
    }


    @Override
    public int getItemCount() {

        return this.listaEstudiantes.size();
    }

    @Override
    //le inflamos al holder el xml del diseño de cada card
    public ModulosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element, parent, false);
        return new ModulosViewHolder(v);
    }


    @Override
    //Enlazamos la información que queremos mostrar a un holder
    public void onBindViewHolder(ModulosViewHolder holder, final int position) {
        holder.estudiante.setText(listaEstudiantes.get(position).getEstudiante());




    }
}
