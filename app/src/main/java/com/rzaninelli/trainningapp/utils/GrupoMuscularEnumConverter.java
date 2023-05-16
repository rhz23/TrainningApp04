package com.rzaninelli.trainningapp.utils;

import android.arch.persistence.room.TypeConverter;

import com.rzaninelli.trainningapp.entities.enums.GrupoMuscularEnum;

public class GrupoMuscularEnumConverter {

    @TypeConverter
    public static GrupoMuscularEnum toGrupoMuscularEnum(int value) {
        return (GrupoMuscularEnum.values()[value]);
    }

    @TypeConverter
    public static int fromGrupoMuscularEnum(GrupoMuscularEnum grupoMuscularEnum) {
        return grupoMuscularEnum.ordinal();
    }
}
