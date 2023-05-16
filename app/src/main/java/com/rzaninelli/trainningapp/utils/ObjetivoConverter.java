package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.Objetivo;

public class ObjetivoConverter {

    @TypeConverter
    public static int toObjetivoID(Objetivo objetivo) {
        return objetivo.ordinal();
    }

    @TypeConverter
    public static Objetivo fromObjetivoID(int id) {
        return (Objetivo.values()[id]);
    }
}
