package com.rzaninelli.trainningapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.ExercicioAdapter;
import com.rzaninelli.trainningapp.adapters.GrupoMuscularAdapter;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.GrupoMuscular;
import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;
import com.rzaninelli.trainningapp.entities.enums.Objetivo;
import com.rzaninelli.trainningapp.persistence.TreinosDatabase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CadastroTreinoActivity extends AppCompatActivity {

    public static final String MODO = "MODO";
    public static final String TREINO = "TREINO";
    public static final String EXERCICIO_SELECIONADO = "EXERCICIO_SELECIONADO";
    public static final String POSICAO_EXERCICIO = "POSICAO_EXERCICIO";
    public static final int NOVO_TREINO = 1;
    public static final int ALTERAR_TREINO = 2;
    private static final int REQUEST_CODE_EXERCICIO_SELECIONADO = 3;

    private TextView editTextTreinoNome, editTextRepeticoes;

    private CheckBox checkBoxSegunda, checkBoxTerca, checkBoxQuarta, checkBoxQuinta, checkBoxSexta, checkBoxSabado, checkBoxDomingo;

    private RadioGroup radioGroupObjetivo;
    private RadioButton radioButtonForca, radioButtonResistencia, radioButtonHipertrofia;

    private Spinner spinnerGrupoMuscular;
    private ArrayList<GrupoMuscular> gruposMusculares;

    private ListView listViewExerciciosSelecionados;

    private List<Exercicio> exerciciosSelecionados = new ArrayList<>();

    private Button buttonAdd;

    private int modo;

    private Treino treinoOriginal;
//    private Treino novoTreino;

    private ExercicioAdapter exercicioAdapter;

    public static void novoTreino(AppCompatActivity activity) {

        Intent intent = new Intent(activity, CadastroTreinoActivity.class);
        intent.putExtra(MODO, NOVO_TREINO);
        activity.startActivityForResult(intent, NOVO_TREINO);

    }

    public static void alterarTreino(AppCompatActivity activity, Treino treino) {

        Intent intent = new Intent(activity, CadastroTreinoActivity.class);
        intent.putExtra(MODO, ALTERAR_TREINO);
        intent.putExtra(TREINO, treino);

        activity.startActivityForResult(intent, ALTERAR_TREINO);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_treino);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        editTextTreinoNome = findViewById(R.id.editTextTreinoNome);
        editTextRepeticoes = findViewById(R.id.editTextRepeticoes);

        checkBoxSegunda = findViewById(R.id.checkBoxSegunda);
        checkBoxTerca = findViewById(R.id.checkBoxTerca);
        checkBoxQuarta = findViewById(R.id.checkBoxQuarta);
        checkBoxQuinta = findViewById(R.id.checkBoxQuinta);
        checkBoxSexta = findViewById(R.id.checkBoxSexta);
        checkBoxSabado = findViewById(R.id.checkBoxSabado);
        checkBoxDomingo = findViewById(R.id.checkBoxDomingo);

        radioGroupObjetivo = findViewById(R.id.radioGroupObjetivo);
        radioButtonForca = findViewById(R.id.radioButtonForça);
        radioButtonResistencia = findViewById(R.id.radioButtonResistencia);
        radioButtonHipertrofia = findViewById(R.id.radioButtonHipertrofia);

        spinnerGrupoMuscular = findViewById(R.id.spinnerGrupoMuscular);

        popularListaGrupoMuscular();

        listViewExerciciosSelecionados = findViewById(R.id.listViewExerciciosSelecionados);

        buttonAdd = findViewById(R.id.buttonAdd);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            modo = bundle.getInt(MODO, NOVO_TREINO);

            if (modo == NOVO_TREINO){
                treinoOriginal = new Treino();
                setTitle(getString(R.string.criar_novo_treino));
            }
            if (modo == ALTERAR_TREINO) {
                treinoOriginal = (Treino) bundle.getSerializable(TREINO);
                preencherCampos(treinoOriginal);
                setTitle(getString(R.string.alterar_treino));
            }
        }
        editTextTreinoNome.requestFocus();

        registerForContextMenu(listViewExerciciosSelecionados);
    }

    private void preencherCampos(Treino treinoOriginal) {

        editTextTreinoNome.setText(treinoOriginal.getNome());
        treinoOriginal.setNome(treinoOriginal.getNome());

        editTextRepeticoes.setText(treinoOriginal.getRepeticoes());
        treinoOriginal.setRepeticoes(treinoOriginal.getRepeticoes());

        if (treinoOriginal.getDiasDeTreino().contains(DiasDaSemana.SEGUNDA))
            checkBoxSegunda.setChecked(true);
        if (treinoOriginal.getDiasDeTreino().contains(DiasDaSemana.TERCA))
            checkBoxTerca.setChecked(true);
        if (treinoOriginal.getDiasDeTreino().contains(DiasDaSemana.QUARTA))
            checkBoxQuarta.setChecked(true);
        if (treinoOriginal.getDiasDeTreino().contains(DiasDaSemana.QUINTA))
            checkBoxQuinta.setChecked(true);
        if (treinoOriginal.getDiasDeTreino().contains(DiasDaSemana.SEXTA))
            checkBoxSexta.setChecked(true);
        if (treinoOriginal.getDiasDeTreino().contains(DiasDaSemana.SABADO))
            checkBoxSabado.setChecked(true);
        if (treinoOriginal.getDiasDeTreino().contains(DiasDaSemana.DOMINGO))
            checkBoxDomingo.setChecked(true);

        treinoOriginal.setDiasDeTreino(treinoOriginal.getDiasDeTreino());

        if (treinoOriginal.getObjetivo().getIndice() == 0)
            radioButtonForca.setChecked(true);
        if (treinoOriginal.getObjetivo().getIndice() == 1)
            radioButtonResistencia.setChecked(true);
        if (treinoOriginal.getObjetivo().getIndice() == 2)
            radioButtonHipertrofia.setChecked(true);

        treinoOriginal.setObjetivo(treinoOriginal.getObjetivo());

        treinoOriginal.setExerciciosDoTreino(treinoOriginal.getExerciciosDoTreino());


        spinnerGrupoMuscular.setSelection(treinoOriginal.getGrupoMuscularID());
        treinoOriginal.setGrupoMuscularID(treinoOriginal.getGrupoMuscularID());

        popularListaExerciciosSelecionados(treinoOriginal);
    }

    private void popularListaExerciciosSelecionados(Treino treino) {

        TypedArray imagensExercicios = getResources().obtainTypedArray(R.array.imagens_exercicios);
        List<Exercicio> exerciciosCarregados = treino.getExerciciosDoTreino();
        for (Exercicio x: exerciciosCarregados) {
            x.setImagemExercicio(imagensExercicios.getDrawable(x.getImagemExercicioRef()));
        }
        exercicioAdapter = new ExercicioAdapter(this, exerciciosCarregados);
        listViewExerciciosSelecionados.setAdapter(exercicioAdapter);
        exercicioAdapter.notifyDataSetChanged();

    }

    public void addExercicio(View view) {

        Intent intent = new Intent(this, ListExerciciosActivity.class);
        startActivityForResult(intent, REQUEST_CODE_EXERCICIO_SELECIONADO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EXERCICIO_SELECIONADO && resultCode == RESULT_OK) {

            if (data != null && data.hasExtra(EXERCICIO_SELECIONADO)) {

                Exercicio exercicioSelecionado = (Exercicio) data.getSerializableExtra(EXERCICIO_SELECIONADO);
                treinoOriginal.addExercicioDoTreino(exercicioSelecionado);
                popularListaExerciciosSelecionados(treinoOriginal);
            }
        }
    }

    public void limparCampos() {

        treinoOriginal = new Treino();

        editTextTreinoNome.setText(null);
        editTextRepeticoes.setText(null);
        checkBoxSegunda.setChecked(false);
        checkBoxTerca.setChecked(false);
        checkBoxQuarta.setChecked(false);
        checkBoxQuinta.setChecked(false);
        checkBoxSexta.setChecked(false);
        checkBoxSabado.setChecked(false);
        checkBoxDomingo.setChecked(false);

        radioGroupObjetivo.clearCheck();

        popularListaExerciciosSelecionados(treinoOriginal);

        Toast.makeText(this, R.string.campos_cadastro_foram_limpos, Toast.LENGTH_LONG).show();
    }

    public void salvarCadastro() {
        

        String mensagem = getString(R.string.os_campos_precisam_ser_preenchidos);
        boolean cadastroIncompleto = false;
        TextView primeiroCampoFaltante = null;

        if (editTextTreinoNome.getText().toString().trim().isEmpty() || editTextTreinoNome == null){
            mensagem += "\n" + getString(R.string.nome_treino);
            cadastroIncompleto = true;
            primeiroCampoFaltante = editTextTreinoNome;
        }
        if (editTextRepeticoes.getText().toString().trim().isEmpty()){
            mensagem += "\n" + getString(R.string.repeticoes);
            cadastroIncompleto = true;
            if (primeiroCampoFaltante == null)
                primeiroCampoFaltante = editTextRepeticoes;
        }
        if (!checkBoxSegunda.isChecked() && !checkBoxTerca.isChecked() && !checkBoxQuarta.isChecked() && !checkBoxQuinta.isChecked() && !checkBoxSexta.isChecked() && !checkBoxSabado.isChecked() && !checkBoxDomingo.isChecked()){
            mensagem += "\n" + getString(R.string.pelo_menos_um_dia_semana_selecionado);
            cadastroIncompleto = true;
        }
        if (radioGroupObjetivo.getCheckedRadioButtonId() != R.id.radioButtonForça && radioGroupObjetivo.getCheckedRadioButtonId() != R.id.radioButtonHipertrofia && radioGroupObjetivo.getCheckedRadioButtonId() != R.id.radioButtonResistencia) {
            mensagem += "\n" + getString(R.string.objetivo);
            cadastroIncompleto = true;
        }


        if (cadastroIncompleto) {
            Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
            if (primeiroCampoFaltante != null)
                primeiroCampoFaltante.requestFocus();
            return;
        }
        else {
            
            treinoOriginal.setNome(editTextTreinoNome.getText().toString());
            
            treinoOriginal.setRepeticoes(editTextRepeticoes.getText().toString());

            treinoOriginal.setDiasDeTreino(new HashSet<>());

            if (checkBoxSegunda.isChecked())
                treinoOriginal.addDiaDaSemana(DiasDaSemana.SEGUNDA);
            if (checkBoxTerca.isChecked())
                treinoOriginal.addDiaDaSemana(DiasDaSemana.TERCA);
            if (checkBoxQuarta.isChecked())
                treinoOriginal.addDiaDaSemana(DiasDaSemana.QUARTA);
            if (checkBoxQuinta.isChecked())
                treinoOriginal.addDiaDaSemana(DiasDaSemana.QUINTA);
            if (checkBoxSexta.isChecked())
                treinoOriginal.addDiaDaSemana(DiasDaSemana.SEXTA);
            if (checkBoxSabado.isChecked())
                treinoOriginal.addDiaDaSemana(DiasDaSemana.SABADO);
            if (checkBoxDomingo.isChecked())
                treinoOriginal.addDiaDaSemana(DiasDaSemana.DOMINGO);

            int objetivoInt = radioGroupObjetivo.getCheckedRadioButtonId();
            switch (objetivoInt) {
                case R.id.radioButtonForça:
                    treinoOriginal.setObjetivo(Objetivo.FORCA);
                    break;
                case R.id.radioButtonHipertrofia:
                    treinoOriginal.setObjetivo(Objetivo.HIPERTROFIA);
                    break;
                case R.id.radioButtonResistencia:
                    treinoOriginal.setObjetivo(Objetivo.RESISTENCIA);
                    break;
            }

            treinoOriginal.setGrupoMuscularID(spinnerGrupoMuscular.getSelectedItemPosition());

            AsyncTask.execute(() -> {
                TreinosDatabase database = TreinosDatabase.getDatabase(CadastroTreinoActivity.this);

                if (modo == ALTERAR_TREINO) {
//                    treinoOriginal = novoTreino;
                    database.beginTransaction();
                    database.treinoDao().updateTreinoWithExercicioAndDiasDaSemana(treinoOriginal);
                    database.setTransactionSuccessful();
                    database.endTransaction();
                }
                else {
                    database.beginTransaction();

                    database.treinoDao().insertTreinoWithExerciciosAndDiasDaSemana(treinoOriginal);

                    database.setTransactionSuccessful();
                    database.endTransaction();
                }

            });

            Intent intent = new Intent();
            intent.putExtra(TREINO, treinoOriginal);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    public void cancelar() {
        setResult(Activity.RESULT_CANCELED);
        finish();
    }

    @Override
    public void onBackPressed() {
        cancelar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro_treino_opcoes, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menuItemSalvar:
                salvarCadastro();
                return true;

            case R.id.menuItemLimpar:
                limparCampos();
                return true;

            case android.R.id.home:
                cancelar();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        getMenuInflater().inflate(R.menu.exercicios_selecionados_menu_contexto, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {

            case R.id.menuItemRemover:
                excluir(info.position);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }

    private void excluir(int position) {

        treinoOriginal.getExerciciosDoTreino().remove(position);

        exercicioAdapter.notifyDataSetChanged();
    }

    private void popularListaGrupoMuscular() {

        AsyncTask.execute(() -> {
            TreinosDatabase database = TreinosDatabase.getDatabase(CadastroTreinoActivity.this);

            gruposMusculares = (ArrayList<GrupoMuscular>) database.grupoMuscularDao().queryAll();
            popularSpinnerGrupoMuscular();

            CadastroTreinoActivity.this.runOnUiThread(() -> {
                GrupoMuscularAdapter grupoMuscularAdapter = new GrupoMuscularAdapter(this, gruposMusculares);

                spinnerGrupoMuscular.setAdapter(grupoMuscularAdapter);
            });
        });
    }

    private void popularSpinnerGrupoMuscular() {
        String[] nomes = getResources().getStringArray(R.array.nomes_grupo_muscular);
        TypedArray imagemGrupoMuscular = getResources().obtainTypedArray(R.array.imagens_grupo_muscular);

        ArrayList<GrupoMuscular> gruposMusculares1 = new ArrayList();

        for (GrupoMuscular grupoMuscular: gruposMusculares) {
            int cont = grupoMuscular.getId()-1;

            grupoMuscular.setNome(nomes[cont]);
            grupoMuscular.setImagemGrupoMuscular(imagemGrupoMuscular.getDrawable(cont));
        }

        GrupoMuscularAdapter grupoMuscularAdapter = new GrupoMuscularAdapter(this, gruposMusculares1);
        spinnerGrupoMuscular.setAdapter(grupoMuscularAdapter);
    }

}