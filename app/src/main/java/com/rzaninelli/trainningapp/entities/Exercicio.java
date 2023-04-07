package com.rzaninelli.trainningapp.entities;

import android.graphics.drawable.Drawable;

import com.rzaninelli.trainningapp.entities.enums.Dificuldade;
import com.rzaninelli.trainningapp.entities.enums.EquipamentoUtilizado;
import com.rzaninelli.trainningapp.entities.enums.GrupoMuscular;

import java.io.Serializable;

public class Exercicio implements Serializable {

    private String nome;
    private GrupoMuscular grupoMuscular;
    private Drawable imagemExercicio;
    private EquipamentoUtilizado equipamentoUtilizado;
    private Dificuldade dificuldade;

    public Exercicio() {
    }

    public Exercicio(String nome, GrupoMuscular grupoMuscular, Drawable imagemExercicio, EquipamentoUtilizado equipamentoUtilizado) {
        this.nome = nome;
        this.grupoMuscular = grupoMuscular;
        this.imagemExercicio = imagemExercicio;
        this.equipamentoUtilizado = equipamentoUtilizado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public GrupoMuscular getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setGrupoMuscular(GrupoMuscular grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
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
}
