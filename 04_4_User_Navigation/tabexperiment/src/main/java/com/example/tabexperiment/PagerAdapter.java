package com.example.tabexperiment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

// this is to do with setting up tabs


public class PagerAdapter extends FragmentStatePagerAdapter {
    int numOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs=numOfTabs;
    }


    /*
    returns the frag associated with aspecififed position
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TabFragment1();
            case 1:
                return new TabFragment2();
            case 2:
                return new TabFragment3();
            default:
                return null;
        }
    }

    /*
    Return the number of views avialable
     */

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
