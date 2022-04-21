package org.alex.scrivimi.fragments;

import android.content.Context;
import android.os.Bundle;
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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UpdateNoteFragment extends Fragment {
private Note note;
private FragmentsLoader loader;
    public UpdateNoteFragment(Note note) {
        this.note = note;
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof FragmentsLoader))
            throw  new UnsupportedOperationException("Not Yer implemented");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_update_note, container, false);
        initComponents(layout,note);
        return layout;
    }
    private void initComponents(View layout,Note note) {
        EditText fieldTitle = layout.findViewById(R.id.noteTitleField);
        fieldTitle.setText(note.getTitolo());
        EditText fieldContent = layout.findViewById(R.id.contentText);
        fieldContent.setText(note.getTesto());
        layout.findViewById(R.id.updateBtn).setOnClickListener(source->{
            note.setTitolo(fieldTitle.getText().toString());
            note.setTesto(fieldContent.getText().toString());
            NoteManager manager = Room.databaseBuilder(getContext(),NoteManager.class,"scrivimi").build();
            ExecutorService updateService = Executors.newSingleThreadExecutor();
            updateService.submit(()->manager.getDAO().update(note));
            updateService.shutdown();
             Toast.makeText(getContext(),getString(R.string.update_ok),Toast.LENGTH_LONG).show();
        });

        layout.findViewById(R.id.backButton).setOnClickListener(source->{
            loader.loadFragments(new ShowNotesFragment());
        });
    }
}