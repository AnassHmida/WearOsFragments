package com.example.android.wearable.bank.Activities.Login;




import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android.wearable.bank.DataSources.LoginClient;
import com.example.android.wearable.bank.Model.Login;
import com.example.android.wearable.bank.Model.User;
import com.example.android.wearable.bank.Network.LoginService;

import org.reactivestreams.Subscriber;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LoginViewModel  extends ViewModel {
    private  LoginService loginService;
    private static final String TAG = "AuthViewModel";
    private String Authenticated ;
    private rx.Subscription subscription;
    final MutableLiveData<LoginResource<Login>> result = new MutableLiveData<>();
    public LoginViewModel(){
    }

    public LoginViewModel(LoginService loginService){
        this.loginService = loginService;
     }

    public void LoginWithCreds(User user){


        subscription = LoginClient.getInstance().LoginUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Login>() {

                    @Override
                    public void onNext(Login login) {
                    result.setValue(LoginResource.authenticated(login));
                       // Log.d(TAG, "onNext: " + login.getFirstName());
                    }

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setValue(LoginResource.error("error",null));
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
    public LiveData<LoginResource<Login>> observeAuthState(){
return result;
    }
}