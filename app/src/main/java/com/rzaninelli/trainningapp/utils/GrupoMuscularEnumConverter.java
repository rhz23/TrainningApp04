package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.GrupoMuscularEnum;

public class GrupoMuscularEnumConverter {

    @TypeConverter
    public static GrupoMuscularEnum toGrupoMuscularEnum(int value) {
        return (GrupoMuscularEnum.values()[value]);
//        switch (value) {
//            case 1:
//                return GrupoMuscularEnum.DORSAL;
//            case 2:
//                return GrupoMuscularEnum.DELTOIDE;
//            case 3:
//                return GrupoMuscularEnum.TRICEPS;
//            case 4:
//                return GrupoMuscularEnum.BICEPS;
//            case 5:
//                return GrupoMuscularEnum.PERNA;
//            case 6:
//                return GrupoMuscularEnum.PANTURRILHA;
//            case 7:
//                return GrupoMuscularEnum.ABDOMINAL;
//            case 8:
//                return GrupoMuscularEnum.PEITO;
//            default:
//                throw new IllegalArgumentException("Invalid value for GrupoMuscular: " + value);
//        }
    }

    @TypeConverter
    public static int fromGrupoMuscularEnum(GrupoMuscularEnum grupoMuscularEnum) {
        return grupoMuscularEnum.ordinal();
    }
}
