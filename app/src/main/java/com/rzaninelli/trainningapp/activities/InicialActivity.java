package com.rzaninelli.trainningapp.activities;

import static com.rzaninelli.trainningapp.activities.CadastroTreinoActivity.ALTERAR_TREINO;
import static com.rzaninelli.trainningapp.activities.CadastroTreinoActivity.TREINO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.TreinoAdapter;
import com.rzaninelli.trainningapp.entities.Treino;

import java.util.ArrayList;

public class InicialActivity extends AppCompatActivity {

    private Button buttonAdicionarTreino, buttonSobre;
    private ListView listViewTreinoss;
    private ArrayList<Treino> treinos;
    private TreinoAdapter treinoAdapter;

    private int posicaoSelecionada = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        buttonAdicionarTreino = findViewById(R.id.buttonAdicionarTreino);
        buttonSobre = findViewById(R.id.buttonSobre);

        listViewTreinoss = findViewById(R.id.listViewTreinos);

        listViewTreinoss.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        posicaoSelecionada = position;
                        alterarTreino();
                    }
                });

        listViewTreinoss.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                        posicaoSelecionada = position;
                        alterarTreino();
                        return true;
                    }
                });
        popularListaTreino();
    }

    private void popularListaTreino() {
        treinos = new ArrayList<>();
        treinoAdapter = new TreinoAdapter(this, treinos);
        listViewTreinoss.setAdapter(treinoAdapter);
    }

    private void alterarTreino() {
        Treino treino = treinos.get(posicaoSelecionada);
        CadastroTreinoActivity.alterarTreino(this, treino);
    }

    public void novoTreino(View view) {
        CadastroTreinoActivity.novoTreino(this);
    }

    public void sobre(View view) {

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (data != null && data.hasExtra(TREINO)) {

                Treino treinoRetornado = (Treino) data.getSerializableExtra(TREINO);

                if (requestCode == ALTERAR_TREINO) {
                    Treino treino = treinos.get(posicaoSelecionada);

                    atualizarDadosTreinoSelecionado(treino, treinoRetornado);

                    posicaoSelecionada = -1;
                } else {
                    treinos.add(treinoRetornado);
                }
                treinoAdapter.notifyDataSetChanged();
            }
        }
    }

    private void atualizarDadosTreinoSelecionado(Treino treino, Treino treinoRetornado) {
        treino.setNome(treinoRetornado.getNome());
        treino.setDiasDeTreino(treinoRetornado.getDiasDeTreino());
        treino.setExerciciosDoTreino(treinoRetornado.getExerciciosDoTreino());
        treino.setRepeticoes(treinoRetornado.getRepeticoes());
        treino.setObjetivos(treinoRetornado.getObjetivos());
    }
}