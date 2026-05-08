package com.unicesumar.techweek.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity(tableName = "projetos")
public class Projeto {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nome_autor")
    public String nomeAutor;

    @ColumnInfo(name = "ra_autor")
    public String raAutor;

    @ColumnInfo(name = "nome_projeto")
    public String nomeProjeto;

    @ColumnInfo(name = "descricao")
    public String descricao;

    public Projeto() {}

    public Projeto(String nomeAutor, String raAutor, String nomeProjeto, String descricao) {
        this.nomeAutor = nomeAutor;
        this.raAutor = raAutor;
        this.nomeProjeto = nomeProjeto;
        this.descricao = descricao;
    }
}
