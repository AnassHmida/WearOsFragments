package com.example.android.wearable.bank.Network;



import com.example.android.wearable.bank.Model.Account;
import com.example.android.wearable.bank.Model.Accountss;
import com.example.android.wearable.bank.Model.Login;
import com.example.android.wearable.bank.Model.User;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AccountsService {

    @POST("api/accounts?apiVersion=1.0")
    Observable<List<Accountss>> getLoginResponse(@Body Account account);


}