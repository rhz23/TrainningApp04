package com.rzaninelli.trainningapp.entities;

import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;
import com.rzaninelli.trainningapp.entities.enums.Objetivos;

import java.io.Serializable;
import java.util.List;

public class Treino implements Serializable {

    private String nome;
    private List<DiasDaSemana> diasDeTreino;
    private List<Exercicio> exerciciosDoTreino;
    private String repeticoes;
    private Objetivos objetivos;

    public Treino(){
    }

    public Treino(String nome, List<DiasDaSemana> diasDeTreino, List<Exercicio> exerciciosDoTreino, String repeticoes, Objetivos objetivos) {
        this.nome = nome;
        this.diasDeTreino = diasDeTreino;
        this.exerciciosDoTreino = exerciciosDoTreino;
        this.repeticoes = repeticoes;
        this.objetivos = objetivos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<DiasDaSemana> getDiasDeTreino() {
        return diasDeTreino;
    }

    public void setDiasDeTreino(List<DiasDaSemana> diasDeTreino) {
        this.diasDeTreino = diasDeTreino;
    }

    public List<Exercicio> getExerciciosDoTreino() {
        return exerciciosDoTreino;
    }

    public void setExerciciosDoTreino(List<Exercicio> exerciciosDoTreino) {
        this.exerciciosDoTreino = exerciciosDoTreino;
    }

    public String getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(String repeticoes) {
        this.repeticoes = repeticoes;
    }

    public Objetivos getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(Objetivos objetivos) {
        this.objetivos = objetivos;
    }

    public void addDiaDaSemana(DiasDaSemana diaDaSemana) {
        if (!diasDeTreino.contains(diaDaSemana))
        diasDeTreino.add(diaDaSemana);
    }
}
