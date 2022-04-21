package org.alex.scrivimi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import org.alex.scrivimi.R;
import org.alex.scrivimi.adapter.NoteListAdapter;
import org.alex.scrivimi.data.Note;
import org.alex.scrivimi.data.NoteManager;

import java.util.ArrayList;
import java.util.List;


public class ShowNotesFragment extends Fragment {
    private FragmentsLoader loader;

    private RecyclerView listNotesView;
    private View layout;
    public ShowNotesFragment() {
        // Required empty public constructor

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if(!(context instanceof FragmentsLoader))
            throw new UnsupportedOperationException("Not yet implemented");
        this.loader = (FragmentsLoader) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        layout =  inflater.inflate(R.layout.fragment_show_notes, container, false);
        layout.findViewById(R.id.backButton).setOnClickListener(source->this.loader.loadFragments(new OperationsFragment()));
        getNotes();
        return layout;
    }
    private void getNotes() {
        NoteManager manager = Room.databaseBuilder(getContext(),NoteManager.class,"scrivimi").build();
        manager.getDAO().getAll().observe(this,(listNotes->{
            listNotesView = layout.findViewById(R.id.noteList);
            NoteListAdapter adapter = new NoteListAdapter(listNotes,this.loader);
            listNotesView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
            listNotesView.setHasFixedSize(true);
            listNotesView.setAdapter(adapter);
   }));

    }
}