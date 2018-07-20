package com.jeeva.notes.ui.noteslist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jeeva.notes.R;
import com.jeeva.notes.data.dto.Note;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by jeevanandham on 19/07/18
 */
public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.NotesListVH> {

    private LayoutInflater mInflater;

    private List<Note> mNoteList = new ArrayList<>();

    private PublishSubject<Note> mItemClick = PublishSubject.create();

    public NotesListAdapter(LayoutInflater inflater) {
        this.mInflater = inflater;
    }

    public Observable<Note> observeItemClicks() {
        return mItemClick;
    }

    @NonNull
    @Override
    public NotesListVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesListVH(mInflater.inflate(R.layout.inflater_notes_row,
                parent, false), mItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesListVH holder, int position) {
        holder.bind(mNoteList.get(position));
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }

    class NotesListVH extends RecyclerView.ViewHolder {

        TextView tvContent;

        public NotesListVH(View view, PublishSubject<Note> itemClick) {
            super(view);

            view.setOnClickListener(v -> itemClick.onNext(mNoteList.get(getAdapterPosition())));
            tvContent = view.findViewById(R.id.notes_row_tv_content);
        }

        public void bind(Note note) {
            tvContent.setText(note.getContent());
        }
    }

    public void updateNotesList(List<Note> notesList) {
        this.mNoteList = notesList;
        notifyDataSetChanged();
    }
}