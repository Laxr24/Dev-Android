package com.example.uitheme;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PageAdapter extends FragmentStatePagerAdapter {

    public PageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position == 0){
            return new AFragment();
        }
        else if(position == 1){
            return new BFragment();
        }
        else{
            return new CFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
