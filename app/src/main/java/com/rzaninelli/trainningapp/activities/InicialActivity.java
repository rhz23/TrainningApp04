package com.rzaninelli.trainningapp.activities;

import static com.rzaninelli.trainningapp.activities.CadastroTreinoActivity.ALTERAR_TREINO;
import static com.rzaninelli.trainningapp.activities.CadastroTreinoActivity.TREINO;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    private View viewSelecionada;

    private ActionMode actionMode;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            MenuInflater inflate = mode.getMenuInflater();
            inflate.inflate(R.menu.inicial_item_selecionado, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem menuItem) {

            switch (menuItem.getItemId()) {

                case R.id.menuItemEditar:
                    alterarTreino();
                    mode.finish();
                    return true;

                case R.id.menuItemExcluir:
                    excluirPessoa();
                    mode.finish();
                    return true;

                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            if (viewSelecionada != null) {
                viewSelecionada.setBackgroundColor(Color.TRANSPARENT);
            }

            actionMode = null;
            viewSelecionada = null;

            listViewTreinoss.setEnabled(true);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        listViewTreinoss = findViewById(R.id.listViewTreinos);

        listViewTreinoss.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        posicaoSelecionada = position;
                        alterarTreino();
                    }
                });

        listViewTreinoss.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewTreinoss.setOnItemLongClickListener((parent, view, position, id) -> {
            if (actionMode != null) {
                return false;
            }

            posicaoSelecionada = position;

            view.setBackgroundColor(Color.LTGRAY);

            viewSelecionada = view;

            listViewTreinoss.setEnabled(false);

            actionMode = startSupportActionMode(mActionModeCallback);

            return true;

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

    private void excluirPessoa() {
        treinos.remove(posicaoSelecionada);
        treinoAdapter.notifyDataSetChanged();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicial_opcoes, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuItemAdicionar:
                novoTreino(this.getCurrentFocus());
            return true;

            case R.id.menuItemAbout:
                sobre(this.getCurrentFocus());
            return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}