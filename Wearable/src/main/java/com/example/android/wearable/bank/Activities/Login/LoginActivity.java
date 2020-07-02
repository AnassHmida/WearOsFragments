package com.example.android.wearable.bank.Activities.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.wearable.bank.Activities.Main.MainActivity;
import com.example.android.wearable.bank.Model.Login;
import com.example.android.wearable.bank.Model.User;
import com.example.android.wearable.jumpingjack.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class LoginActivity extends FragmentActivity {
@BindView(R.id.signin)
TextView signin;
    @BindView(R.id.login)
    EditText login;
    @BindView(R.id.password)
    EditText password;
    private static final String TAG = "LoginActivity";
    LoginViewModel loginViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        Paper.init(this);
        loginViewModel = new ViewModelProvider.NewInstanceFactory().create(LoginViewModel.class);
        SubscribeObservers();
    }

    @OnClick(R.id.signin)
    public void login(){
        checkPassword(login.getText().toString(), password.getText().toString());


    }
    public void checkPassword(String login, String password){

        if(TextUtils.isEmpty(login)){
    //        util.showToast(getApplicationContext(), "Enter valid email");
        }else if(password.length() <= 3){
        //    util.showToast(getApplicationContext(), "Please enter password longer than 5 characters");
        }else{

           loginViewModel.LoginWithCreds(new User(login,password));

        }
    }
    public void successfulLogin(Login loginResponseModel) {
        Paper.book().write("login", loginResponseModel);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
      //  intent.putExtra("Login","potato");
        startActivity(intent);
        finish();

    }
    public void showError(String call, String message) {

     //   if(call.equals("network error")) util.showSheet(sheetBehavior);
    }
    private void SubscribeObservers(){
        loginViewModel.observeAuthState().observe( this, new androidx.lifecycle.Observer<LoginResource<Login>>() {
            @Override
            public void onChanged(LoginResource<Login> userLoginResource) {
                if(userLoginResource != null){
                    switch (userLoginResource.status){
                        case LOADING:{

                            break;
                        }
                        case AUTHENTICATED:
                            successfulLogin(userLoginResource.data);
                            Log.d(TAG, "onChanged:  LOGIN SUCESS" + userLoginResource.data);
                            break;
                        case ERROR:{
                            Log.d(TAG, "onChanged: "+"mochkla");
                            //Toast.makeText(LoginActivity.this,userLoginResource.message +" \n , Did you enter a number between 1 and 10 ?", Toast.LENGTH_LONG).show();
                            break;
                        }case NOT_AUTHENTICATED:{
                            showError("network error","");
                            break;
                        }
                    }
                }
            }
        });
    }





}
