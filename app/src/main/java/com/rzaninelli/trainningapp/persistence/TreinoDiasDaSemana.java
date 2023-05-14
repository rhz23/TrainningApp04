package com.rzaninelli.trainningapp.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;

import java.util.List;

@Dao
public interface TreinoDiasDaSemana {

    @Insert
    void insert(DiasDaSemana diasDaSemana);

    @Delete
    void delete(DiasDaSemana diasDaSemana);

//    @Query("SELECT * FROM treino_diasDaSemana WHERE treinoId = :treinoId")
//    List<TreinoDiasDaSemana> getDiasDaSemanaForTreino(int treinoId);
}
