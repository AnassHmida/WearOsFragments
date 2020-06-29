package com.example.android.wearable.jumpingjack.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android.wearable.jumpingjack.Fragments.ParentFragment;
import com.example.android.wearable.jumpingjack.Model.Accounts;

import java.util.ArrayList;
import java.util.List;

public class ParentViewPagerAdapter extends FragmentPagerAdapter {
List<Accounts> accounts;
private List<Fragment> mFragments;

    public ParentViewPagerAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mFragments = new ArrayList<>();
    }
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    public void addFragment(Fragment fragment) {
        mFragments.add(fragment);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}