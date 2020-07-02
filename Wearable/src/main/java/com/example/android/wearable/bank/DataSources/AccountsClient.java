package com.example.android.wearable.bank.DataSources;



import android.util.Pair;

import androidx.annotation.NonNull;

import com.example.android.wearable.bank.Model.Account;
import com.example.android.wearable.bank.Model.Accounts;
import com.example.android.wearable.bank.Model.Accountss;
import com.example.android.wearable.bank.Model.Login;
import com.example.android.wearable.bank.Model.User;
import com.example.android.wearable.bank.Network.AccountsService;
import com.example.android.wearable.bank.Network.LoginService;
import com.example.android.wearable.bank.Utils.util;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Single;

public class AccountsClient {

    private static AccountsClient instance;
    private AccountsService accountsService;

    private AccountsClient(){

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
        accountsService  = retrofit.create(AccountsService.class);
    }

    public static AccountsClient getInstance(){
        if(instance == null){
            instance = new AccountsClient();
        }
        return instance;
    }
   /* public Single<Pair<List<Accountss>, Boolean>> getAccountsList(Account account) {
        Single<List<Accountss>> restaurantListSingle =
                accountsService.getAllAccounts(account).flatMap(user -> {
                    return ddApi.getAvailableRestaurant(user.defaultAddress.lat, user.defaultAddress.lng);
                });

        Single<Boolean> showPhotoExperimentSingle =
                ddApi.getShowPhotosExperiment(userId);

        return restaurantListSingle.zipWith(showPhotoExperimentSingle, Pair::new);
    }

    public Single<List<Accountss>> getAccountsDetails(Account account) {
        return accountsService.getAllAccounts(account).flatMap(user -> {
            return accountsService.gettTransactionsResponse(user.defaultAddress.lat, user.defaultAddress.lng);
        });
    }
*/

    public Observable<List<Accountss>> getAllAccounts(Account account){
        return accountsService.getAllAccounts(account);
    }
}

