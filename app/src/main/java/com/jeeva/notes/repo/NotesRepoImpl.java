package com.jeeva.notes.repo;

import android.arch.lifecycle.LiveData;

import com.jeeva.notes.data.dao.NoteDao;
import com.jeeva.notes.data.dto.Note;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;

/**
 * Created by jeevanandham on 19/07/18
 */
public class NotesRepoImpl implements NotesRepo {

    @Inject
    NoteDao mNoteDao;

    public NotesRepoImpl(NoteDao noteDao) {
        this.mNoteDao = noteDao;
    }

    @Override
    public Completable addNote(final Note note) {
        return Completable.fromAction(() -> note.setId((int) mNoteDao.addNote(note)));
    }

    @Override
    public LiveData<List<Note>> getNotes() {
        return mNoteDao.getNotes();
    }

    @Override
    public Completable updateNote(Note note) {
        return Completable.fromAction(() -> mNoteDao.updateNote(note));
    }

    @Override
    public Completable deleteNote(Note note) {
        return Completable.fromAction(() -> mNoteDao.deleteNote(note));
    }
}