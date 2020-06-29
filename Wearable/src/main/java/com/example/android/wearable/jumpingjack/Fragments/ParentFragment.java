package com.example.android.wearable.jumpingjack.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android.wearable.jumpingjack.Adapter.PagerAdapter;
import com.example.android.wearable.jumpingjack.Model.Accounts;
import com.example.android.wearable.jumpingjack.R;


public class ParentFragment extends Fragment  {

    private MainFragment mMainFragment;
    private List1Fragment List1Page;
    private List2Fragment List2Page;

    Accounts accounts;
    Context context;
     PagerAdapter adapter ;
    int i;
    private ViewPager2 verticalViewPager;


    public ParentFragment(Accounts accounts,Context context) {
        this.accounts = accounts;
        this.context = context;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_parent, container, false);
        verticalViewPager = rootView.findViewById(R.id.verticalview_pager);
      adapter =  new PagerAdapter(this);
        verticalViewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        verticalViewPager.registerOnPageChangeCallback (new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("API123", "onPageSelected " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mMainFragment = new MainFragment(verticalViewPager,accounts);
        List2Page = new List2Fragment(verticalViewPager,accounts);

        adapter.addFragment(mMainFragment);
        adapter.addFragment(List2Page);
        verticalViewPager.setAdapter(adapter);
        return rootView;
    }


    }

