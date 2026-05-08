package com.unicesumar.techweek.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "participantes")
public class Participante {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nome")
    public String nome;

    @ColumnInfo(name = "ra")
    public String ra;

    @ColumnInfo(name = "curso")
    public String curso;

    @ColumnInfo(name = "serie")
    public String serie;

    @ColumnInfo(name = "coffee_break")
    public boolean coffeeBreak;

    @ColumnInfo(name = "presenca_confirmada")
    public boolean presencaConfirmada;

    public Participante() {}

    public Participante(String nome, String ra, String curso, String serie, boolean coffeeBreak) {
        this.nome = nome;
        this.ra = ra;
        this.curso = curso;
        this.serie = serie;
        this.coffeeBreak = coffeeBreak;
        this.presencaConfirmada = false;
    }
}
