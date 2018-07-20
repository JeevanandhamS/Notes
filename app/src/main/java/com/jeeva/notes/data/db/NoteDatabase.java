package com.jeeva.notes.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.jeeva.notes.data.dao.NoteDao;
import com.jeeva.notes.data.dto.Note;

/**
 * Created by jeevanandham on 19/07/18
 */
@Database(entities = {Note.class}, version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class NoteDatabase extends RoomDatabase {

    public abstract NoteDao getNoteDao();
}