package org.alex.scrivimi.data;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

@Dao
public interface DAO {
    @Insert
    void add(Note newNote);
    @Query("DELETE FROM note WHERE id_nota=:id")
    void delete(int id);
    @Update
    void update(Note updatedNote);
    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAll();
}
