package com.rzaninelli.trainningapp.activities;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.ExercicioAdapter;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.enums.Dificuldade;
import com.rzaninelli.trainningapp.entities.enums.EquipamentoUtilizado;
import com.rzaninelli.trainningapp.entities.enums.GrupoMuscular;

import java.util.ArrayList;

public class ListExerciciosActivity extends AppCompatActivity {

    private ListView listViewExercicios;
    private ArrayList<Exercicio> exercicios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exercicios);

        listViewExercicios = findViewById(R.id.listViewExercicios);

        listViewExercicios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Exercicio exercicioSelecionado = (Exercicio) listViewExercicios.getItemAtPosition(position);

                Toast.makeText(getApplicationContext(), getString(R.string.o_exercicio) + exercicioSelecionado.getNome() + getString(R.string.foi_clicado), Toast.LENGTH_LONG).show();
            }
        });
        
        popularListaExercicios();
    }

    private void popularListaExercicios() {

        String[] nomes = getResources().getStringArray(R.array.nomes_exercicios);
        int[] gruposMusculares_posicao = getResources().getIntArray(R.array.grupos_musculares_exercicios);
        int[] equipamentosUtilizados_posicao = getResources().getIntArray(R.array.equipamentos_utilizados_exercicios);
        int[] dificuldades_posicao = getResources().getIntArray(R.array.dificuldades_exercicios);
        TypedArray imagensExercicios = getResources().obtainTypedArray(R.array.imagens_exercicios);

        GrupoMuscular[] gruposMusculares = GrupoMuscular.values();
        EquipamentoUtilizado[] equipamentoUtilizados = EquipamentoUtilizado.values();
        Dificuldade[] dificuldades = Dificuldade.values();

        exercicios = new ArrayList<>();

        Exercicio exercicio;

        for (int cont = 0; cont < nomes.length; cont++) {

            exercicio = new Exercicio();
            exercicio.setNome(nomes[cont]);
            exercicio.setGrupoMuscular(gruposMusculares[gruposMusculares_posicao[cont]]);
            exercicio.setEquipamentoUtilizado(equipamentoUtilizados[equipamentosUtilizados_posicao[cont]]);
            exercicio.setDificuldade(dificuldades[dificuldades_posicao[cont]]);
            exercicio.setImagemExercicio(imagensExercicios.getDrawable(cont));

            exercicios.add(exercicio);
        }
        ExercicioAdapter exercicioAdapter = new ExercicioAdapter(this, exercicios);
        listViewExercicios.setAdapter(exercicioAdapter);
    }
}