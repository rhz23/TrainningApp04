package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.EquipamentoUtilizado;

public class EquipamentoUtilizadoConverter {

    @TypeConverter
    public static EquipamentoUtilizado toEquipamentoUtilizado(int indice) {

        return (EquipamentoUtilizado.values()[indice]);

//        switch (value) {
//            case "PESOCORPORAL":
//                return EquipamentoUtilizado.PESOCORPORAL;
//            case "HALTERES":
//                return EquipamentoUtilizado.HALTERES;
//            case "BARRA":
//                return EquipamentoUtilizado.BARRA;
//            case "CABO":
//                return EquipamentoUtilizado.CABO;
//            case "MAQUINAARTICULADA":
//                return EquipamentoUtilizado.MAQUINAARTICULADA;
//            default:
//                throw new IllegalArgumentException("Invalid value for EquipamentoUtilizado: " + value);
//        }
    }

    @TypeConverter
    public static int fromEquipamentoUtilizado(EquipamentoUtilizado value) {
        return value.ordinal();
    }
}
