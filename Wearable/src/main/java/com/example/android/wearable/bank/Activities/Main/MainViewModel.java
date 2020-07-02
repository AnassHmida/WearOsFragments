package com.example.android.wearable.bank.Activities.Main;





import android.util.Log;

import androidx.lifecycle.LiveData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android.wearable.bank.DataSources.AccountsClient;
import com.example.android.wearable.bank.Model.Account;
import com.example.android.wearable.bank.Model.Accountss;
import com.example.android.wearable.bank.Network.AccountsService;

import java.util.List;

import rx.Observer;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainViewModel  extends ViewModel {
    private  AccountsService accountsService;
    private static final String TAG = "AuthViewModel";
    private String Authenticated ;
    private rx.Subscription subscription;
    final MutableLiveData<MainResource<List<Accountss>>> result = new MutableLiveData<>();
    public MainViewModel(){
    }

    public MainViewModel(AccountsService accountsService){
        this.accountsService = accountsService;
    }


    public void GetAllAccounts(Account account){


        subscription = AccountsClient.getInstance().getAllAccounts(account)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<List<Accountss>>() {

                    @Override
                    public void onNext(List<Accountss> accounts) {
                        result.setValue(MainResource.datareceived(accounts));
                        Log.d(TAG, "onNext: " + accounts.get(0).getOpenBalance());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setValue(MainResource.error("error",null));
                        Log.d(TAG, "onError: " +e.getMessage());
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
    public LiveData<MainResource<List<Accountss>>> observeAuthState(){
        return result;
    }
}