package com.unicesumar.techweek.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.unicesumar.techweek.data.dao.ParticipanteDao;
import com.unicesumar.techweek.data.dao.PalestraDao;
import com.unicesumar.techweek.data.dao.ProjetoDao;
import com.unicesumar.techweek.data.dao.PresencaDao;
import com.unicesumar.techweek.data.entity.Participante;
import com.unicesumar.techweek.data.entity.Palestra;
import com.unicesumar.techweek.data.entity.Projeto;
import com.unicesumar.techweek.data.entity.Presenca;

@Database(
    entities = {Participante.class, Palestra.class, Projeto.class, Presenca.class},
    version = 1,
    exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract ParticipanteDao participanteDao();
    public abstract PalestraDao palestraDao();
    public abstract ProjetoDao projetoDao();
    public abstract PresencaDao presencaDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "techweek.db"
                    )
                    .addCallback(new SeedCallback(context))
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
