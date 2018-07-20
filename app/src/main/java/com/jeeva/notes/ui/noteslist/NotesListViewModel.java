package com.jeeva.notes.ui.noteslist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jeeva.notes.data.dto.Note;
import com.jeeva.notes.repo.NotesRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jeevanandham on 19/07/18
 */
public class NotesListViewModel extends ViewModel {

    @Inject
    NotesRepo mNotesRepo;

    @Inject
    public NotesListViewModel() {
    }

    public LiveData<List<Note>> getNoteList() {
        return mNotesRepo.getNotes();
    }

    public void deleteNote(Note note) {
        mNotesRepo.deleteNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}

                    @Override
                    public void onComplete() {}

                    @Override
                    public void onError(Throwable e) {}
                });
    }
}