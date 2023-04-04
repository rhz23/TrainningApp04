package com.rzaninelli.trainningapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.ExercicioAdapter;
import com.rzaninelli.trainningapp.entities.Exercicio;

import java.util.ArrayList;

public class CadastroTreinoActivity extends AppCompatActivity {

    private TextView editTextTreinoNome, editTextRepeticoes;

    private CheckBox checkBoxSegunda, checkBoxTerca, checkBoxQuarta, checkBoxQuinta, checkBoxSexta, checkBoxSabado, checkBoxDomingo;

    private RadioGroup radioGroupObjetivo;

    private ListView listViewExerciciosSelecionados;

    private ArrayList<Exercicio> exerciciosSelecionados;


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

        if (!exerciciosSelecionados.isEmpty()){
            popularListaExerciciosSelecionados();
        }

    }

    private void popularListaExerciciosSelecionados() {

        ExercicioAdapter exercicioAdapter = new ExercicioAdapter(this, exerciciosSelecionados);
        listViewExerciciosSelecionados.setAdapter(exercicioAdapter);

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
            Toast.makeText(this, R.string.cadastro_efetuado_com_sucesso, Toast.LENGTH_LONG).show();
        }

    }

}