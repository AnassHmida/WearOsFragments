package com.example.android.wearable.bank.Model.Transaction;

public class Transaction {
    String Otp;
    String bankaccountid;
    int nlast;

    public int getnLast() {
        return nlast;
    }

    public void setnLast(int nLast) {
        this.nlast = nLast;
    }

    public String getOtp() {
        return Otp;
    }

    public void setOtp(String otp) {
        Otp = otp;
    }

    public String getBankAccountId() {
        return bankaccountid;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankaccountid = bankAccountId;
    }

    public int getNLast() {
        return nlast;
    }

    public void setNLast(int NLast) {
        this.nlast = NLast;
    }

    public Transaction(String otp, String bankAccountId, int NLast) {
        this.Otp = otp;
        this.bankaccountid = bankAccountId;
        this.nlast = NLast;
    }
}
