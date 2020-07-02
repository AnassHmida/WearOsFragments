package com.example.android.wearable.bank.Network;



import com.example.android.wearable.bank.Model.Login.Login;
import com.example.android.wearable.bank.Model.Login.User;
import com.example.android.wearable.bank.Model.Transaction.Transaction;
import com.example.android.wearable.bank.Model.Transaction.Transactions;


import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

public interface TransactionsService {

    @POST("api/extractnlast?apiVersion=1.0")
    Observable<List<Transactions>> getTransactionsPerAccount(@Body Transaction transactionInformation);


}