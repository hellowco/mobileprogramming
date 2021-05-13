package com.example.stock;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ThemeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_theme, container, false);

        TabLayout tabLayout = view.findViewById(R.id.themeTableLayout);

        ViewPager2 viewPager2 = view.findViewById(R.id.themePage);

        ThemeAdapter themeAdapter = new ThemeAdapter(getActivity());

        for(int k=0; k<5; k++){
            Fragment frag = new ThemeList();
            themeAdapter.addFrag(frag);
        }

        viewPager2.setAdapter(themeAdapter);
        themeAdapter.notifyDataSetChanged();

        final String[] tabElement = new String[]{"1", "2", "3", "4", "5"};
        new TabLayoutMediator(tabLayout, viewPager2,
                ((tab, position) -> tab.setText(tabElement[position]))).attach();

        return view;
    }
}