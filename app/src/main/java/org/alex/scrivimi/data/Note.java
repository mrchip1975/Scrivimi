package org.alex.scrivimi.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_nota")
    private int idNota;
    @ColumnInfo(name = "titolo")
    private String titolo;
    @ColumnInfo(name = "testo")
    private String testo;
    @ColumnInfo(name = "data_pubblicazione", defaultValue = "CURRENT_TIMESTAMP")
    private String dataPubblicazione;

    public int getIdNota() {
        return idNota;
    }

    public void setIdNota(int idNota) {
        this.idNota = idNota;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public String getDataPubblicazione() {
        return dataPubblicazione;
    }

    public void setDataPubblicazione(String  dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }
}
