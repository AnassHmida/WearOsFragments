/*
 * Copyright (C) 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.wearable.bank.Fragments.AccountFragments;

import com.example.android.wearable.bank.Adapters.AccountsAdapter;
import com.example.android.wearable.bank.Model.Accounts;
import com.example.android.wearable.jumpingjack.R;

import android.content.Context;
import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SupportFragment extends Fragment {
    AccountsAdapter adapter;
    private static final String TAG = "SupportFragment";
    ViewPager2 pager;
    Accounts accounts;
    @BindView(R.id.edit_text)
    ExtractEditText editText;
    @BindView(R.id.back)
    Button back;
    DataSentListener dataSentListener;
public SupportFragment(ViewPager2 pager, Accounts accounts){
    this.pager = pager;
    this.accounts = accounts;
}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.support_layout, container, false);
        ButterKnife.bind(this, view);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {

            }
        });
        editText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Log.d(TAG, "onKey: "+"manzouz done");
                    return true;
                }
                return false;
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        dataSentListener.ApplyText(editText.getText().toString(),accounts);
            }
        });



        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof DataSentListener) {
            dataSentListener =(DataSentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentCommunicationListener");
        }

    }
    @Override
    public void onDetach() {
        super.onDetach();
        dataSentListener = null;
    }
    public interface DataSentListener{
        void ApplyText(String Text,Accounts accounts);
    }

}
