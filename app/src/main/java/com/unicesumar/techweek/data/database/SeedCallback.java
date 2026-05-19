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
                // 01/06 - Segunda
                new Palestra(
                        "Pense com IA: A Revolucao da Inteligencia Ampliada",
                        "Gustavo Melles",
                        "Fundador do BuscaIA.com e OiRobo.com. Professor no MBA em Inovacao e IA da PUCPR. Colunista na Radio CBN Londrina.",
                        "01/06 - 19:15",
                        "Auditorio Principal",
                        "TW2026-01"
                ),
                new Palestra(
                        "Os Data Taggers e o Trabalho Invisivel por Tras da IA",
                        "Jessy Borges Ferracioli",
                        "Advogada e pesquisadora de direito e inteligencia artificial na Lawgorithm, do nucleo IA e Raca.",
                        "01/06 - 20:30",
                        "Auditorio Principal",
                        "TW2026-02"
                ),

                // 02/06 - Terça
                new Palestra(
                        "Construcao e Orquestracao de Agentes de IA",
                        "Luciano Soler",
                        "Engenheiro da Computacao, Mestre em Ciencias da Computacao. Atua com desenvolvimento de Software e IA no IAPAR.",
                        "02/06 - 19:15",
                        "Auditorio Principal",
                        "TW2026-03"
                ),
                new Palestra(
                        "O Programador Morreu. Vida Longa ao Programador",
                        "Michel Banagouro",
                        "Arquiteto de Software e CTO na Leanwork Group. 20 anos de experiencia em e-commerces como Centauro, Ultrafarma e Riachuelo.",
                        "02/06 - 20:30",
                        "Auditorio Principal",
                        "TW2026-04"
                ),

                // 03/06 - Quarta
                new Palestra(
                        "IA e Protecao de Dados: Desafios, Etica e Seguranca",
                        "Luiz Fernando Pereira Nunes",
                        "Profissional com +15 anos em TI. Mestre em Ciencia da Computacao e em Direito e Tecnologia. Lider na Hanke Digital Solutions.",
                        "03/06 - 19:15",
                        "Auditorio Principal",
                        "TW2026-05"
                ),
                new Palestra(
                        "Mostra de Trabalhos - II Tech Week",
                        "Alunos UniCesumar",
                        "Apresentacao dos projetos desenvolvidos pelos alunos dos cursos de tecnologia da UniCesumar Londrina.",
                        "03/06 - 20:30",
                        "Auditorio Principal",
                        "TW2026-06"
                )
        };


        for (Palestra p : palestras) {
            database.palestraDao().inserir(p);
        }
    }
}
