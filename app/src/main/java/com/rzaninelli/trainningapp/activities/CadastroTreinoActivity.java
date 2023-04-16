package com.rzaninelli.trainningapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.ExercicioAdapter;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;
import com.rzaninelli.trainningapp.entities.enums.Objetivos;

import java.util.ArrayList;
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

    private ListView listViewExerciciosSelecionados;

    private List<Exercicio> exerciciosSelecionados = new ArrayList<>();

    private Button buttonAdd, buttonSalvarTreino, buttonCancelarTreino;

    private int modo;

    private Treino treinoOriginal;
    private Treino novoTreino;

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

        listViewExerciciosSelecionados = findViewById(R.id.listViewExerciciosSelecionados);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSalvarTreino = findViewById(R.id.buttonSalvarTreino);
        buttonCancelarTreino = findViewById(R.id.buttonCancelarTreino);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            modo = bundle.getInt(MODO, NOVO_TREINO);
            novoTreino = new Treino();

            if (modo == NOVO_TREINO){
                setTitle(getString(R.string.criar_novo_treino));
            }
            if (modo == ALTERAR_TREINO) {
                treinoOriginal = (Treino) bundle.getSerializable(TREINO);
                preencherCampos(treinoOriginal);
                setTitle(getString(R.string.alterar_treino));
            }
        }
        editTextTreinoNome.requestFocus();
    }

    private void preencherCampos(Treino treinoOriginal) {

        editTextTreinoNome.setText(treinoOriginal.getNome());
        novoTreino.setNome(treinoOriginal.getNome());

        editTextRepeticoes.setText(treinoOriginal.getRepeticoes());
        novoTreino.setRepeticoes(treinoOriginal.getRepeticoes());

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

        novoTreino.setDiasDeTreino(treinoOriginal.getDiasDeTreino());

        if (treinoOriginal.getObjetivos().indice() == 0)
            radioButtonForca.setChecked(true);
        if (treinoOriginal.getObjetivos().indice() == 1)
            radioButtonResistencia.setChecked(true);
        if (treinoOriginal.getObjetivos().indice() == 2)
            radioButtonHipertrofia.setChecked(true);

        novoTreino.setObjetivos(treinoOriginal.getObjetivos());

        novoTreino.setExerciciosDoTreino(treinoOriginal.getExerciciosDoTreino());

        popularListaExerciciosSelecionados(novoTreino);
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
                novoTreino.addExercicioDoTreino(exercicioSelecionado);
                popularListaExerciciosSelecionados(novoTreino);
            }
        }
    }

    public void limparCampos(View view) {

        novoTreino = new Treino();

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

        popularListaExerciciosSelecionados(novoTreino);

//        exerciciosSelecionados = new ArrayList<>();
//        exercicioAdapter.notifyDataSetChanged();

        Toast.makeText(this, R.string.campos_cadastro_foram_limpos, Toast.LENGTH_LONG).show();
    }

    public void salvarCadastro(View view) {
        

        String mensagem = getString(R.string.os_campos_precisam_ser_preenchidos);
        boolean cadastroIncompleto = false;
        TextView primeiroCampoFaltante = null;

        if (editTextTreinoNome.getText().toString().trim().isEmpty()){
            mensagem += "\n" + getString(R.string.nome);
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
            
            novoTreino.setNome(editTextTreinoNome.getText().toString());
            
            novoTreino.setRepeticoes(editTextRepeticoes.getText().toString());

            if (checkBoxSegunda.isChecked())
                novoTreino.addDiaDaSemana(DiasDaSemana.SEGUNDA);
            if (checkBoxTerca.isChecked())
                novoTreino.addDiaDaSemana(DiasDaSemana.TERCA);
            if (checkBoxQuarta.isChecked())
                novoTreino.addDiaDaSemana(DiasDaSemana.QUARTA);
            if (checkBoxQuinta.isChecked())
                novoTreino.addDiaDaSemana(DiasDaSemana.QUINTA);
            if (checkBoxSexta.isChecked())
                novoTreino.addDiaDaSemana(DiasDaSemana.SEXTA);
            if (checkBoxSabado.isChecked())
                novoTreino.addDiaDaSemana(DiasDaSemana.SABADO);
            if (checkBoxDomingo.isChecked())
                novoTreino.addDiaDaSemana(DiasDaSemana.DOMINGO);

            int objetivoInt = radioGroupObjetivo.getCheckedRadioButtonId();
            switch (objetivoInt) {
                case R.id.radioButtonForça:
                    novoTreino.setObjetivos(Objetivos.FORCA);
                    break;
                case R.id.radioButtonHipertrofia:
                    novoTreino.setObjetivos(Objetivos.HIPERTROFIA);
                    break;
                case R.id.radioButtonResistencia:
                    novoTreino.setObjetivos(Objetivos.RESISTENCIA);
                    break;
            }

            if (modo == ALTERAR_TREINO) {
                treinoOriginal = novoTreino;
            }

            Intent intent = new Intent();
            intent.putExtra(TREINO, novoTreino);

            setResult(Activity.RESULT_OK, intent);
            finish();
        }
    }

    public void cancelar(View view) {
        finish();
    }

    @Override
    public void onBackPressed() {
        setResult(Activity.RESULT_CANCELED);
        finish();
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
                salvarCadastro(this.getCurrentFocus());
                return true;

            case R.id.menuItemLimpar:
                limparCampos(this.getCurrentFocus());
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}