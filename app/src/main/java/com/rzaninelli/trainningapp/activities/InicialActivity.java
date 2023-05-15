package com.rzaninelli.trainningapp.activities;

import static com.rzaninelli.trainningapp.activities.CadastroTreinoActivity.ALTERAR_TREINO;
import static com.rzaninelli.trainningapp.activities.CadastroTreinoActivity.TREINO;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.TreinoAdapter;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.entities.TreinoWithExercicios;
import com.rzaninelli.trainningapp.entities.auxiliares.TreinoDiasDaSemana;
import com.rzaninelli.trainningapp.entities.auxiliares.TreinoExercicio;
import com.rzaninelli.trainningapp.persistence.TreinosDatabase;
import com.rzaninelli.trainningapp.utils.DiasDaSemanaConverter;
import com.rzaninelli.trainningapp.utils.UtilsGUI;

import java.util.ArrayList;
import java.util.List;

public class InicialActivity extends AppCompatActivity {

    private TreinosDatabase treinosDatabase;

    private static final String ARQUIVO = "com.rzaninelli.trainingapp.MODE_PREFERENCES";

    private static final String DARK_MODE = "DARK_MODE";

    private boolean darkMode = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private ListView listViewTreinoss;
    private ArrayList<Treino> treinos;
    private TreinoAdapter treinoAdapter;

    private int posicaoSelecionada = -1;
    private Treino treinoSelecionado;

    private View viewSelecionada;

    private ActionMode actionMode;

    private Switch switchButton;

    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            sharedPreferences = getSharedPreferences(ARQUIVO, MODE_PRIVATE);
            editor = getSharedPreferences(ARQUIVO, MODE_PRIVATE).edit();

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


//            AdapterView.AdapterContextMenuInfo info;
//            info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
//            Treino treino = (Treino) listViewTreinoss.getItemAtPosition(info.position);

            switch (menuItem.getItemId()) {

                case R.id.menuItemEditar:
                    alterarTreino(treinoSelecionado);
                    mode.finish();
                    return true;

                case R.id.menuItemExcluir:
                    excluirTreino(treinoSelecionado);
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
        //// TODO: 15/05/2023 atention!!!!
        registerForContextMenu(listViewTreinoss);

        listViewTreinoss.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                        posicaoSelecionada = position;
                        Treino treino = treinos.get(posicaoSelecionada);
                        alterarTreino(treino);
                    }
                });

        listViewTreinoss.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listViewTreinoss.setOnItemLongClickListener((parent, view, position, id) -> {
            if (actionMode != null) {
                return false;
            }

            posicaoSelecionada = position;
            treinoSelecionado = treinos.get(position);

            view.setBackgroundColor(Color.LTGRAY);

            viewSelecionada = view;

            listViewTreinoss.setEnabled(false);

            actionMode = startSupportActionMode(mActionModeCallback);

            return true;

        });

        popularListaTreino();

        lerPreferenciaDarkMode();

    }

    private void popularListaTreino() {
//        treinos = new ArrayList<>();
//        treinoAdapter = new TreinoAdapter(this, treinos);
//        listViewTreinoss.setAdapter(treinoAdapter);

        AsyncTask.execute(() -> {
            treinosDatabase = TreinosDatabase.getDatabase(InicialActivity.this);

            treinos = (ArrayList<Treino>) treinosDatabase.treinoDao().getTreinos();


            for (Treino treino: treinos) {

                List<TreinoDiasDaSemana> treinoDiasDaSemanas = treinosDatabase.treinoDiasDaSemanaDao().getDiasDaSemanaForTreino(treino.getId());
                for (TreinoDiasDaSemana treinoDiaDaSemana: treinoDiasDaSemanas) {
                    treino.addDiaDaSemana(DiasDaSemanaConverter.fromDiasDaSemanaString(treinoDiaDaSemana.getDiasDaSemana()));
                }

                List<TreinoExercicio> treinoExercicios = treinosDatabase.treinoExercicioDao().getExerciciosForTreino(treino.getId());
                for (TreinoExercicio treinoExercicio: treinoExercicios) {
                    Exercicio exercicio = treinosDatabase.exercicioDao().queryForId((int)treinoExercicio.getExercicioId());
                    treino.addExercicioDoTreino(exercicio);
                }
                System.out.println("aqui");
            }

//            treinos = (ArrayList<Treino>) treinosDatabase.treinoDao().getTreinos();

            InicialActivity.this.runOnUiThread(() -> {
                treinoAdapter = new TreinoAdapter(this, treinos);

                listViewTreinoss.setAdapter(treinoAdapter);
            });
        });

    }


    private void alterarTreino(Treino treino) {

//        treinosDatabase = TreinosDatabase.getDatabase(this);

//        Treino treino = treinosDatabase.treinoDao().get(posicaoSelecionada);

        CadastroTreinoActivity.alterarTreino(this, treino);
    }

    public void novoTreino(View view) {
        CadastroTreinoActivity.novoTreino(this);
    }

    private void excluirTreino(Treino treino) {

        String mensagem = getString(R.string.confirma_delete) + "\n" + treino.getNome();

//        String mensagem = "Deseja realmente apagar treino?" + "\n" + treino.getNome();

        DialogInterface.OnClickListener listener = (dialog, which) -> {

            switch (which) {

                case DialogInterface.BUTTON_POSITIVE:

                    AsyncTask.execute(() -> {
                        treinosDatabase.treinoDao().delete(treino);
                    });
                    treinos.remove(posicaoSelecionada);
                    System.out.println("aqui");
                    treinoAdapter.notifyDataSetChanged();

//                    treinosDatabase.treinoDao().delete(treino);

                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    break;

            }

        };

        UtilsGUI.confirmaAcao(this, mensagem, listener);
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
        treino.setObjetivo(treinoRetornado.getObjetivo());
        treino.setGrupoMuscularID(treinoRetornado.getGrupoMuscularID());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.inicial_opcoes, menu);
        MenuItem item  = menu.findItem(R.id.switchButton);
        switchButton = (Switch) item.getActionView();
        switchButton.setChecked(darkMode);
        switchButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        if (switchButton.isChecked())
                            darkMode = true;
                        else
                            darkMode = false;
                        ativarDarkMode();
//                        getApplicationContext().setTheme(R.style.Theme_TrainningApp);
                        salvarPreferenciaDarkMode();
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                        overridePendingTransition(0,0);
                    }
                }
        );
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

    private void lerPreferenciaDarkMode() {

        sharedPreferences = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);
        darkMode = sharedPreferences.getBoolean(DARK_MODE, darkMode);

        ativarDarkMode();
    }

    private void salvarPreferenciaDarkMode() {
        sharedPreferences = getSharedPreferences(ARQUIVO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(DARK_MODE, darkMode);

        editor.commit();

    }

    private void ativarDarkMode() {
        if (darkMode == false) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    //// TODO: 15/05/2023 -- trying
    
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//
//        getMenuInflater().inflate(R.menu.exercicios_selecionados_menu_contexto, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        int position = info.position;
//
//        // Handle the selected menu item based on its ID
//        switch (item.getItemId()) {
//            case R.id.menuItemEditar:
//                // Edit item
//                return true;
//
//            case R.id.menuItemExcluir:
//                // Delete item
//                return true;
//
//            default:
//                return super.onContextItemSelected(item);
//        }
//    }


}

