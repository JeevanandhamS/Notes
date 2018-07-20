package com.jeeva.notes.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.jeeva.notes.ui.editnotes.EditNotesViewModel;
import com.jeeva.notes.ui.noteslist.NotesListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by jeevanandham on 19/07/18
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(EditNotesViewModel.class)
    abstract ViewModel bindEditNotesViewModel(EditNotesViewModel editNotesViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(NotesListViewModel.class)
    abstract ViewModel bindNotesListViewModel(NotesListViewModel notesListViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(NotesViewModelFactory factory);
}