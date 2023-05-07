package com.rzaninelli.trainningapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.entities.GrupoMuscular;

import java.util.List;

public class GrupoMuscularAdapter extends BaseAdapter {

    Context context;
    List<GrupoMuscular> gruposMusculares;

    private static class GrupoMuscularHolder {
        public ImageView imageViewGrupoMuscularImagem;
        public TextView textViewGrupoMuscularNome;
    }

    public GrupoMuscularAdapter(Context context, List<GrupoMuscular> gruposMusculares) {
        this.context = context;
        this.gruposMusculares = gruposMusculares;
    }

    @Override
    public int getCount() {
        return gruposMusculares.size();
    }

    @Override
    public Object getItem(int i) {
        return gruposMusculares.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        GrupoMuscularAdapter.GrupoMuscularHolder holder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_grupo_muscular, viewGroup, false);

            holder = new GrupoMuscularAdapter.GrupoMuscularHolder();

            holder.imageViewGrupoMuscularImagem = view.findViewById(R.id.imageViewGrupoMuscularImagem);
            holder.textViewGrupoMuscularNome = view.findViewById(R.id.textViewGrupoMuscularNome);

            view.setTag(holder);
        }
        else {
            holder = (GrupoMuscularAdapter.GrupoMuscularHolder) view.getTag();
        }

        holder.imageViewGrupoMuscularImagem.setImageDrawable(gruposMusculares.get(i).getImagemGrupoMuscular());
        holder.textViewGrupoMuscularNome.setText(gruposMusculares.get(i).getNome());

        return view;
    }
}
