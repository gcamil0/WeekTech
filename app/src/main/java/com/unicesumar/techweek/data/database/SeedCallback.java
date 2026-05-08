package com.unicesumar.techweek.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.RoomDatabase;

import com.unicesumar.techweek.data.entity.Palestra;

import java.util.concurrent.Executors;

/**
 * Popula o banco com dados do evento na primeira execução (seed).
 * Garante que o app funcione offline-first desde o primeiro acesso.
 */
public class SeedCallback extends RoomDatabase.Callback {

    private final Context context;

    public SeedCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(@NonNull androidx.sqlite.db.SupportSQLiteDatabase db) {
        super.onCreate(db);
        Executors.newSingleThreadExecutor().execute(() -> popularPalestras());
    }

    private void popularPalestras() {
        AppDatabase database = AppDatabase.getInstance(context);

        // Palestras do evento (dados fixos do evento)
        Palestra[] palestras = {
            new Palestra(
                "Introdução ao Desenvolvimento Mobile",
                "Prof. Carlos Mendes",
                "Especialista em Android com 10 anos de experiência no mercado.",
                "09:00 - 10:00",
                "Auditório Principal",
                "TW2024-01"
            ),
            new Palestra(
                "Clean Architecture na Prática",
                "Ana Paula Souza",
                "Engenheira de software sênior, referência em arquitetura Android.",
                "10:30 - 11:30",
                "Auditório Principal",
                "TW2024-02"
            ),
            new Palestra(
                "Room Database e Persistência Local",
                "Prof. Ricardo Alves",
                "Doutor em Ciência da Computação, pesquisador de banco de dados móveis.",
                "14:00 - 15:00",
                "Sala 101",
                "TW2024-03"
            ),
            new Palestra(
                "UX/UI para Apps Mobile",
                "Fernanda Lima",
                "Designer de produto com foco em experiência do usuário mobile.",
                "15:30 - 16:30",
                "Auditório Principal",
                "TW2024-04"
            ),
            new Palestra(
                "DevOps e Deploy de Apps Android",
                "Marcos Oliveira",
                "Especialista em CI/CD e automação de build para Android.",
                "17:00 - 18:00",
                "Sala 101",
                "TW2024-05"
            )
        };

        for (Palestra p : palestras) {
            database.palestraDao().inserir(p);
        }
    }
}
