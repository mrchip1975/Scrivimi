package org.alex.scrivimi.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Note.class}, version = 1)
public abstract class NoteManager extends RoomDatabase {
    public abstract DAO getDAO();
}
