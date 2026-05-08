package com.unicesumar.techweek.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "palestras")
public class Palestra {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "titulo")
    public String titulo;

    @ColumnInfo(name = "palestrante")
    public String palestrante;

    @ColumnInfo(name = "bio_palestrante")
    public String bioPalestrante;

    @ColumnInfo(name = "horario")
    public String horario;

    @ColumnInfo(name = "local")
    public String local;

    @ColumnInfo(name = "codigo_checkin")
    public String codigoCheckin;

    @ColumnInfo(name = "favoritada")
    public boolean favoritada;

    public Palestra() {}

    public Palestra(String titulo, String palestrante, String bioPalestrante,
                    String horario, String local, String codigoCheckin) {
        this.titulo = titulo;
        this.palestrante = palestrante;
        this.bioPalestrante = bioPalestrante;
        this.horario = horario;
        this.local = local;
        this.codigoCheckin = codigoCheckin;
        this.favoritada = false;
    }
}
