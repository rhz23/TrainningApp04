package com.rzaninelli.trainningapp.entities.enums;

public enum EquipamentoUtilizado {

    PESOCORPORAL(1),
    HALTERES(2),
    BARRA(3),
    CABO(4),
    MAQUINAARTICULADA(4);

    public final int indice;

    EquipamentoUtilizado(int newIndice) {
        indice = newIndice;
    }

    public int getIndice(){
        return indice;
    }
}
