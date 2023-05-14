package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.Objetivo;

public class ObjetivoConverter {

    @TypeConverter
    public static int toObjetivoID(Objetivo objetivo) {
        return objetivo.ordinal();
//        switch (objetivo) {
//            case FORCA:
//                return 1;
//            case HIPERTROFIA:
//                return 2;
//            case RESISTENCIA:
//                return 3;
//            default:
//                throw new IllegalArgumentException("Invalid Value");
//        }
    }

    @TypeConverter
    public static Objetivo fromObjetivoID(int id) {
        return (Objetivo.values()[id]);
//        switch (id) {
//            case 1:
//                return Objetivo.FORCA;
//            case 2:
//                return Objetivo.HIPERTROFIA;
//            case 3:
//                return Objetivo.RESISTENCIA;
//            default:
//                throw new IllegalArgumentException("Invalid Value");
//        }
    }
}
