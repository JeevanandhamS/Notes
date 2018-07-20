package com.jeeva.notes.ui.editnotes;

import android.arch.lifecycle.ViewModel;

import com.jeeva.notes.data.dto.Note;
import com.jeeva.notes.repo.NotesRepo;

import org.threeten.bp.OffsetDateTime;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by jeevanandham on 19/07/18
 */
public class EditNotesViewModel extends ViewModel {

    @Inject
    NotesRepo mNotesRepo;

    private Note mNote;

    @Inject
    public EditNotesViewModel() {
    }

    public void setNote(Note note) {
        this.mNote = note;
    }

    public Note getNote() {
        return mNote;
    }

    public void addNote(String content) {
        mNote = new Note(content, OffsetDateTime.now());
        mNotesRepo.addNote(mNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    public void updateNote(String content) {
        mNote.setContent(content);
        mNote.setUpdatedTime(OffsetDateTime.now());
        mNotesRepo.updateNote(mNote)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }
}