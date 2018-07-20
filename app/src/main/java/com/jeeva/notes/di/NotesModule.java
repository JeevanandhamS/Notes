package com.jeeva.notes.di;

import android.arch.persistence.room.Room;

import com.jeeva.notes.NotesApplication;
import com.jeeva.notes.data.dao.NoteDao;
import com.jeeva.notes.data.db.NoteDatabase;
import com.jeeva.notes.repo.NotesRepo;
import com.jeeva.notes.repo.NotesRepoImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

/**
 * Created by jeevanandham on 19/07/18
 */
@Module(includes = {AndroidInjectionModule.class, ViewModelModule.class})
public class NotesModule {

    @Provides
    NotesRepo providesNotesRepo(NoteDao noteDao) {
        return new NotesRepoImpl(noteDao);
    }

    @Provides
    @Singleton
    NoteDao providesNoteDao(NoteDatabase noteDatabase) {
        return noteDatabase.getNoteDao();
    }

    @Provides
    @Singleton
    NoteDatabase providesNoteDatabase(NotesApplication context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                NoteDatabase.class, "notes_db").build();
    }
}