package com.unicesumar.techweek.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;

import com.unicesumar.techweek.data.entity.Projeto;

import java.util.List;

@Dao
public interface ProjetoDao {

    @Insert
    long inserir(Projeto projeto);

    @Delete
    void deletar(Projeto projeto);

    @Query("SELECT * FROM projetos ORDER BY nome_projeto ASC")
    List<Projeto> listarTodos();

    @Query("SELECT * FROM projetos WHERE ra_autor = :ra LIMIT 1")
    Projeto buscarPorRa(String ra);
}
