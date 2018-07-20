package com.jeeva.notes.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jeeva.notes.data.dto.Note;

import java.util.List;

/**
 * Created by jeevanandham on 19/07/18
 */
@Dao
public interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long addNote(Note note);

    @Query("SELECT * FROM " + Note.TABLE_NAME + " ORDER BY datetime(" + Note.FIELD_UPDATED_TIME + ") DESC")
    LiveData<List<Note>> getNotes();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateNote(Note note);

    @Delete
    void deleteNote(Note note);
}