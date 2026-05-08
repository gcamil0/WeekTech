package com.unicesumar.techweek.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(
    tableName = "presencas",
    foreignKeys = {
        @ForeignKey(
            entity = Participante.class,
            parentColumns = "id",
            childColumns = "participante_id",
            onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
            entity = Palestra.class,
            parentColumns = "id",
            childColumns = "palestra_id",
            onDelete = ForeignKey.CASCADE
        )
    },
    indices = {
        @Index("participante_id"),
        @Index("palestra_id")
    }
)
public class Presenca {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "participante_id")
    public int participanteId;

    @ColumnInfo(name = "palestra_id")
    public int palestraId;

    public Presenca() {}

    public Presenca(int participanteId, int palestraId) {
        this.participanteId = participanteId;
        this.palestraId = palestraId;
    }
}
