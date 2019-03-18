package com.avinash_badramoni.GHMC;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makeramen.roundedimageview.RoundedImageView;


public class HomeFragment extends Fragment {
    public static HomeFragment newInstance(){
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    RoundedImageView roundedImageView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootview  =inflater.inflate(R.layout.fragment_home,container,false);


        roundedImageView =(RoundedImageView)rootview.findViewById(R.id.postacomp);
        roundedImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),PostAcomp.class);
                startActivity(intent);
            }
        });



        return rootview;
    }
}
