package com.example.android.wearable.bank.Activities.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android.wearable.bank.Activities.Main.MainActivity;
import com.example.android.wearable.bank.Model.Login.Login;
import com.example.android.wearable.bank.Model.Login.User;
import com.example.android.wearable.bank.Utils.WatchEditText;
import com.example.android.wearable.jumpingjack.R;
import com.google.android.material.snackbar.Snackbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class LoginActivity extends FragmentActivity {
@BindView(R.id.signin)
TextView signin;
    @BindView(R.id.login)
    WatchEditText login;
    @BindView(R.id.password)
    WatchEditText password;
    private static final String TAG = "LoginActivity";
    LoginViewModel loginViewModel;
    Drawable errorIcon;
    @BindView(R.id.progress)
    ProgressBar progress;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        Paper.init(this);
        loginViewModel = new ViewModelProvider.NewInstanceFactory().create(LoginViewModel.class);

        errorIcon = getResources().getDrawable(R.drawable.error_icon);
        DrawableCompat.setTint(errorIcon, getResources().getColor(R.color.flat_red));
        errorIcon.setBounds(new Rect(0, 0, errorIcon.getIntrinsicWidth(), errorIcon.getIntrinsicHeight()));
        SubscribeObservers();


    }


    public void clearTextField(EditText edittext){
        edittext.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                login.setText("");
                password.setText("");

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int aft )
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {



            }
        });
    }

    @OnClick(R.id.signin)
    public void login(){

        checkPassword(login.getText().toString(), password.getText().toString());
    }

    public void DoneProgress(){
        signin.setText("Sign in");
        progress.setVisibility(View.GONE);
    }
    public void StartProgress(){
        signin.setText("");
        progress.setVisibility(View.VISIBLE);
    }
    public void checkPassword(String login_text, String password_text){

        if(TextUtils.isEmpty(login_text)){login.setError("", errorIcon);;
        }else if(password_text.length() <= 3){
        //    util.showToast(getApplicationContext(), "Please enter password longer than 5 characters");
            password.setError("", errorIcon);
        }else{
            StartProgress();
           loginViewModel.LoginWithCreds(new User(login_text,password_text));

        }
    }
    public void successfulLogin(Login loginResponseModel) {
        Paper.book().write("login", loginResponseModel);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(intent);
        finish();

    }
    public void showError() {

        LayoutInflater inflater = getLayoutInflater();
        AlertDialog.Builder stories =   new AlertDialog.Builder(LoginActivity.this);
        stories.setView(inflater.inflate(R.layout.error_alert_dialog,null));
        AlertDialog storie = stories.create();
        storie.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        storie.show();
        TextView close = (TextView) storie.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storie.hide();
                login.setText("");
                password.setText("");
            }
        });
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
                         //   showError();
                          //  DoneProgress();
                          successfulLogin(userLoginResource.data);
                            Log.d(TAG, "onChanged:  LOGIN SUCESS" + userLoginResource.data);


                            break;
                        case ERROR:{
                            DoneProgress();
                            Log.d(TAG, "onChanged: "+"mochkla");
                            showError();
                            break;
                        }case NOT_AUTHENTICATED:{
                            showError();
                            break;
                        }
                    }
                }
            }
        });
    }





}
