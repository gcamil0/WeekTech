package com.unicesumar.techweek.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.unicesumar.techweek.data.entity.Presenca;

import java.util.List;

@Dao
public interface PresencaDao {

    @Insert
    long inserir(Presenca presenca);

    @Query("SELECT * FROM presencas WHERE palestra_id = :palestraId")
    List<Presenca> listarPorPalestra(int palestraId);

    @Query("SELECT COUNT(*) FROM presencas WHERE palestra_id = :palestraId")
    int contarPorPalestra(int palestraId);

    @Query("SELECT COUNT(*) FROM presencas WHERE participante_id = :participanteId AND palestra_id = :palestraId")
    int jaConfirmou(int participanteId, int palestraId);
}
