package com.example.android.wearable.bank.Activities.Main;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;
import androidx.wear.ambient.AmbientModeSupport;

import com.example.android.wearable.bank.Adapters.AccountsAdapter;
import com.example.android.wearable.bank.Fragments.AccountFragments.Support.SupportFragment;
import com.example.android.wearable.bank.Fragments.ParentFragment;
import com.example.android.wearable.bank.Model.Accounts.Account;
import com.example.android.wearable.bank.Model.Accounts.Accounts;
import com.example.android.wearable.bank.Model.Login.Login;
import com.example.android.wearable.jumpingjack.R;

import java.util.List;

import io.paperdb.Paper;


public class MainActivity extends FragmentActivity
        implements AmbientModeSupport.AmbientCallbackProvider,SupportFragment.DataSentListener {

    private static final String TAG = "MainActivity";

private int SelectedParentPage = 0;
    private LinearLayout ll;
    private ViewPager2 mPager;
    private AccountsAdapter adapter;
    private ParentFragment mParentFragment;
    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity_layout);
        Paper.init(this);
        AmbientModeSupport.attach(this);
        mainViewModel = new ViewModelProvider.NewInstanceFactory().create(MainViewModel.class);
        SubscribeObservers();
        Login login = Paper.book().read("login");
        mainViewModel.GetAllAccounts(new Account(login.getOtp(),"All"));
    }

    public ParentFragment newAccountsInstance(Accounts accounts) {
        ParentFragment parentFragment = new ParentFragment(accounts);
        Bundle argument = new Bundle();
        argument.putString("ID", accounts.getId());
        parentFragment.setArguments(argument);
        return parentFragment;
    }

    private void SubscribeObservers(){
        mainViewModel.observeAuthState().observe( this, new androidx.lifecycle.Observer<MainResource<List<Accounts>>>() {
            @Override
            public void onChanged(MainResource<List<Accounts>> accountMainResource) {
                if(accountMainResource != null){
                    switch (accountMainResource.status){
                        case LOADING:{

                            break;
                        }
                        case DATARECEIVED:
                            Log.d(TAG, "onChanged:  LOGIN SUCESS" + accountMainResource.data);
                            Paper.book().write("accounts", accountMainResource.data);
                            setupAccountsViews();
                            break;
                        case ERROR:{
                            Log.d(TAG, "onChanged: "+"mochkla");
                            //Toast.makeText(LoginActivity.this,userLoginResource.message +" \n , Did you enter a number between 1 and 10 ?", Toast.LENGTH_LONG).show();
                            break;
                        }case NOT_AUTHENTICATED:{
                           // showError("network error","");
                            break;
                        }
                    }
                }
            }
        });
    }


/*    private void setupViews() {


        List<Accounts> accounts= new Accounts().dummyAccounts();


        mPager = findViewById(R.id.pager);
       adapter = new AccountsAdapter(getSupportFragmentManager(),getLifecycle());
        SetAccountIndicators(accounts);
        for (Accounts acc:accounts) {
            mParentFragment = newInstance(acc);
            adapter.addFragment(mParentFragment);
        }
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                SelectedParentPage = i;
                ImageView img = (ImageView) findViewById(R.id.indicatorlayout).findViewWithTag(i);
                resetIndicators(accounts);
                img.setImageResource(R.drawable.full_10);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // No-op.
            }
        });

        mPager.setAdapter(adapter);
    }*/

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void setupAccountsViews() {

        List<Accounts> accountss = Paper.book().read("accounts");
        mPager = findViewById(R.id.pager);

        adapter = new AccountsAdapter(getSupportFragmentManager(),getLifecycle());
        SetAccountsIndicators(accountss);
        for (Accounts acc:accountss) {
            mParentFragment = newAccountsInstance(acc);
            adapter.addFragment(mParentFragment);
        }
        mPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                SelectedParentPage = i;
                ImageView img = (ImageView) findViewById(R.id.indicatorlayout).findViewWithTag(i);
                resetIndicators(accountss);
                img.setImageResource(R.drawable.full_10);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                // No-op.
            }
        });

        mPager.setAdapter(adapter);
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (Log.isLoggable(TAG, Log.DEBUG)) {
            Log.d(TAG, "Unregistered for sensor events");
        }
    }



    private void SetAccountsIndicators(List<Accounts> accounts){
        ll = (LinearLayout)findViewById(R.id.indicatorlayout);
        for(int i=0;i<accounts.size();i++)
        {
            ImageView ii= new ImageView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMarginEnd(5);
            ii.setTag(i);
            ii.setLayoutParams(params);
            ii.setImageResource(R.drawable.empty_10);
            ll.addView(ii);
        }
    }

    private void SetAccountIndicators(List<Accounts> accounts){
        ll = (LinearLayout)findViewById(R.id.indicatorlayout);
        for(int i=0;i<accounts.size();i++)
        {
            ImageView ii= new ImageView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMarginEnd(5);
            ii.setTag(i);
            ii.setLayoutParams(params);
            ii.setImageResource(R.drawable.empty_10);
            ll.addView(ii);
        }
    }

    private void resetIndicators(List<Accounts> accounts){
        for(int i=0;i<accounts.size();i++)
        {
            ImageView img = (ImageView) findViewById(R.id.indicatorlayout).findViewWithTag(i);
            img.setImageResource(R.drawable.empty_10);
        }

    }

    @Override
    public AmbientModeSupport.AmbientCallback getAmbientCallback() {
        return new MyAmbientCallback();
    }

    @Override
    public void ApplyText(String Text,Accounts accounts) {


     ParentFragment parentFragment = (ParentFragment)adapter.createFragment(SelectedParentPage);
     parentFragment.dostuff(Text,accounts);
    }

        /** Customizes appearance for Ambient mode. (We don't do anything minus default.) */
    private class MyAmbientCallback extends AmbientModeSupport.AmbientCallback {
        /** Prepares the UI for ambient mode. */
        @Override
        public void onEnterAmbient(Bundle ambientDetails) {
            super.onEnterAmbient(ambientDetails);
        }

        /**
         * Updates the display in ambient mode on the standard interval. Since we're using a custom
         * refresh cycle, this method does NOT update the data in the display. Rather, this method
         * simply updates the positioning of the data in the screen to avoid burn-in, if the display
         * requires it.
         */
        @Override
        public void onUpdateAmbient() {
            super.onUpdateAmbient();
        }

        /** Restores the UI to active (non-ambient) mode. */
        @Override
        public void onExitAmbient() {
            super.onExitAmbient();
        }
    }

}
