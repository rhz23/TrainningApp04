package com.rzaninelli.trainningapp.entities;

import android.graphics.drawable.Drawable;

public class GrupoMuscular {

    private String nome;
    private Drawable imagemGrupoMuscular;

    public GrupoMuscular(String nome, Drawable imagemGrupoMuscular) {
        this.nome = nome;
        this.imagemGrupoMuscular = imagemGrupoMuscular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Drawable getImagemGrupoMuscular() {
        return imagemGrupoMuscular;
    }

    public void setImagemGrupoMuscular(Drawable imagemGrupoMuscular) {
        this.imagemGrupoMuscular = imagemGrupoMuscular;
    }
}
