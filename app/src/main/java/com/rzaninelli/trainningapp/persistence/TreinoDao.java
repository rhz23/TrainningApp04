package com.rzaninelli.trainningapp.persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rzaninelli.trainningapp.entities.Treino;

import java.util.List;

@Dao
public interface TreinoDao {

    @Insert
    void insert(Treino treino);

    @Delete
    void delete(Treino treino);

    @Update
    void update(Treino treino);

    @Query("SELECT * FROM treinos WHERE treinos.id = :id")
    Treino get(int id);

//    @Query("SELECT * FROM treinos ORDER BY nome ASC")
//    List<Treino> queryAll();

    @Query("SELECT * FROM treinos")
    List<Treino> getTreinos();

    @Query("SELECT COUNT(*) FROM treinos")
    int getRowCount();

    @Query("DELETE FROM treinos")
    void deleteAll();

}
