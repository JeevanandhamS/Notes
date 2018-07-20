package com.jeeva.notes.di;

import com.jeeva.notes.ui.editnotes.EditNotesActivity;
import com.jeeva.notes.ui.noteslist.NotesListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by jeevanandham on 19/07/18
 */
@Module
public abstract class AppBinder {
    @ContributesAndroidInjector
    public abstract NotesListActivity bindNotesListActivity();

    @ContributesAndroidInjector
    public abstract EditNotesActivity bindEditNotesActivity();
}