package com.example.android.wearable.bank.Fragments.AccountFragments.Transactions;




import android.util.Log;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android.wearable.bank.DataSources.LoginClient;
import com.example.android.wearable.bank.DataSources.TransactionsClient;
import com.example.android.wearable.bank.Fragments.AccountFragments.Transactions.TransactionsResource;
import com.example.android.wearable.bank.Model.Accounts.Accounts;
import com.example.android.wearable.bank.Model.Login.Login;
import com.example.android.wearable.bank.Model.Login.User;
import com.example.android.wearable.bank.Model.Transaction.Transaction;
import com.example.android.wearable.bank.Model.Transaction.Transactions;
import com.example.android.wearable.bank.Network.LoginService;
import com.example.android.wearable.bank.Network.TransactionsService;

import java.util.List;

import io.paperdb.Paper;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class TransactionsViewModel  extends ViewModel {
    private TransactionsService transactionsService;
    private static final String TAG = "AuthViewModel";
    private String Authenticated ;
    private rx.Subscription subscription;
    final MutableLiveData<TransactionsResource<List<Transactions>>> result = new MutableLiveData<>();
    public TransactionsViewModel(){
    }

    public TransactionsViewModel(TransactionsService transactionsService){
        this.transactionsService = transactionsService;
    }

    public void getTransactionsPerAccount(Transaction transaction){
        subscription = TransactionsClient.getInstance().getTransactionsPerAccount(transaction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Transactions>>() {

                    @Override
                    public void onNext(List<Transactions> transactions) {
                        result.setValue(TransactionsResource.datareceived(transactions));
                        Paper.book().write(transaction.getBankAccountId(), transactions);
                         Log.d(TAG, "onNext: " + transactions.get(0).getAmount());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "onError: "+e.getMessage());
                        result.setValue(TransactionsResource.error("error",null));
                    }

                });
    }
/*
    public LiveData<LoginResource<Login>> AuthenticateWithUser(User userId){
        Log.d(TAG, "AuthenticateWithId: Attempt to login");
        return queryUserDetails(userId);

    }
*/

    /*  public LiveData<LoginResource<Login>> queryUserDetails(User userId){
          return  LiveDataReactiveStreams.fromPublisher(loginService.getLoginResponse(userId)
                  .onErrorReturn(new Function<Throwable, Login>() {
                      @Override
                      public Login apply(Throwable throwable) throws Exception {
                          Login errorUser = new Login();
                          errorUser.setOtp(null);
                          return errorUser;

                      }
                  })

                  .map(new Function<Login, LoginResource<Login>>() {
                      @Override
                      public LoginResource<Login> apply(Login user) throws Exception {
                          if(user.getOtp() ==null){
                              result.setValue(null);
                              return LoginResource.error("Could not Authenticate",(Login) null);
                          }
                          result.setValue(LoginResource.authenticated(user));
                          return LoginResource.authenticated(user);
                      }
                  }).subscribeOn(Schedulers.io()));
      }*/
    public LiveData<TransactionsResource<List<Transactions>>> observeTransactionsState(){
        return result;
    }
}