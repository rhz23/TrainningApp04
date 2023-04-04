package com.rzaninelli.trainningapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.Treino;

import java.util.ArrayList;

public class InicialActivity extends AppCompatActivity {

    private Button buttonNovoTreino, buttonSobre;
    private ListView listViewTreinoss;
    private ArrayList<Treino> treinos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);

        buttonNovoTreino = findViewById(R.id.buttonNovoTreino);
        buttonSobre = findViewById(R.id.buttonSobre);
    }
}