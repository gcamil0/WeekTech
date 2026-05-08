package com.unicesumar.techweek.data.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import com.unicesumar.techweek.data.entity.Participante;

import java.util.List;

@Dao
public interface ParticipanteDao {

    @Insert
    long inserir(Participante participante);

    @Update
    void atualizar(Participante participante);

    @Delete
    void deletar(Participante participante);

    @Query("SELECT * FROM participantes ORDER BY nome ASC")
    List<Participante> listarTodos();

    @Query("SELECT * FROM participantes WHERE ra = :ra LIMIT 1")
    Participante buscarPorRa(String ra);

    @Query("SELECT * FROM participantes WHERE coffee_break = 1 ORDER BY nome ASC")
    List<Participante> listarCoffeeBreak();

    @Query("SELECT COUNT(*) FROM participantes")
    int contarTotal();

    @Query("SELECT COUNT(*) FROM participantes WHERE coffee_break = 1")
    int contarCoffeeBreak();

    @Query("UPDATE participantes SET presenca_confirmada = 1 WHERE id = :id")
    void confirmarPresenca(int id);
}
