package com.avinash_badramoni.GHMC;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        ViewPager viewPager = findViewById(R.id.viewPager);
        adapterViewPager = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapterViewPager);
        viewPager.setCurrentItem(1);

    }

    public static class MyPageAdapter extends FragmentPagerAdapter {
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
                       switch (i){

                           case 0:
                               return Events.newInstance();

                           case 1:
                               return HomeFragment.newInstance();

                               case 2:

                               return Profile.newInstance();


                       }

            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
