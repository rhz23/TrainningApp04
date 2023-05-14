package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.Dificuldade;
import com.rzaninelli.trainningapp.entities.enums.Objetivo;

public class DificuldadeConverter {

    @TypeConverter
    public static int toDificuldadeID(Dificuldade dificuldade) {
        return dificuldade.ordinal();
//        switch (dificuldade) {
//            case FACIL:
//                return 1;
//            case MODERADO:
//                return 2;
//            case DIFICIL:
//                return 3;
//            case MUITODIFICIL:
//                return 4;
//            default:
//                throw new IllegalArgumentException("Invalid Value");
//        }
    }

    @TypeConverter
    public static Dificuldade fromDificuldadeID(int id) {
        return (Dificuldade.values()[id]);
//        switch (id) {
//            case 1:
//                return Dificuldade.FACIL;
//            case 2:
//                return Dificuldade.MODERADO;
//            case 3:
//                return Dificuldade.DIFICIL;
//            case 4:
//                return Dificuldade.MUITODIFICIL;
//            default:
//                throw new IllegalArgumentException("Invalid Value");
//        }
    }
}
