package com.kipkoech.petfinderip.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kipkoech.petfinderip.R;
import com.kipkoech.petfinderip.models.Animal;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PetDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PetDetailFragment extends Fragment {
    @BindView(R.id.Image1)
    ImageView mImageLabel;
    @BindView(R.id.NameTextView)
    TextView mNameLabel;
    @BindView(R.id.TextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.savePetButton) TextView mSaveRestaurantButton;

    private Animal mRestaurant;

    public PetDetailFragment() {
        // Required empty public constructor
    }


    public static PetDetailFragment newInstance(Animal animal) {
        PetDetailFragment restaurantDetailFragment = new PetDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("animal", Parcels.wrap(animal));
        restaurantDetailFragment.setArguments(args);
        return restaurantDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mRestaurant = Parcels.unwrap(getArguments().getParcelable("animal"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_pet_detail, container, false);
        ButterKnife.bind(this, view);
        Picasso.get().load(mRestaurant.getUrl()).into(mImageLabel);

//        List<String> categories = new ArrayList<>();

//        for (Category category: mRestaurant.getCategories()) {
//            categories.add(category.getTitle());
//        }

        mNameLabel.setText(mRestaurant.getName());
//        mCategoriesLabel.setText(android.text.TextUtils.join(", ", categories));
        mRatingLabel.setText((mRestaurant.getCoat().toString()));
        mPhoneLabel.setText(mRestaurant.getContact().toString());
        mAddressLabel.setText(mRestaurant.getAge());

        return view;
    }
}