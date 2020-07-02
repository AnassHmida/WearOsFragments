package com.example.android.wearable.bank.Fragments;

import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android.wearable.bank.Adapters.AccountsAdapter;
import com.example.android.wearable.bank.Fragments.AccountFragments.SupportFragment;
import com.example.android.wearable.bank.Fragments.AccountFragments.TransactionsFragment;
import com.example.android.wearable.bank.Fragments.AccountFragments.MainFragment;
import com.example.android.wearable.bank.Model.Accounts;
import com.example.android.wearable.bank.Model.Accountss;
import com.example.android.wearable.jumpingjack.R;

import java.util.ArrayList;


public class ParentFragment extends Fragment  {

    private MainFragment mMainFragment;
    private SupportFragment supportFragment;
    private TransactionsFragment transactionsFragment;

    Accountss accountss;
    Context context;
     AccountsAdapter adapter ;
    int i;
    private ViewPager2 verticalViewPager;
    private static final String TAG = "ParentFragment";

    public ParentFragment(Accountss accounts) {
        this.accountss = accounts;


    }
    public void dostuff(String Text,Accounts accounts){
        mMainFragment.setBalance(Text);
    }

    public Fragment newInstance(Fragment fragment,Accountss accounts,int type) {
        Bundle argument = new Bundle();
        argument.putSerializable("Accounts", accounts);

        switch(type) {
            case 0:
                MainFragment mainFragment = new MainFragment();
                mainFragment.setArguments(argument);
                return mainFragment;

            case 1:

            TransactionsFragment transactionsFragment = new TransactionsFragment();
            // argument.putString("ID", accounts.getId());
            transactionsFragment.setArguments(argument);
            return transactionsFragment;

            case 2:
                SupportFragment supportFragment  = new SupportFragment();

                // argument.putString("ID", accounts.getId());
                supportFragment.setArguments(argument);
                return supportFragment;


            default:
                // code block
                return new Fragment();
        }




    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_parent, container, false);
        verticalViewPager = rootView.findViewById(R.id.verticalview_pager);
      adapter =  new AccountsAdapter(this);
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

        mMainFragment = (MainFragment)newInstance(mMainFragment,accountss,0);
        transactionsFragment = (TransactionsFragment)newInstance(transactionsFragment,accountss,0);
        // = (MainFragment)newInstance(mMainFragment,accountss,1);
        //mMainFragment = (MainFragment)newInstance(mMainFragment,accountss,0);
     //   mMainFragment = new MainFragment(verticalViewPager,accountss);
        //ransactionsFragment = new TransactionsFragment(verticalViewPager,accountss);
     // supportFragment = new SupportFragment(verticalViewPager,accountss);
        adapter.addFragment(mMainFragment);
      //  adapter.addFragment(transactionsFragment);
       // adapter.addFragment(supportFragment);
        verticalViewPager.setAdapter(adapter);

        return rootView;
    }


    }

