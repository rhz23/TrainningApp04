package com.rzaninelli.trainningapp.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.auxiliares.TreinoExercicio;

import java.util.List;

@Dao
public interface TreinoExercicioDao {

    @Insert
    void insert(TreinoExercicio treinoExercicio);

    @Delete
    void delete(TreinoExercicio treinoExercicio);

//    @Query("SELECT * FROM treino_exercicio WHERE treinoId = :treinoId")
    @Query("SELECT * FROM exercicios INNER JOIN treino_exercicio ON exercicios.id = treino_exercicio.exercicioId WHERE treino_exercicio.treinoId = :treinoId")
    List<TreinoExercicio> getExerciciosForTreino(int treinoId);

////    @Query("SELECT * FROM treino_exercicio WHERE exercicioId = :exercicioId")
//    @Query("SELECT * FROM treinos INNER JOIN treino_exercicio on treinos.id = treino_exercicio.treinoId WHERE treino_exercicio.exercicioId = :exercicioId")
//    List<TreinoExercicio> getTreinosForExercicio(int exercicioId);

//    @Query("SELECT * FROM treinos " +
//            "INNER JOIN treino_exercicio ON treinos.id = treino_exercicio.treinoId " +
//            "INNER JOIN exercicios ON exercicios.id = treino_exercicio.exercicioId " +
//            "WHERE treino_exercicio.treinoId = :treinoId")
//    List<Exercicio> getTreinoWithExercicios(int treinoId);

}
