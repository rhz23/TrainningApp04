package com.rzaninelli.trainningapp.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;

import com.rzaninelli.trainningapp.entities.enums.Dificuldade;
import com.rzaninelli.trainningapp.entities.enums.EquipamentoUtilizado;
import com.rzaninelli.trainningapp.entities.enums.GrupoMuscularEnum;

import java.io.Serializable;

@Entity(tableName = "exercicios_table")
public class Exercicio implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    @Ignore
    private GrupoMuscularEnum grupoMuscularEnum;
    @Ignore
    private transient Drawable imagemExercicio;
    @Ignore
    private EquipamentoUtilizado equipamentoUtilizado;
    @Ignore
    private Dificuldade dificuldade;
    @Ignore
    private int imagemExercicioRef;

    public Exercicio() {
    }

    public Exercicio(String nome, GrupoMuscularEnum grupoMuscularEnum, Drawable imagemExercicio, EquipamentoUtilizado equipamentoUtilizado) {
        this.nome = nome;
        this.grupoMuscularEnum = grupoMuscularEnum;
        this.imagemExercicio = imagemExercicio;
        this.equipamentoUtilizado = equipamentoUtilizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GrupoMuscularEnum getGrupoMuscularEnum() {
        return grupoMuscularEnum;
    }

    public void setGrupoMuscularEnum(GrupoMuscularEnum grupoMuscularEnum) {
        this.grupoMuscularEnum = grupoMuscularEnum;
    }

    public Drawable getImagemExercicio() {
        return imagemExercicio;
    }

    public void setImagemExercicio(Drawable imagemExercicio) {
        this.imagemExercicio = imagemExercicio;
    }

    public EquipamentoUtilizado getEquipamentoUtilizado() {
        return equipamentoUtilizado;
    }

    public void setEquipamentoUtilizado(EquipamentoUtilizado equipamentoUtilizado) {
        this.equipamentoUtilizado = equipamentoUtilizado;
    }

    public Dificuldade getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Dificuldade dificuldade) {
        this.dificuldade = dificuldade;
    }

    public int getImagemExercicioRef() {
        return imagemExercicioRef;
    }

    public void setImagemExercicioRef(int imagemExercicioRef) {
        this.imagemExercicioRef = imagemExercicioRef;
    }
}
