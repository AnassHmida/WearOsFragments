package com.example.android.wearable.jumpingjack.fragments;





import com.example.android.wearable.jumpingjack.Adapter.MyAdapter;
import com.example.android.wearable.jumpingjack.PagerAdapter;
import com.example.android.wearable.jumpingjack.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import java.util.ArrayList;
import java.util.List;


public class List2Fragment extends Fragment {
    PagerAdapter adapter;
    ViewPager pager;
    public List2Fragment(ViewPager pager){
        this.pager = pager;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list2_layout, container, false);
        Button back = view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0, true);
            }
        });
        WearableRecyclerView rv = view.findViewById(R.id.recycler_list_view2);
        rv.setEdgeItemsCenteringEnabled(true);
        rv.setLayoutManager(new WearableLinearLayoutManager(getContext()));
        List<String> dataset = new ArrayList<String>();
        dataset.add("This");
        dataset.add("is");
        dataset.add("the");
        dataset.add("second");
        dataset.add("one");
        dataset.add("Ez");
        MyAdapter adapter = new MyAdapter(getContext(),dataset);
        rv.setAdapter(adapter);


        return view;
    }

}
