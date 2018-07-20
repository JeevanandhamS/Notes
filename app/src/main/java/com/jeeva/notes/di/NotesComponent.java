package com.jeeva.notes.di;

import com.jeeva.notes.NotesApplication;
import com.jeeva.notes.ui.noteslist.NotesListActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by jeevanandham on 19/07/18
 */
@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, NotesModule.class, AppBinder.class})
public interface NotesComponent {

    void inject(NotesApplication application);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(NotesApplication application);

        NotesComponent build();
    }
}