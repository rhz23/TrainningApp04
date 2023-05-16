package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;

public class DiasDaSemanaConverter {

    @TypeConverter
    public static int toDiasDaSemanaID(DiasDaSemana diasDaSemana) {
        return diasDaSemana.ordinal();
    }

    @TypeConverter
    public static DiasDaSemana fromDiasDaSemanaID(int id) {
        return (DiasDaSemana.values()[id]);
    }

    @TypeConverter
    public static DiasDaSemana fromDiasDaSemanaString(String diaDaSemana) {
        return (DiasDaSemana.valueOf(diaDaSemana));
    }
}
