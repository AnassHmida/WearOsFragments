package com.example.android.wearable.bank.Fragments.AccountFragments.Transactions;






import com.example.android.wearable.bank.Activities.Main.MainViewModel;
import com.example.android.wearable.bank.Adapters.AccountsAdapter;
import com.example.android.wearable.bank.Adapters.TransactionsListAdapter;
import com.example.android.wearable.bank.Model.Accounts.Accounts;
import com.example.android.wearable.bank.Model.Login.Login;
import com.example.android.wearable.bank.Model.Transaction.Transaction;
import com.example.android.wearable.bank.Model.Transaction.Transactions;
import com.example.android.wearable.jumpingjack.R;
import com.todkars.shimmer.ShimmerRecyclerView;


import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;
import rx.Observer;


public class TransactionsFragment extends Fragment {

    Accounts accounts;
    GestureDetector gestureDetector;
    TransactionsListAdapter adapter;
    ArrayList<Transactions> jack,jack2;
    @BindView(R.id.errordialog)
    RelativeLayout errorRL;
    TransactionsViewModel transactionViewModel;
    List<Transactions> SavedStateTransactionData;
    private static final String TAG = "TransactionsFragment";
 /*   @BindView(R.id.back)

    TextView back;*/
    @BindView(R.id.recycler_list_view2)
            ShimmerRecyclerView rv;
    ViewPager2 pager;
    public TransactionsFragment(){

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("Transactions", (ArrayList<? extends Parcelable>) jack);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SubscribeOberservers();
        if (savedInstanceState != null) {
            Log.d(TAG, "onActivityCreated: "+"Problem 1");

          jack = savedInstanceState.getParcelableArrayList("Transactions");
          jack2 = new ArrayList<>();
          jack2 = Paper.book().read(accounts.getId());
          //  Log.d(TAG, "onActivityCreated: "+jack2.get(0).getAmount());
          if(jack!=null){
              adapter.SetTransactionData(jack);
              rv.hideShimmer();
          }else if(jack2==null){
              Log.d(TAG, "onActivityCreated: We have a problemo");

          }else if(!jack2.isEmpty()){
                  adapter.SetTransactionData(jack2);
                  rv.hideShimmer();

          }
            } else {
            if (jack!=null) {
                Log.d(TAG, "onActivityCreated: "+"Problem 2");
                    adapter.SetTransactionData(jack);
                    rv.hideShimmer();
                } else {
                Log.d(TAG, "onActivityCreated: "+"Problem 3");

                    Login login = Paper.book().read("login");
                    transactionViewModel.getTransactionsPerAccount(
                            new Transaction(login.getOtp(),accounts.getId(),5)
                    );

                }
        }
    }
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transactions_layout, container, false);
        ButterKnife.bind(this, view);
        accounts = (Accounts) getArguments().getSerializable("Accounts");
        rv.setLayoutManager(new LinearLayoutManager(getActivity()), R.layout.transaction_item_shimmer);
        transactionViewModel = new ViewModelProvider.NewInstanceFactory().create(TransactionsViewModel.class);
        SubscribeOberservers();
        adapter = new TransactionsListAdapter(getContext(),new ArrayList<>());
        rv.setAdapter(adapter);
        rv.showShimmer();
        return view;
    }
@OnClick(R.id.img)
public void retry(){
        errorRL.setVisibility(View.GONE);
    Login login = Paper.book().read("login");
    transactionViewModel.getTransactionsPerAccount(
            new Transaction(login.getOtp(),accounts.getId(),5)
    );
    rv.showShimmer();
}
    private void SubscribeOberservers(){

      //  transactionViewModel.observeTransactionsState().removeObservers(getViewLifecycleOwner());
        transactionViewModel.observeTransactionsState().observe(getViewLifecycleOwner(), new androidx.lifecycle.Observer<TransactionsResource<List<Transactions>>>() {
            @Override
            public void onChanged(TransactionsResource<List<Transactions>> transactionsResource) {
                if(transactionsResource != null){
                    switch(transactionsResource.status){
                        case DATARECEIVED:{
                            Log.d(TAG, "onChanged: Successfully got all of the data, TITLE : " + transactionsResource.data);
                          //  Log.d(TAG, "onChanged: "+transactionsResource.data.get(0).getAmount());
                            rv.hideShimmer();
                            jack = (ArrayList)transactionsResource.data;
                            adapter.SetTransactionData(transactionsResource.data);

                            break;
                        }
                        case ERROR:{

                        //    Log.d(TAG, "onChanged:  Error ! " + transactionsResource.message);
                          errorRL.setVisibility(View.VISIBLE);
                          rv.hideShimmer();
                            break;

                        }case LOADING:{
                          //  Log.d(TAG, "onChanged:  Loading data ...");
                            break;
                        }
                        case NOT_AUTHENTICATED:
                        {
                            break;
                        }
                    }
                }
            }
        });
    }

}
