package com.rzaninelli.trainningapp.entities.enums;

public enum Objetivos {

    FORCA(1),
    HIPERTROFIA(2),
    RESISTENCIA(3);

    private int index;
    public int indice() {
        return index;
    }
    private Objetivos(int indice) {
        index = indice;
    }

}
