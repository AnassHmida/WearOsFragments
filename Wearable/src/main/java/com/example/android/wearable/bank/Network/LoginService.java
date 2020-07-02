package com.example.android.wearable.bank.Network;



import com.example.android.wearable.bank.Model.Login.Login;
import com.example.android.wearable.bank.Model.Login.User;


import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface LoginService {

    @POST("api/authentication?apiVersion=1.0")
    Observable<Login> getLoginResponse(@Body User loginInformation);


}