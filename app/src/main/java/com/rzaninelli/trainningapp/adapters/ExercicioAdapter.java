package com.rzaninelli.trainningapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.TipoFisico;

import java.util.List;

public class ExercicioAdapter extends BaseAdapter {

    Context context;
    List<Exercicio> exercicios;

    private static class ExercicioHolder {
        public ImageView imageViewExercicioImagem;
        public TextView textViewNomeExercicio;
        public TextView textViewGrupoMuscular;
        public TextView textViewEquipamentoUtilizado;
        public TextView textViewDificuldade;
    }

    public ExercicioAdapter(Context context, List<Exercicio> exercicios) {
        this.context = context;
        this.exercicios = exercicios;
    }

    @Override
    public int getCount() {
        return exercicios.size();
    }

    @Override
    public Object getItem(int i) {
        return exercicios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ExercicioHolder exercicioHolder;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_exercicio_listview, viewGroup, false);

            exercicioHolder = new ExercicioHolder();

            exercicioHolder.imageViewExercicioImagem = view.findViewById(R.id.imageViewExercicioImagem);
            exercicioHolder.textViewNomeExercicio = view.findViewById(R.id.textViewNomeExercicio);
            exercicioHolder.textViewGrupoMuscular = view.findViewById(R.id.textViewGrupoMuscular);
            exercicioHolder.textViewEquipamentoUtilizado = view.findViewById(R.id.textViewEquipamentoUtilizado);
            exercicioHolder.textViewDificuldade = view.findViewById(R.id.textViewDificuldade);

            view.setTag(exercicioHolder);
        }
        else {
            exercicioHolder = (ExercicioHolder) view.getTag();
        }

        exercicioHolder.imageViewExercicioImagem.setImageDrawable(exercicios.get(i).getImagemExercicio());
        exercicioHolder.textViewNomeExercicio.setText(exercicios.get(i).getNome());
        exercicioHolder.textViewGrupoMuscular.setText(exercicios.get(i).getGrupoMuscular().name());
        exercicioHolder.textViewEquipamentoUtilizado.setText(exercicios.get(i).getEquipamentoUtilizado().name());
        exercicioHolder.textViewDificuldade.setText(exercicios.get(i).getDificuldade().name());

        return view;
    }
}
