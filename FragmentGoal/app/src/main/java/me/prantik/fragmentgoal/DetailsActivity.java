package me.prantik.fragmentgoal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        int pos = getIntent().getIntExtra("pos", 0);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putInt("pos", pos);

        DetailsFragment detailsFragment = new DetailsFragment();

        detailsFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.frame2, detailsFragment).commit();
    }
}