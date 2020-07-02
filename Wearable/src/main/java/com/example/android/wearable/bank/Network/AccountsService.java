package com.example.android.wearable.bank.Network;



import com.example.android.wearable.bank.Model.Accounts.Account;
import com.example.android.wearable.bank.Model.Accounts.Accounts;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface AccountsService {

    @POST("api/accounts?apiVersion=1.0")
    Observable<List<Accounts>> getAllAccounts(@Body Account account);



}