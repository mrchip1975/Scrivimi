package org.alex.scrivimi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.room.Room;
import org.alex.scrivimi.R;
import org.alex.scrivimi.data.Note;
import org.alex.scrivimi.data.NoteManager;

import java.time.LocalDate;
import java.util.Objects;

public class NewNoteFragment extends Fragment {
    private FragmentsLoader loader;
    public NewNoteFragment() {
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
        View layout =  inflater.inflate(R.layout.fragment_new_note, container, false);
        layout.findViewById(R.id.saveButton).setOnClickListener(source->saveNote(layout));
        layout.findViewById(R.id.backButton).setOnClickListener(source->loader.loadFragments(new OperationsFragment()));
        return layout;
    }
    private void saveNote(View layout) {
        try {
            new Thread(()->{
                NoteManager manager = Room.databaseBuilder(getContext(), NoteManager.class,"scrivimi").build();
                manager.getDAO().add(buildNote(layout));
                manager.close();
                getActivity().runOnUiThread(()->{
                    Toast.makeText(getContext(),getString(R.string.save_ok),Toast.LENGTH_SHORT).show();
                });
            }).start();

        } catch (Exception ex) {
            Log.d("eccezione",ex.getMessage());
        }
    }
    private Note buildNote(View layout) {
        Note note = new Note();
        EditText field;
        note.setDataPubblicazione(LocalDate.now().toString());
        field = layout.findViewById(R.id.noteTitle);
        note.setTitolo(field.getText().toString());
        field = layout.findViewById(R.id.contentArea);
        note.setTesto(field.getText().toString());
        return note;
    }
}