package com.example.android.wearable.bank.Model.Accounts;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Accounts implements Serializable {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getBankAccountId() {
        return BankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        BankAccountId = bankAccountId;
    }

    public String getOpenBalance() {
        return openBalance;
    }

    public void setOpenBalance(String openBalance) {
        this.openBalance = openBalance;
    }

    public String getOnlineBalance() {
        return onlineBalance;
    }

    public void setOnlineBalance(String onlineBalance) {
        this.onlineBalance = onlineBalance;
    }

    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;
    @SerializedName("rib")
    private String rib;
    @SerializedName("id")
    private String id;
    @SerializedName("categorie")
    private String categorie;
    @SerializedName("creationDate")
    private String creationDate;
    @SerializedName("currency")
    private String currency;
    @SerializedName("BankAccountId")
    private String BankAccountId;
    @SerializedName("openBalance")
    private String openBalance;
    @SerializedName("onlineBalance")
    private String onlineBalance;
}
