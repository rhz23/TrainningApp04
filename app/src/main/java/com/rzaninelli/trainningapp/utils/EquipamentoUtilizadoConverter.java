package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.EquipamentoUtilizado;

public class EquipamentoUtilizadoConverter {

    @TypeConverter
    public static EquipamentoUtilizado toEquipamentoUtilizado(int indice) {

        return (EquipamentoUtilizado.values()[indice]);
    }

    @TypeConverter
    public static int fromEquipamentoUtilizado(EquipamentoUtilizado value) {
        return value.ordinal();
    }
}
