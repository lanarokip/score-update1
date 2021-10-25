package com.kipkoech.petfinderip.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kipkoech.petfinderip.Constants;
import com.kipkoech.petfinderip.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private  String mRecentGender;

    private DatabaseReference mSearchedlocationReference;

    @BindView(R.id.findRestaurantsButton)
    Button mFindRestaurantsButton;
    @BindView(R.id.locationEditText)
    EditText mLocationEditText;
    @BindView(R.id.savedPetButton) Button savedPetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        instance of database reference

        mSearchedlocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);


        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        mFindRestaurantsButton.setOnClickListener(this);
        savedPetButton.setOnClickListener(this);
        mRecentGender= mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY,null);
        Log.d("Recent-Adress-gender","Here is the message"+ mRecentGender);


    }

    @Override
    public void onClick(View v) {

        if(v == mFindRestaurantsButton){
            if(mFindRestaurantsButton.length()==0){
                Intent intent = new Intent(MainActivity.this,PetListActivity.class);
                intent.putExtra("gender",mRecentGender);

//                saving to database
                saveLocationToFirebase(mRecentGender);
                startActivity(intent);

            }
            else{
            String gender = mLocationEditText.getText().toString();
            addToSharedPreferences(gender);

//            saving to database

                saveLocationToFirebase(gender);
            Intent intent = new Intent(MainActivity.this,PetListActivity.class);
            startActivity(intent);}

        }
        if(v == savedPetButton){
            Intent intent = new Intent(MainActivity.this,PetListActivity.class);
            intent.putExtra("gender",mRecentGender);
            saveLocationToFirebase(mRecentGender);
            startActivity(intent);
        }
    }

    public void saveLocationToFirebase( String gender){
        mSearchedlocationReference.setValue(gender);
    }

    private void addToSharedPreferences(String gender) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY,gender).apply();
    }
}