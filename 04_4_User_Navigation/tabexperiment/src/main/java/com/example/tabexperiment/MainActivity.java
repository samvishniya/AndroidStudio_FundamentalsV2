package com.example.tabexperiment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
// setting up and inflating the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // creating an instance of the tab layout from the view
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        // set text for each tab
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label3));
        // set tabs to fill layout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        // use PagerAdapter to manage page views in fragments
        // each page has its own frag
        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        // set a litener for clicks on pagevhange ?? and on tab to select tab

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new    TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }



        });

    }
}
