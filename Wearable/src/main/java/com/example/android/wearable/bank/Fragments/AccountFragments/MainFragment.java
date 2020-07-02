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

package com.example.android.wearable.bank.Fragments.AccountFragments;

import com.example.android.wearable.bank.Model.Accounts.Accounts;
import com.example.android.wearable.jumpingjack.R;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple fragment for showing the count
 */
public class MainFragment extends Fragment{

    ViewPager2 pager;
    Accounts accounts;

    @BindView(R.id.balance)
    TextView balance;
    @BindView(R.id.balance_card_view)
    CardView balance_card_view;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.incoming)
    TextView incoming;
    @BindView(R.id.acc_id)
    TextView accid;



    public MainFragment(){

    }

public void setBalance(String number){
    balance.setText(number);
    if(number.contains("-")){
        balance_card_view.setCardBackgroundColor(getResources().getColor(R.color.flat_red));
    }
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_layout, container, false);
        ButterKnife.bind(this,view);
        Accounts accounts = (Accounts) getArguments().getSerializable("Accounts");
        setBalance(accounts.getOnlineBalance());
        date.setText("mis à jour le "+accounts.getCreationDate());
        incoming.setText(accounts.getOpenBalance());
        if(accounts.getOpenBalance().contains("-")){
            incoming.setTextColor(getResources().getColor(R.color.flat_red));
        }
        accid.setText("Account ID : "+String.valueOf(accounts.getBankAccountId()));
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
