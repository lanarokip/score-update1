package com.kipkoech.petfinderip.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kipkoech.petfinderip.models.Animal;
import com.kipkoech.petfinderip.ui.PetDetailFragment;

import java.util.List;

public class PetPagerAdapter extends FragmentPagerAdapter {
    private List<Animal> mPets;

    public PetPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Animal> Pets) {
        super(fm, behavior);
        mPets = Pets;
    }

    @Override
    public Fragment getItem(int position) {
        return PetDetailFragment.newInstance(mPets.get(position));
    }

    @Override
    public int getCount() {
        return mPets.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mPets.get(position).getName();
    }
}
