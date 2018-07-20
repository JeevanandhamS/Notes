package com.jeeva.notes.ui.editnotes;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.EditText;

import com.jeeva.notes.R;
import com.jeeva.notes.data.dto.Note;
import com.jeeva.notes.di.NotesViewModelFactory;
import com.jeeva.notes.ui.noteslist.NotesListViewModel;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by jeevanandham on 19/07/18
 */
public class EditNotesActivity extends AppCompatActivity implements HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;

    private static final String NOTE_DATA_KEY = "noteData";

    private EditText mEtContent;

    @Inject
    NotesViewModelFactory mNotesViewModelFactory;

    private NotesListViewModel mNotesListViewModel;

    private EditNotesViewModel mEditNotesViewModel;

    public static void startActivity(Context context, Note note) {
        Intent intent = new Intent(context, EditNotesActivity.class);
        intent.putExtra(NOTE_DATA_KEY, note);
        context.startActivity(intent);
    }

    public static void startActivityFresh(Context context) {
        context.startActivity(new Intent(context, EditNotesActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        mEtContent = findViewById(R.id.edit_notes_et_content);

        mNotesListViewModel = ViewModelProviders.of(this, mNotesViewModelFactory)
                .get(NotesListViewModel.class);

        mEditNotesViewModel = ViewModelProviders.of(this, mNotesViewModelFactory)
                .get(EditNotesViewModel.class);

        Note note = unBundleNote();

        if (null != note) {
            mEtContent.setText(note.getContent());
            mEditNotesViewModel.setNote(note);
        } else {
            mEditNotesViewModel.addNote("");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private Note unBundleNote() {
        Bundle bundle = getIntent().getExtras();
        if (null != bundle && bundle.containsKey(NOTE_DATA_KEY)) {
            return (Note) bundle.getSerializable(NOTE_DATA_KEY);
        }

        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        String content = mEtContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            mNotesListViewModel.deleteNote(mEditNotesViewModel.getNote());
        } else {
            mEditNotesViewModel.updateNote(content);
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}