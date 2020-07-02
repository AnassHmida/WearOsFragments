package com.example.android.wearable.bank.Network;



import com.example.android.wearable.bank.Model.Account;
import com.example.android.wearable.bank.Model.Accountss;
import com.example.android.wearable.bank.Model.Login;
import com.example.android.wearable.bank.Model.User;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;
import rx.Single;

public interface AccountsService {

    @POST("api/accounts?apiVersion=1.0")
    Observable<List<Accountss>> getAllAccounts(@Body Account account);

    @POST("api/accounts?apiVersion=1.0")
    Single<List<Accountss>> gettTransactionsResponse(@Body Account account);


}