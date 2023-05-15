package com.rzaninelli.trainningapp.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rzaninelli.trainningapp.entities.Exercicio;

import java.util.List;

@Dao
public interface ExercicioDao {

    @Insert
    Long insert(Exercicio exercicio);

    @Delete
    void delete(Exercicio exercicio);

    @Update
    void update(Exercicio exercicio);

    @Query("SELECT * FROM exercicios WHERE id = :id")
    Exercicio queryForId(int id);

    @Query("SELECT * FROM exercicios ORDER BY nome ASC")
    List<Exercicio> queryAll();

}
