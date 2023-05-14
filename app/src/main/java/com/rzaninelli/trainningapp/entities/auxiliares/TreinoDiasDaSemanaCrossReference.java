package com.rzaninelli.trainningapp.entities.auxiliares;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;

@Entity(tableName = "treino_diasDaSemana",
        primaryKeys = {"treinoId", "diaDaSemana"},
        foreignKeys = {
            @ForeignKey(entity = Treino.class,
                parentColumns = "id",
                childColumns = "treino"),
        }
)
public class TreinoDiasDaSemanaCrossReference {
    private int treinoId;
    private DiasDaSemana diasDaSemana;

    public TreinoDiasDaSemanaCrossReference() {
    }

    public int getTreinoId() {
        return treinoId;
    }

    public void setTreinoId(int treinoId) {
        this.treinoId = treinoId;
    }

    public DiasDaSemana getDiasDaSemana() {
        return diasDaSemana;
    }

    public void setDiasDaSemana(DiasDaSemana diasDaSemana) {
        this.diasDaSemana = diasDaSemana;
    }
}
