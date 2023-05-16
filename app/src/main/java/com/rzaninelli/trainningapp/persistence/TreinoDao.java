package com.rzaninelli.trainningapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Transaction;
import android.arch.persistence.room.Update;

import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.entities.TreinoWithExercicios;
import com.rzaninelli.trainningapp.entities.auxiliares.TreinoDiasDaSemana;
import com.rzaninelli.trainningapp.entities.auxiliares.TreinoExercicio;
import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TreinoDao {

    @Insert
    long insert(Treino treino);

    @Delete
    void delete(Treino treino);

    @Update
    void update(Treino treino);

    @Query("SELECT * FROM treinos WHERE treinos.id = :id")
    Treino get(int id);

    @Query("SELECT * FROM treinos")
    List<Treino> getTreinos();

    @Query("SELECT COUNT(*) FROM treinos")
    int getRowCount();

    @Query("DELETE FROM treinos")
    void deleteAll();

    @Transaction
    @Query("SELECT * FROM treinos WHERE id = :treinoId")
    LiveData<TreinoWithExercicios> getTreinoWithExercicios(int treinoId);

    @Transaction
    default void insertTreinoWithExercicios(Treino treino) {
        long treinoId = insert(treino);

        List<TreinoExercicio> treinoExercicios = new ArrayList<>();

        for (Exercicio exercicio: treino.getExerciciosDoTreino()) {
            TreinoExercicio treinoExercicio = new TreinoExercicio();
            treinoExercicio.setTreinoId(treinoId);
            treinoExercicio.setExercicioId(exercicio.getId());
            treinoExercicios.add(treinoExercicio);
        }
        insertTreinoExercicioJuncoes(treinoExercicios);
    }

    @Insert
    void insertTreinoExercicioJuncoes(List<TreinoExercicio> treinoExercicios);

    @Transaction
    default void insertTreinoWithDiasDaSemana(Treino treino) {
        long treinoId = insert(treino);

        List<TreinoDiasDaSemana> diasDaSemanaList = new ArrayList<>();

        for (DiasDaSemana diasDaSemana : treino.getDiasDeTreino()) {
            TreinoDiasDaSemana treinoDiasDaSemana = new TreinoDiasDaSemana();
            treinoDiasDaSemana.setTreinoId(treinoId);
            treinoDiasDaSemana.setDiasDaSemana(diasDaSemana.toString());
        }
        insertDiasDaSemana(diasDaSemanaList);
    }

    @Insert
    void insertDiasDaSemana(List<TreinoDiasDaSemana> treinoDiasDaSemanaList);

    @Transaction
    default void insertTreinoWithExerciciosAndDiasDaSemana(Treino treino) {
        long treinoId = insert(treino);

        List<TreinoExercicio> treinoExercicios = new ArrayList<>();
        List<TreinoDiasDaSemana> diasDaSemanaList = new ArrayList<>();

        for (Exercicio exercicio : treino.getExerciciosDoTreino()) {
            TreinoExercicio treinoExercicio = new TreinoExercicio();
            treinoExercicio.setTreinoId(treinoId);
            treinoExercicio.setExercicioId(exercicio.getId());
            treinoExercicios.add(treinoExercicio);
        }

        for (DiasDaSemana diasDaSemana : treino.getDiasDeTreino()) {
            TreinoDiasDaSemana treinoDiasDaSemana = new TreinoDiasDaSemana();
            treinoDiasDaSemana.setTreinoId(treinoId);
            treinoDiasDaSemana.setDiasDaSemana(diasDaSemana.toString());
            diasDaSemanaList.add(treinoDiasDaSemana);
        }

        insertTreinoExercicioJuncoes(treinoExercicios);
        insertDiasDaSemana(diasDaSemanaList);
    }

    @Transaction
    default void updateTreinoWithExercicioAndDiasDaSemana(Treino treino) {
        update(treino);

        long treinoId = treino.getId();

        List<TreinoExercicio> treinoExercicios = new ArrayList<>();
        List<TreinoDiasDaSemana> diasDaSemanaList = new ArrayList<>();

        for (Exercicio exercicio : treino.getExerciciosDoTreino()) {
            TreinoExercicio treinoExercicio = new TreinoExercicio();
            treinoExercicio.setTreinoId(treinoId);
            treinoExercicio.setExercicioId(exercicio.getId());
            treinoExercicios.add(treinoExercicio);
        }

        for (DiasDaSemana diasDaSemana : treino.getDiasDeTreino()) {
            TreinoDiasDaSemana treinoDiasDaSemana = new TreinoDiasDaSemana();
            treinoDiasDaSemana.setTreinoId(treinoId);
            treinoDiasDaSemana.setDiasDaSemana(diasDaSemana.toString());
            diasDaSemanaList.add(treinoDiasDaSemana);
        }

        deleteTreinoExercicioJuncoesByTreinoId(treinoId);
        insertTreinoExercicioJuncoes(treinoExercicios);

        deleteDiasDaSemanaByTreinoId(treinoId);
        insertDiasDaSemana(diasDaSemanaList);
    }

    @Query("DELETE FROM treino_exercicio WHERE treinoId = :treinoId")
    void deleteTreinoExercicioJuncoesByTreinoId(long treinoId);

    @Query("DELETE FROM treino_diasDaSemana WHERE treinoId = :treinoId")
    void deleteDiasDaSemanaByTreinoId(long treinoId);

}
