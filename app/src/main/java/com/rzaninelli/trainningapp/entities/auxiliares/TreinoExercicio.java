package com.rzaninelli.trainningapp.entities.auxiliares;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.Treino;

@Entity(tableName = "treino_exercicio",
        primaryKeys = {"treinoId", "exercicioId"},
        foreignKeys = {
                @ForeignKey(entity = Treino.class, parentColumns = "id", childColumns = "treinoId"),
                @ForeignKey(entity = Exercicio.class, parentColumns = "id", childColumns = "exercicioId")
        })
public class TreinoExercicio {

    private int treinoId;
    private int exercicioId;

    public TreinoExercicio() {
    }

    public int getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(int treinoId) {
        this.treinoId = treinoId;
    }

    public int getExercicioId() {
        return exercicioId;
    }

    public void setExercicioId(int exercicioId) {
        this.exercicioId = exercicioId;
    }
}
