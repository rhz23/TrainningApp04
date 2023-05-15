package com.rzaninelli.trainningapp.entities.auxiliares;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.Treino;

@Entity(tableName = "treino_exercicio",
        primaryKeys = {"treinoId", "exercicioId"},
        foreignKeys = {
                @ForeignKey(entity = Treino.class, parentColumns = "id", childColumns = "treinoId", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Exercicio.class, parentColumns = "id", childColumns = "exercicioId", onDelete = ForeignKey.CASCADE)
        })
public class TreinoExercicio {

    private long treinoId;
    @ColumnInfo(index = true)
    private long exercicioId;

    public TreinoExercicio() {
    }

    public long getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(long treinoId) {
        this.treinoId = treinoId;
    }

    public long getExercicioId() {
        return exercicioId;
    }

    public void setExercicioId(long exercicioId) {
        this.exercicioId = exercicioId;
    }
}
