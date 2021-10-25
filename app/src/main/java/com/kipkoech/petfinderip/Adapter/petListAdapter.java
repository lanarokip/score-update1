package com.kipkoech.petfinderip.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kipkoech.petfinderip.R;
import com.kipkoech.petfinderip.models.Animal;
import com.kipkoech.petfinderip.models.Photo;
import com.kipkoech.petfinderip.ui.PetDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class petListAdapter  extends RecyclerView.Adapter<petListAdapter.petViewHolder>{
    private List<Animal> mPets;
    private Context mContext;

    public petListAdapter(Context context, List<Animal> pets) {
        mContext = context;
        mPets = pets;
    }

    @Override
    public petListAdapter.petViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_list_item,parent,false);
        petViewHolder viewHolder = new petViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder( petListAdapter.petViewHolder holder, int position) {
holder.bindRestaurant(mPets.get(position));
    }

    @Override
    public int getItemCount() {
        return mPets.size();
    }

    public class petViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.restaurantImageView)
        ImageView mPetImageView;
        @BindView(R.id.restaurantNameTextView)
        TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;

//        public void bindPets(Animal animals){
//
//        }

        public petViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        public void bindRestaurant(Animal gender) {
            mNameTextView.setText(gender.getName());
            mCategoryTextView.setText(gender.getGender());
            mRatingTextView.setText("description " + gender.getDescription());

            for(int i=0;i<gender.getPhotos().size();i++){
                Log.d("Photo","photo sizes"+gender.getPhotos().get(i));
                String photo = gender.getPhotos().get(i).getFull();
            Picasso.get().load(photo).into(mPetImageView);
            }
        }

        @Override
        public void onClick(View v) {

            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PetDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mNameTextView));
            mContext.startActivity(intent);
        }
    }
}
