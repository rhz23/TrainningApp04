package com.rzaninelli.trainningapp.entities;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.graphics.drawable.Drawable;

@Entity
public class GrupoMuscular {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nome;
    @Ignore
    private Drawable imagemGrupoMuscular;
    private int imagemGrupoMuscularRef;

    public GrupoMuscular(){

    }

    public GrupoMuscular(String nome, Drawable imagemGrupoMuscular) {
        this.nome = nome;
        this.imagemGrupoMuscular = imagemGrupoMuscular;
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

    public Drawable getImagemGrupoMuscular() {
        return imagemGrupoMuscular;
    }

    public void setImagemGrupoMuscular(Drawable imagemGrupoMuscular) {
        this.imagemGrupoMuscular = imagemGrupoMuscular;
    }

    public int getImagemGrupoMuscularRef() {
        return imagemGrupoMuscularRef;
    }

    public void setImagemGrupoMuscularRef(int imagemGrupoMuscularRef) {
        this.imagemGrupoMuscularRef = imagemGrupoMuscularRef;
    }
}
