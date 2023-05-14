package com.rzaninelli.trainningapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.entities.Treino;

import java.util.List;

public class TreinoAdapter extends BaseAdapter {

    Context context;
    List<Treino> treinos;

    private static class TreinoHolder {
        public TextView textViewNomeTreino;
        public TextView textViewObjetivoTreino;
    }

    public TreinoAdapter(Context context, List<Treino> treinos) {
        this.context = context;
        this.treinos = treinos;
    }

    @Override
    public int getCount() {
        return treinos.size();
    }

    @Override
    public Object getItem(int i) {
        return treinos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        TreinoHolder treinoHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_treino, viewGroup, false);

            treinoHolder = new TreinoHolder();

            treinoHolder.textViewNomeTreino = view.findViewById(R.id.textViewNomeTreino);
            treinoHolder.textViewObjetivoTreino = view.findViewById(R.id.textViewObjetivoTreino);

            view.setTag(treinoHolder);
        }
        else {
            treinoHolder = (TreinoHolder) view.getTag();
        }

        treinoHolder.textViewNomeTreino.setText(treinos.get(i).getNome());
        treinoHolder.textViewObjetivoTreino.setText(treinos.get(i).getObjetivo().toString());

        return view;
    }
}
