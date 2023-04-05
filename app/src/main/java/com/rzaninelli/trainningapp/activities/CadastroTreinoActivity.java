package com.rzaninelli.trainningapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.ExercicioAdapter;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CadastroTreinoActivity extends AppCompatActivity {

    public static final String MODO = "MODO";
    public static final String TREINO = "TREINO";
    public static final int NOVO_TREINO = 1;
    public static final int ALTERAR_TREINO = 2;

    private TextView editTextTreinoNome, editTextRepeticoes;

    private CheckBox checkBoxSegunda, checkBoxTerca, checkBoxQuarta, checkBoxQuinta, checkBoxSexta, checkBoxSabado, checkBoxDomingo;

    private RadioGroup radioGroupObjetivo;

    private ListView listViewExerciciosSelecionados;

    private List<Exercicio> exerciciosSelecionados;

    private Button buttonAdd, buttonSalvarTreino, buttonCancelarTreino;

    private int modo;

    private Treino treinoOriginal;

    public static void novoTreino(AppCompatActivity activity) {

        Intent intent = new Intent(activity, CadastroTreinoActivity.class);
        intent.putExtra(MODO, NOVO_TREINO);
        activity.startActivityForResult(intent, NOVO_TREINO);

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

        listViewExerciciosSelecionados = findViewById(R.id.listViewExerciciosSelecionados);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSalvarTreino = findViewById(R.id.buttonSalvarTreino);
        buttonCancelarTreino = findViewById(R.id.buttonCancelarTreino);

        if (exerciciosSelecionados != null && !exerciciosSelecionados.isEmpty()){
            popularListaExerciciosSelecionados();
        }

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            modo = bundle.getInt(MODO, NOVO_TREINO);

            if (modo == NOVO_TREINO){
                setTitle(getString(R.string.criar_novo_treino));
            }
            if (modo == ALTERAR_TREINO) {
                treinoOriginal = (Treino) bundle.getSerializable(TREINO);
                preencherCampos(treinoOriginal);
                setTitle(getString(R.string.alterar_treino));
            }

        }

    }

    private void preencherCampos(Treino treinoOriginal) {

        editTextTreinoNome.setText(treinoOriginal.getNome());
        editTextRepeticoes.setText(treinoOriginal.getRepeticoes());
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

        if (treinoOriginal.getObjetivos().equals(R.id.radioButtonForça))
            radioGroupObjetivo.check(R.id.radioButtonForça);
        if (treinoOriginal.getObjetivos().equals(R.id.radioButtonHipertrofia))
            radioGroupObjetivo.check(R.id.radioButtonHipertrofia);
        if (treinoOriginal.getObjetivos().equals(R.id.radioButtonResistencia))
            radioGroupObjetivo.check(R.id.radioButtonResistencia);


        exerciciosSelecionados = treinoOriginal.getExerciciosDoTreino();
    }

    private void popularListaExerciciosSelecionados() {

        ExercicioAdapter exercicioAdapter = new ExercicioAdapter(this, exerciciosSelecionados);
        listViewExerciciosSelecionados.setAdapter(exercicioAdapter);

    }

    public void addTreino(View view) {

    }

    public void limparCampos(View view) {

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

        exerciciosSelecionados = new ArrayList<>();

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
        }
        else {
            Treino novoTreino = new Treino();
            
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
            
            //// TODO: 04/04/2023 - verificar melhor forma de fazer essa parte 
            novoTreino.setObjetivos(radioGroupObjetivo.getCheckedRadioButtonId());
            
            Toast.makeText(this, R.string.cadastro_efetuado_com_sucesso, Toast.LENGTH_LONG).show();
        }

    }

    public void cancelar(View view) {
        finish();
    }

}