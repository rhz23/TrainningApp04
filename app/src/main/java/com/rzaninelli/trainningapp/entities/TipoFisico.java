package com.rzaninelli.trainningapp.entities;

import android.graphics.drawable.Drawable;

public class TipoFisico {

    private String nome;
    private Drawable imagemTipoFisico;

    public TipoFisico(String nome, Drawable imagemTipoFisico) {
        this.nome = nome;
        this.imagemTipoFisico = imagemTipoFisico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Drawable getImagemTipoFisico() {
        return imagemTipoFisico;
    }

    public void setImagemTipoFisico(Drawable imagemTipoFisico) {
        this.imagemTipoFisico = imagemTipoFisico;
    }
}
