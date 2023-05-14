package com.rzaninelli.trainningapp.entities.enums;

public enum GrupoMuscularEnum {

    DORSAL(1),
    DELTOIDE(2),
    TRICEPS(3),
    BICEPS(4),
    PERNA(5),
    PANTURRILHA(6),
    ABDOMINAL(7),
    PEITO(8);

    private int index;
    public int indice() {
        return index;
    }
    GrupoMuscularEnum(int indice) {
        index = indice;
    }
}
