package com.example.android.wearable.bank.DataSources;


import com.example.android.wearable.bank.Model.Login.Login;
import com.example.android.wearable.bank.Model.Login.User;
import com.example.android.wearable.bank.Network.LoginService;
import com.example.android.wearable.bank.Utils.util;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

public class LoginClient {

    private static LoginClient instance;
    private LoginService loginService;

    private LoginClient(){
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
        loginService  = retrofit.create(LoginService.class);
    }

    public static LoginClient getInstance(){
        if(instance == null){
            instance = new LoginClient();
        }
        return instance;
    }

    public Observable<Login> LoginUser(User user){
        return loginService.getLoginResponse(user);
    }
}
