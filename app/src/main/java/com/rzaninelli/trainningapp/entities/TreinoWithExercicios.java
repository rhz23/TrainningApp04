package com.rzaninelli.trainningapp.entities;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import com.rzaninelli.trainningapp.entities.auxiliares.TreinoExercicio;

import java.util.List;

public class TreinoWithExercicios {

    @Embedded
    public Treino treino;

    @Relation(parentColumn = "id", entityColumn = "id")
    public List<Exercicio> exercicios;
}
