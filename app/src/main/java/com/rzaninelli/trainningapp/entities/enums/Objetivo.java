package com.rzaninelli.trainningapp.entities.enums;

public enum Objetivo {

    FORCA(1),
    HIPERTROFIA(2),
    RESISTENCIA(3);

    public final int indice;

    public int getIndice() {
        return indice;
    }

    Objetivo(int newIndice) {
        indice = newIndice;
    }

}
