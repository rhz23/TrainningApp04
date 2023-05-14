package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;

public class DiasDaSemanaConverter {

    @TypeConverter
    public static int toDiasDaSemanaID(DiasDaSemana diasDaSemana) {
        return diasDaSemana.ordinal();
//        switch (diasDaSemana) {
//            case SEGUNDA:
//                return 1;
//            case TERCA:
//                return 2;
//            case QUARTA:
//                return 3;
//            case QUINTA:
//                return 4;
//            case SEXTA:
//                return 5;
//            case SABADO:
//                return 6;
//            case DOMINGO:
//                return 7;
//            default:
//                throw new IllegalArgumentException("Invalid Value");
//        }
    }

    @TypeConverter
    public static DiasDaSemana fromDiasDaSemanaID(int id) {
        return (DiasDaSemana.values()[id]);
//        switch (id) {
//            case 1:
//                return DiasDaSemana.SEGUNDA;
//            case 2:
//                return DiasDaSemana.TERCA;
//            case 3:
//                return DiasDaSemana.QUARTA;
//            case 4:
//                return DiasDaSemana.QUINTA;
//            case 5:
//                return DiasDaSemana.SEXTA;
//            case 6:
//                return DiasDaSemana.SABADO;
//            case 7:
//                return DiasDaSemana.DOMINGO;
//            default:
//                throw new IllegalArgumentException("Invalid Value");
//        }
    }
}
