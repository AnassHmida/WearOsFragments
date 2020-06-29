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

import com.example.android.wearable.jumpingjack.Adapter.PagerAdapter;
import com.example.android.wearable.jumpingjack.Model.Accounts;
import com.example.android.wearable.jumpingjack.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import androidx.wear.widget.WearableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class List1Fragment extends Fragment {
    PagerAdapter adapter;
    @BindView(R.id.back)
    Button back;
    @BindView(R.id.recycler_list_view)
    WearableRecyclerView rv;
    ViewPager2 pager;
    Accounts accounts;
public List1Fragment(ViewPager2 pager, Accounts accounts){
    this.pager = pager;
    this.accounts = accounts;
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.list1_layout, container, false);
        ButterKnife.bind(this, view);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        pager.setCurrentItem(0, true);
            }
        });
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        List<String> dataset = new ArrayList<String>();
        dataset.add("Lorem");
        dataset.add("Satori");
        dataset.add("Something");
        dataset.add("Android");
        dataset.add("Wear");
        dataset.add("Ez");
//        MyAdapter adapter = new MyAdapter(getContext(),dataset);
        //rv.setAdapter(adapter);


        return view;
    }

}
