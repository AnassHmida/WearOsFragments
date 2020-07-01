package com.example.android.wearable.bank.Fragments.AccountFragments;





import com.example.android.wearable.bank.Adapters.TransactionsListAdapter;
import com.example.android.wearable.bank.Adapters.AccountsAdapter;
import com.example.android.wearable.bank.Model.Accounts;
import com.example.android.wearable.bank.Model.Transactions;
import com.example.android.wearable.jumpingjack.R;


import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import androidx.wear.widget.WearableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TransactionsFragment extends Fragment {
    AccountsAdapter adapter;
    Accounts accounts;
    GestureDetector gestureDetector;
 /*   @BindView(R.id.back)
    TextView back;*/
    @BindView(R.id.recycler_list_view2)
            WearableRecyclerView rv;
    ViewPager2 pager;
    public TransactionsFragment(ViewPager2 pager, Accounts accounts){
        this.pager = pager;
        this.accounts = accounts;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transactions_layout, container, false);
        //gestureDetector = new GestureDetector(getActivity(), new GestureListener());
        ButterKnife.bind(this, view);
     //   ViewCompat.setNestedScrollingEnabled(rv, false);
    /*    back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pager.setCurrentItem(0, true);
            }
        });*/
       // rv.setEdgeItemsCenteringEnabled(true);
     /*   rv.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                rv.dispatchNestedFling(velocityX, velocityY, false);
                return false;
            }
        });*/
        rv.setLayoutManager(new LinearLayoutManager(getContext()));

        TransactionsListAdapter adapter = new TransactionsListAdapter(getContext(),accounts.getTransactions());
        rv.setAdapter(adapter);


        return view;
    }

/*    public class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private final int Y_BUFFER = 10;

        @Override
        public boolean onDown(MotionEvent e) {
            // Prevent ViewPager from intercepting touch events as soon as a DOWN is detected.
            // If we don't do this the next MOVE event may trigger the ViewPager to switch
            // tabs before this view can intercept the event.
            Log.d("vp", "true1");
            rv.getParent().requestDisallowInterceptTouchEvent(true);
            return super.onDown(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (Math.abs(distanceX) > Math.abs(distanceY)) {
                Log.d("vp2", "true");
                // Detected a horizontal scroll, allow the viewpager from switching tabs
                rv.getParent().requestDisallowInterceptTouchEvent(false);
            } else if (Math.abs(distanceY) > Y_BUFFER) {
                // Detected a vertical scroll prevent the viewpager from switching tabs
                Log.d("vp3", "false");
                rv.getParent().requestDisallowInterceptTouchEvent(true);
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            return false;
        }

}*/

}
