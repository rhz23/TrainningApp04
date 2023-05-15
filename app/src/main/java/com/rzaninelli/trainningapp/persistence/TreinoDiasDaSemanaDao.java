package com.rzaninelli.trainningapp.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rzaninelli.trainningapp.entities.auxiliares.TreinoDiasDaSemana;
import com.rzaninelli.trainningapp.entities.auxiliares.TreinoExercicio;

import java.util.List;

@Dao
public interface TreinoDiasDaSemanaDao {

    @Insert
    void insert(TreinoDiasDaSemana treinoDiasDaSemana);

    @Delete
    void delete(TreinoDiasDaSemana treinoDiasDaSemana);

    @Query("SELECT * FROM treino_diasDaSemana WHERE treinoId = :treinoId")
    List<TreinoDiasDaSemana> getDiasDaSemanaForTreino(int treinoId);

}
