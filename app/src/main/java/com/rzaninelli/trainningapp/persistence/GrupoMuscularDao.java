package com.rzaninelli.trainningapp.persistence;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.rzaninelli.trainningapp.entities.GrupoMuscular;

import java.util.List;

@Dao
public interface GrupoMuscularDao {

    @Insert
    Long insert(GrupoMuscular grupoMuscular);

    @Delete
    void delete(GrupoMuscular grupoMuscular);

    @Update
    void update(GrupoMuscular grupoMuscular);

    @Query("SELECT * FROM grupoMuscular WHERE id = :id")
    GrupoMuscular queryForId(int id);

    @Query("SELECT * FROM grupoMuscular ORDER BY nome ASC")
    List<GrupoMuscular> queryAll();

}
