package com.example.stock;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Arrays;
import java.util.List;

public class RecommendFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommend, container, false);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPager2 viewPager2 = view.findViewById(R.id.pager);
        Fragment frag1 = new RecommendFirst();
        Fragment frag2 = new RecommendSecond();
        Fragment frag3 = new RecommendThird();
        RecommendAdapater recommendAdapater = new RecommendAdapater(getActivity());
        recommendAdapater.addFrag(frag1);
        recommendAdapater.addFrag(frag2);
        recommendAdapater.addFrag(frag3);
        viewPager2.setAdapter(recommendAdapater);
        recommendAdapater.notifyDataSetChanged();


        final String[] tabElement = new String[]{"추천1", "추천2", "추천3"};

        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> tab.setText(tabElement[position]))
                .attach();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) { }
    @Override
    public void onDestroyView() { super.onDestroyView(); }

}