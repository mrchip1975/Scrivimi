package org.alex.scrivimi.fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.alex.scrivimi.R;

import java.time.LocalDate;


public class OperationsFragment extends Fragment implements View.OnClickListener {
    FragmentsLoader loader;
    public OperationsFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_operations, container, false);
        changeNewNoteIcon(layout);
        initComponents(layout);
        return layout;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(!(context instanceof FragmentsLoader))
            throw new UnsupportedOperationException("Not Yet implemented");
        this.loader = (FragmentsLoader) context;
    }

    private void changeNewNoteIcon(View layout) {
        ImageView noteImage = layout.findViewById(R.id.newNoteImg);
        String monthName = LocalDate.now().getMonth().name().toLowerCase();
        int idDrawable = getResources().
                getIdentifier(monthName, "drawable", getActivity().getPackageName());
        noteImage.setImageResource(idDrawable);
    }
    private void initComponents(View layout) {
        layout.findViewById(R.id.newNoteImg).setOnClickListener(this);
        layout.findViewById(R.id.showNoteImg).setOnClickListener(this);
        layout.findViewById(R.id.aboutImg).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Fragment fragment = new Fragment();
        switch (id) {
            case R.id.newNoteImg:fragment = new NewNoteFragment();
                                                        break;
            case R.id.showNoteImg:fragment = new ShowNotesFragment();
                                                         break;
        }
        this.loader.loadFragments(fragment);
    }
}