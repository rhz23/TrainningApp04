package com.rzaninelli.trainningapp.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;
import com.rzaninelli.trainningapp.entities.enums.Objetivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity(tableName = "treinos", indices = @Index(value = {"nome"}, unique = true))
public class Treino implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @NonNull
    private String nome;
    @Ignore
    private HashSet<DiasDaSemana> diasDeTreino;
    @Ignore
    private List<Exercicio> exerciciosDoTreino;
    private String repeticoes;
    @ForeignKey(entity = GrupoMuscular.class, parentColumns = "id", childColumns = "grupoMuscularId")
    private int grupoMuscularID;
    private Objetivo objetivo;

    public Treino(){
        diasDeTreino = new HashSet<>();
        exerciciosDoTreino = new ArrayList<>();
    }

    public Treino(String nome, HashSet<DiasDaSemana> diasDeTreino, List<Exercicio> exerciciosDoTreino, String repeticoes, int grupoMuscularID, Objetivo objetivo) {
        this.nome = nome;
        this.diasDeTreino = diasDeTreino;
        this.exerciciosDoTreino = exerciciosDoTreino;
        this.repeticoes = repeticoes;
        this.grupoMuscularID = grupoMuscularID;
        this.objetivo = objetivo;
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

    public HashSet<DiasDaSemana> getDiasDeTreino() {
        return diasDeTreino;
    }

    public void setDiasDeTreino(HashSet<DiasDaSemana> diasDeTreino) {
        this.diasDeTreino = diasDeTreino;
    }

    public List<Exercicio> getExerciciosDoTreino() {
        return exerciciosDoTreino;
    }

    public void setExerciciosDoTreino(List<Exercicio> exerciciosDoTreino) {
        this.exerciciosDoTreino = exerciciosDoTreino;
    }

    public void addExercicioDoTreino(Exercicio exercicio){
        exerciciosDoTreino.add(exercicio);
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Objetivo getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(Objetivo objetivo) {
        this.objetivo = objetivo;
    }

    public void addDiaDaSemana(DiasDaSemana diaDaSemana) {

        diasDeTreino.add(diaDaSemana);
    }

    public int getGrupoMuscularID() {
        return grupoMuscularID;
    }

    public void setGrupoMuscularID(int grupoMuscularID) {
        this.grupoMuscularID = grupoMuscularID;
    }

}
