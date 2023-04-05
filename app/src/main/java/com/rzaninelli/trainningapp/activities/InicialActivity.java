package com.rzaninelli.trainningapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.entities.Treino;

import java.util.ArrayList;

public class InicialActivity extends AppCompatActivity {

    private Button buttonAdicionarTreino, buttonSobre;
    private ListView listViewTreinoss;
    private ArrayList<Treino> treinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        buttonAdicionarTreino = findViewById(R.id.buttonAdicionarTreino);
        buttonSobre = findViewById(R.id.buttonSobre);
    }

    public void novoTreino(View view) {

        Intent intent = new Intent(this, CadastroTreinoActivity.class);
        startActivity(intent);
    }

    public void sobre(View view) {

        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}