package com.avinash_badramoni.GHMC;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Profile extends Fragment {
    public static Profile newInstance(){
        Profile fragment = new Profile();
        return fragment;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.fragment_profile,container,false);
        return view;
    }
}
