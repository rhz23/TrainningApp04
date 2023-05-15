package com.rzaninelli.trainningapp.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DiasDaSemanaDao {

    @Query("SELECT diasDaSemana FROM treino_diasDaSemana WHERE treinoId = :treinoId")
    List<String> getDiasDaSemanaForTreino(int treinoId);
}
