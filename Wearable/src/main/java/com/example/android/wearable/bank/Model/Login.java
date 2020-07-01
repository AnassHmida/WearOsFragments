package com.example.android.wearable.bank.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Login {
    public List<ServicesEntity> getServices() {
        return Services;
    }

    public void setServices(List<ServicesEntity> services) {
        Services = services;
    }

    public String getMax_daily_transfer_amount() {
        return max_daily_transfer_amount;
    }

    public void setMax_daily_transfer_amount(String max_daily_transfer_amount) {
        this.max_daily_transfer_amount = max_daily_transfer_amount;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public List<BankAccountRequestSenderEntity> getBankAccountRequestSender() {
        return BankAccountRequestSender;
    }

    public void setBankAccountRequestSender(List<BankAccountRequestSenderEntity> bankAccountRequestSender) {
        BankAccountRequestSender = bankAccountRequestSender;
    }

    public String getProfile_type() {
        return profile_type;
    }

    public void setProfile_type(String profile_type) {
        this.profile_type = profile_type;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMax_transfer_amount() {
        return max_transfer_amount;
    }

    public void setMax_transfer_amount(String max_transfer_amount) {
        this.max_transfer_amount = max_transfer_amount;
    }

    public String getPackage_type() {
        return Package_type;
    }

    public void setPackage_type(String package_type) {
        Package_type = package_type;
    }

    @SerializedName("Services")
    private List<ServicesEntity> Services;
    @SerializedName("otp")
    private String otp;
    @SerializedName("max_daily_transfer_amount")
    private String max_daily_transfer_amount;
    @SerializedName("accountId")
    private int accountId;
    @SerializedName("BankAccountRequestSender")
    private List<BankAccountRequestSenderEntity> BankAccountRequestSender;
    @SerializedName("profile_type")
    private String profile_type;
    @SerializedName("LastName")
    private String LastName;
    @SerializedName("FirstName")
    private String FirstName;
    @SerializedName("max_transfer_amount")
    private String max_transfer_amount;
    @SerializedName("Package_type")
    private String Package_type;

    public static class ServicesEntity {
        @SerializedName("Value")
        private int Value;
        @SerializedName("Name")
        private String Name;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public static class BankAccountRequestSenderEntity {
        @SerializedName("Value")
        private String Value;
        @SerializedName("Key")
        private String Key;
    }
}
