package com.kipkoech.petfinderip;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class PetArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] animals;
    private String[] breed;


    public PetArrayAdapter(@NonNull Context context, int resource, String[] animals,  String[] breed) {
        super(context, resource);
        this.mContext = mContext;
        this.animals = animals;
        this.breed = breed;
    }

    @Override
    public Object getItem(int position){
        String animal = animals[position];
        String breeds = breed[position];

        return String.format( animal+"Was rescued"+breeds);
    }
    @Override
    public int getCount(){
        return animals.length;
    }
}
