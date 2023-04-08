 package com.rzaninelli.trainningapp.activities;

 import android.app.DatePickerDialog;
 import android.content.res.TypedArray;
 import android.os.Bundle;
 import android.support.v7.app.AppCompatActivity;
 import android.view.View;
 import android.widget.CheckBox;
 import android.widget.DatePicker;
 import android.widget.EditText;
 import android.widget.ImageView;
 import android.widget.RadioGroup;
 import android.widget.Spinner;
 import android.widget.TextView;
 import android.widget.Toast;


 import com.rzaninelli.trainningapp.R;
 import com.rzaninelli.trainningapp.adapters.TipoFisicoAdapter;
 import com.rzaninelli.trainningapp.entities.TipoFisico;

 import java.util.ArrayList;
 import java.util.Calendar;

 public class MainActivity extends AppCompatActivity {

     private TextView editTextTextPersonName, editTextNumberDecimalAltura, editTextNumberDecimalPeso;

     private RadioGroup radioGroupSexo;

     private Spinner spinnerTipoFisico;

     private CheckBox cbSaude, cbPerdaDePeso, cbGanhoMassaMuscular, cbGanhoForca, cbGanhoExplosao;

     private EditText dateTXT;
     private ImageView calendar;
     private int mDate, mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        editTextNumberDecimalAltura = findViewById(R.id.editTextNumberDecimalAltura);
        editTextNumberDecimalPeso = findViewById(R.id.editTextNumberDecimalPeso);

        radioGroupSexo = findViewById(R.id.radioGroupSexo);

        spinnerTipoFisico = findViewById(R.id.spinnerTipoFisico);

        popularSpinnerPaises();

        dateTXT = findViewById(R.id.date);
        calendar = findViewById(R.id.datepicker);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar Cal = Calendar.getInstance();
                mDate = Cal.get(Calendar.DATE);
                mMonth = Cal.get(Calendar.MONTH);
                mYear = Cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        month = month +1;
                        dateTXT.setText(date+"-"+month+"-"+year);
                    }
                }, mYear, mMonth, mDate);
                datePickerDialog.show();
            }
        });

        cbSaude = findViewById(R.id.checkBoxSaude);
        cbPerdaDePeso = findViewById(R.id.checkBoxPerdaDePeso);
        cbGanhoMassaMuscular = findViewById(R.id.checkBoxGanhoMassaMuscular);
        cbGanhoForca = findViewById(R.id.checkBoxGanhoForca);
        cbGanhoExplosao = findViewById(R.id.checkBoxGanhoExplosao);
    }

     private void popularSpinnerPaises() {
        String[] nomes = getResources().getStringArray(R.array.nomes_tipo_fisico);
        TypedArray imagemtiposFisicos = getResources().obtainTypedArray(R.array.imagens_tipo_fisico);

         ArrayList<TipoFisico> tiposFisicos = new ArrayList();

         for (int cont = 0; cont < nomes.length; cont++) {
             tiposFisicos.add(new TipoFisico(nomes[cont], imagemtiposFisicos.getDrawable(cont)));
         }

         TipoFisicoAdapter tipoFisicoAdapter = new TipoFisicoAdapter(this, tiposFisicos);
         spinnerTipoFisico.setAdapter(tipoFisicoAdapter);
     }

     public void limparCampos(View view) {
        editTextTextPersonName.setText(null);
        editTextNumberDecimalAltura.setText(null);
        editTextNumberDecimalPeso.setText(null);

        radioGroupSexo.clearCheck();

        dateTXT.setText(null);

         cbSaude.setChecked(false);
         cbPerdaDePeso.setChecked(false);
         cbGanhoMassaMuscular.setChecked(false);
         cbGanhoForca.setChecked(false);
         cbGanhoExplosao.setChecked(false);

         Toast.makeText(this, R.string.campos_cadastro_foram_limpos, Toast.LENGTH_LONG).show();
    }

    public void salvarCadastro(View view) {

        String mensagem = getString(R.string.os_campos_precisam_ser_preenchidos);
        boolean cadastroIncompleto = false;
        TextView primeiroCampoFaltante = null;

        if (editTextTextPersonName.getText().toString().trim().isEmpty()){
            mensagem += "\n" + getString(R.string.nome);
            cadastroIncompleto = true;
            primeiroCampoFaltante = editTextTextPersonName;
        }
        if (dateTXT.getText().toString().isEmpty()) {
            mensagem += "\n" + getString(R.string.data_nascimento);
            cadastroIncompleto = true;
            if (primeiroCampoFaltante == null)
                primeiroCampoFaltante = dateTXT;
        }
        if (editTextNumberDecimalAltura.getText().toString().isEmpty()) {
            mensagem += "\n" + getString(R.string.altura);
            cadastroIncompleto = true;
            if (primeiroCampoFaltante == null)
                primeiroCampoFaltante = editTextNumberDecimalAltura;
        }
        if (editTextNumberDecimalPeso.getText().toString().isEmpty()) {
            mensagem += "\n" + getString(R.string.peso);
            cadastroIncompleto = true;
            if (primeiroCampoFaltante == null)
                primeiroCampoFaltante = editTextNumberDecimalPeso;
        }
        if (radioGroupSexo.getCheckedRadioButtonId() != R.id.radioButtonFeminino && radioGroupSexo.getCheckedRadioButtonId() != R.id.radioButtonMasculino) {
            mensagem += "\n" + getString(R.string.sexo);
            cadastroIncompleto = true;
        }
        if (!cbSaude.isChecked() && !cbPerdaDePeso.isChecked() && !cbGanhoMassaMuscular.isChecked() && !cbGanhoForca.isChecked() && !cbGanhoExplosao.isChecked()){
            mensagem += "\n" + getString(R.string.pelo_menos_um_objetivo_selecionado);
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