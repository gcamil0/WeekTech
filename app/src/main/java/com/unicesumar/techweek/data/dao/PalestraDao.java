package com.unicesumar.techweek.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.unicesumar.techweek.data.entity.Palestra;

import java.util.List;

@Dao
public interface PalestraDao {

    @Insert
    long inserir(Palestra palestra);

    @Update
    void atualizar(Palestra palestra);

    @Query("SELECT * FROM palestras ORDER BY horario ASC")
    List<Palestra> listarTodas();

    @Query("SELECT * FROM palestras WHERE favoritada = 1 ORDER BY horario ASC")
    List<Palestra> listarFavoritadas();

    @Query("UPDATE palestras SET favoritada = :favoritada WHERE id = :id")
    void atualizarFavorito(int id, boolean favoritada);

    @Query("SELECT * FROM palestras WHERE codigo_checkin = :codigo LIMIT 1")
    Palestra buscarPorCodigo(String codigo);

    @Query("SELECT * FROM palestras WHERE id = :id LIMIT 1")
    Palestra buscarPorId(int id);
}
