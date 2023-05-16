package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.Dificuldade;

public class DificuldadeConverter {

    @TypeConverter
    public static int toDificuldadeID(Dificuldade dificuldade) {
        return dificuldade.ordinal();
    }

    @TypeConverter
    public static Dificuldade fromDificuldadeID(int id) {
        return (Dificuldade.values()[id]);
    }
}
