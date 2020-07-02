package com.example.android.wearable.bank.DataSources;



import com.example.android.wearable.bank.Model.Accounts.Account;
import com.example.android.wearable.bank.Model.Accounts.Accounts;
import com.example.android.wearable.bank.Model.Transaction.Transaction;
import com.example.android.wearable.bank.Model.Transaction.Transactions;
import com.example.android.wearable.bank.Network.AccountsService;
import com.example.android.wearable.bank.Network.TransactionsService;
import com.example.android.wearable.bank.Utils.util;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class TransactionsClient {

    private static TransactionsClient instance;
    private TransactionsService transactionsService;

    private TransactionsClient(){

        final Gson gson =new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(util.baseURL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();
        transactionsService  = retrofit.create(TransactionsService.class);
    }

    public static TransactionsClient getInstance(){
        if(instance == null){
            instance = new TransactionsClient();
        }
        return instance;
    }




    public Observable<List<Transactions>> getTransactionsPerAccount(Transaction transaction){
        return transactionsService.getTransactionsPerAccount(transaction);
    }
}

