package com.example.android.wearable.bank.Model;

import com.google.gson.annotations.SerializedName;

public  class Account {
   String otp;
   String Sender = "All";

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public Account(String otp, String sender) {
        this.otp = otp;
        Sender = sender;
    }
}
