package com.example.android.wearable.bank.Utils;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.android.wearable.jumpingjack.R;


public class util {
    static Toast mToast;
    public static String baseURL = "https://dev.banquezitouna.com/TaaSMobileDevCIV2/";
    public static String AccountsBaseUrl = "http://www.freedev.tn:3001/api/";
    public static final String mypreference = "mypref";
    public static final String key = "account";
    public static final String user = "account_user";
    public static final String password = "account_password";
    public static final String logged = "logged";
    public static final String notlogged = "notlogged";
    public static final String bookkey = "loggedmodel";


    public static void showToast(Context context, String statusMsg){
        if(mToast != null) mToast.cancel();
        mToast = Toast.makeText(context,statusMsg, Toast.LENGTH_SHORT);
        mToast.show();
    }



}
