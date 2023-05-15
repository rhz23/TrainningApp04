package com.rzaninelli.trainningapp.entities.auxiliares;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.utils.DiasDaSemanaConverter;

@Entity(tableName = "treino_diasDaSemana",
        primaryKeys = {"treinoId", "diasDaSemana"},
        foreignKeys = {
            @ForeignKey(entity = Treino.class, parentColumns = "id", childColumns = "treinoId", onDelete = ForeignKey.CASCADE),
        }
)
public class TreinoDiasDaSemana {

    @ColumnInfo(name = "treinoId")
    private long treinoId;

    @NonNull
    @TypeConverters(DiasDaSemanaConverter.class)
    private String diasDaSemana;

    public TreinoDiasDaSemana() {
    }

    public long getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(long treinoId) {
        this.treinoId = treinoId;
    }

    public String getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(String diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }
}
