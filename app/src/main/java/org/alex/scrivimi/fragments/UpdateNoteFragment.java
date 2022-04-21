package org.alex.scrivimi.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.alex.scrivimi.R;
import org.alex.scrivimi.data.Note;
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
        return inflater.inflate(R.layout.fragment_update_note, container, false);
    }
}