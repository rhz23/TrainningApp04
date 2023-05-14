package com.rzaninelli.trainningapp.persistence;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;

import com.rzaninelli.trainningapp.R;
import com.rzaninelli.trainningapp.adapters.ExercicioAdapter;
import com.rzaninelli.trainningapp.entities.Exercicio;
import com.rzaninelli.trainningapp.entities.GrupoMuscular;
import com.rzaninelli.trainningapp.entities.Treino;
import com.rzaninelli.trainningapp.entities.enums.DiasDaSemana;
import com.rzaninelli.trainningapp.entities.enums.Dificuldade;
import com.rzaninelli.trainningapp.entities.enums.EquipamentoUtilizado;
import com.rzaninelli.trainningapp.entities.enums.GrupoMuscularEnum;
import com.rzaninelli.trainningapp.entities.enums.Objetivo;
import com.rzaninelli.trainningapp.utils.DiasDaSemanaConverter;
import com.rzaninelli.trainningapp.utils.DificuldadeConverter;
import com.rzaninelli.trainningapp.utils.EquipamentoUtilizadoConverter;
import com.rzaninelli.trainningapp.utils.GrupoMuscularEnumConverter;
import com.rzaninelli.trainningapp.utils.ObjetivoConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Treino.class, Exercicio.class, GrupoMuscular.class}, version = 1, exportSchema = false)
@TypeConverters({
        DiasDaSemanaConverter.class,
        ObjetivoConverter.class,
        GrupoMuscularEnumConverter.class,
        EquipamentoUtilizadoConverter.class,
        DificuldadeConverter.class
})
public abstract class TreinosDatabase extends RoomDatabase {

    public abstract TreinoDao treinoDao();

    public abstract ExercicioDao exercicioDao();

    public abstract GrupoMuscularDao grupoMuscularDao();

    private static TreinosDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static final String DATABASE_NAME = "treinos_database";

    public static TreinosDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (TreinosDatabase.class) {
                if (INSTANCE == null) {
                    Builder builder = Room.databaseBuilder(context, TreinosDatabase.class, DATABASE_NAME);

                    builder.addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                                @Override
                                public void run() {
                                    carregaExerciciosIniciais(context);
                                }
                            });
                        }
                    });

                    INSTANCE = (TreinosDatabase) builder.build();

                    }
                }
            }
        return INSTANCE;
    }

    private static void carregaExerciciosIniciais(Context context) {


        String[] nomes = context.getResources().getStringArray(R.array.nomes_exercicios);
        int[] gruposMusculares_posicao = context.getResources().getIntArray(R.array.grupos_musculares_exercicios);
        int[] equipamentosUtilizados_posicao = context.getResources().getIntArray(R.array.equipamentos_utilizados_exercicios);
        int[] dificuldades_posicao = context.getResources().getIntArray(R.array.dificuldades_exercicios);
        TypedArray imagensExercicios = context.getResources().obtainTypedArray(R.array.imagens_exercicios);

        GrupoMuscularEnum[] gruposMusculares = GrupoMuscularEnum.values();
        EquipamentoUtilizado[] equipamentoUtilizados = EquipamentoUtilizado.values();
        Dificuldade[] dificuldades = Dificuldade.values();

//        List<Exercicio> exercicios = new ArrayList<>();

        Exercicio exercicio;

        for (int cont = 0; cont < nomes.length; cont++) {

            exercicio = new Exercicio();
            exercicio.setNome(nomes[cont]);
            exercicio.setGrupoMuscularEnum(gruposMusculares[gruposMusculares_posicao[cont]]);
            exercicio.setEquipamentoUtilizado(equipamentoUtilizados[equipamentosUtilizados_posicao[cont]]);
            exercicio.setDificuldade(dificuldades[dificuldades_posicao[cont]]);
            exercicio.setImagemExercicio(imagensExercicios.getDrawable(cont));
            exercicio.setImagemExercicioRef(cont);

            INSTANCE.exercicioDao().insert(exercicio);

//            exercicios.add(exercicio);
        }

    }
}