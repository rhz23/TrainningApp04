package com.rzaninelli.trainningapp.entities.enums;

public enum DiasDaSemana {

    SEGUNDA(1),
    TERCA(2),
    QUARTA(3),
    QUINTA(4),
    SEXTA(5),
    SABADO(6),
    DOMINGO(7);

    private int index;
    public int indice() {
        return index;
    }
    DiasDaSemana(int indice) {
        index = indice;
    }
}
