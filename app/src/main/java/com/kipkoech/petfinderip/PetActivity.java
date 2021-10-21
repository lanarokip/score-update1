package com.kipkoech.petfinderip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetActivity extends AppCompatActivity {
    private static final String TAG = PetActivity.class.getSimpleName();
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView)
    ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("location");
        mLocationTextView.setText(gender+"animals");

        interface1 client = PetClient.getClient();
    }
}