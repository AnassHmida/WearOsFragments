/*
 * Copyright (C) 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.wearable.jumpingjack.Fragments;

import com.example.android.wearable.jumpingjack.Model.Accounts;
import com.example.android.wearable.jumpingjack.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple fragment for showing the count
 */
public class MainFragment extends Fragment {

    ViewPager2 pager;
    Accounts accounts;

    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.incoming)
    TextView incoming;
    @BindView(R.id.acc_id)
    TextView accid;


    public MainFragment(ViewPager2 pager, Accounts accounts){
        this.pager = pager;
        this.accounts = accounts;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_layout, container, false);
        ButterKnife.bind(this,view);
        balance.setText(accounts.getBalance());
        date.setText(accounts.getDate());
        incoming.setText(accounts.getIncoming());
        accid.setText("Account ID : "+String.valueOf(accounts.getId()));
  /*      page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(1,true);

            }
        });

        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(2,true);

            }
        });*/
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }
}
