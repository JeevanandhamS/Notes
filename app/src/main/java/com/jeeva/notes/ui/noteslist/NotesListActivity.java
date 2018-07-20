package com.jeeva.notes.ui.noteslist;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jeeva.notes.R;
import com.jeeva.notes.di.NotesViewModelFactory;
import com.jeeva.notes.ui.editnotes.EditNotesActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jeevanandham on 19/07/18
 */
public class NotesListActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    @Inject
    NotesViewModelFactory mNotesViewModelFactory;

    private NotesListAdapter mNotesListAdapter;

    private NotesListViewModel mNotesListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        setupRecyclerView();

        setupFloatingActionButton();

        mNotesListViewModel = ViewModelProviders.of(this, mNotesViewModelFactory)
                .get(NotesListViewModel.class);
        mNotesListViewModel.getNoteList().observe(this,
                notes -> mNotesListAdapter.updateNotesList(notes));
    }

    private void setupRecyclerView() {
        RecyclerView rcvNotesList = findViewById(R.id.notes_list_rcv);
        rcvNotesList.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        rcvNotesList.setHasFixedSize(true);

        mNotesListAdapter = new NotesListAdapter(getLayoutInflater());
        rcvNotesList.setAdapter(mNotesListAdapter);

        mNotesListAdapter.observeItemClicks().subscribe(
                note -> EditNotesActivity.startActivity(this, note)
        );
    }

    private void setupFloatingActionButton() {
        FloatingActionButton fabAddButton = findViewById(R.id.notes_list_fab_add);
        fabAddButton.setOnClickListener(v -> EditNotesActivity.startActivityFresh(this));
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}