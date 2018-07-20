package com.jeeva.notes.repo;

import android.arch.lifecycle.LiveData;

import com.jeeva.notes.data.dto.Note;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by jeevanandham on 19/07/18
 */
public interface NotesRepo {

    Completable addNote(Note note);

    LiveData<List<Note>> getNotes();

    Completable updateNote(Note note);

    Completable deleteNote(Note note);
}