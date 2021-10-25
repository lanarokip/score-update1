package com.kipkoech.petfinderip.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kipkoech.petfinderip.Adapter.petListAdapter;
import com.kipkoech.petfinderip.R;
import com.kipkoech.petfinderip.models.Animal;
import com.kipkoech.petfinderip.models.SearchResponse;
import com.kipkoech.petfinderip.network.PetClient;
import com.kipkoech.petfinderip.network.interface1;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetListActivity extends AppCompatActivity {
    private static final String TAG = PetListActivity.class.getSimpleName();
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private petListAdapter mAdapter;

    public List<Animal> genders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
//        mLocationTextView.setText(gender+"animals");

        interface1 client = PetClient.getClient();
        Call<SearchResponse> call = client.getPets(gender);

        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    genders = response.body().getAnimals();
                    mAdapter = new petListAdapter(PetListActivity.this, genders);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(PetListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

//                    List<Animal> AnimalList = response.body().getAnimals();
//                    String[] Animal = new String[AnimalList.size()];
//                    String[] breed = new String[AnimalList.size()];
////
//                    for (int i = 0; i < Animal.length; i++){
//                        Animal[i] = AnimalList.get(i).getName();
//                    }
////
//                    for (int i = 0; i < breed.length; i++) {
//                        breed[i] = AnimalList.get(i).getAge();
//                    }
////
//                    ArrayAdapter adapter  = new PetArrayAdapter(PetListActivity.this, android.R.layout.simple_list_item_1,Animal,breed);
//                    mListView.setAdapter(adapter);

                    showRestaurants();

//                    mErrorTextView.setText("yeii");
//                    mErrorTextView.setVisibility(View.VISIBLE);
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                Log.e("Error Message", "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();
            }

        });
    }
    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
//        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}