package org.alex.scrivimi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import org.alex.scrivimi.fragments.FragmentsLoader;
import org.alex.scrivimi.fragments.OperationsFragment;

public class MainActivity extends AppCompatActivity implements FragmentsLoader {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragments(new OperationsFragment());
    }

    @Override
    public void loadFragments(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.workArea,fragment).commit();
    }
}