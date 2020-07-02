package com.example.android.wearable.bank.Model.Transaction;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Transactions implements Parcelable {

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode) {
        this.transactionCode = transactionCode;
    }

    public String getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(String startBalance) {
        this.startBalance = startBalance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getEndBalance() {
        return endBalance;
    }

    public void setEndBalance(String endBalance) {
        this.endBalance = endBalance;
    }

    public String getSortedKey() {
        return sortedKey;
    }

    public void setSortedKey(String sortedKey) {
        this.sortedKey = sortedKey;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    @SerializedName("CategoryName")
    private String CategoryName;
    @SerializedName("transactionCode")
    private String transactionCode;
    @SerializedName("startBalance")
    private String startBalance;
    @SerializedName("id")
    private String id;
    @SerializedName("valueDate")
    private String valueDate;
    @SerializedName("endBalance")
    private String endBalance;
    @SerializedName("sortedKey")
    private String sortedKey;
    @SerializedName("operation")
    private String operation;
    @SerializedName("amount")
    private String amount;
    @SerializedName("operationDate")
    private String operationDate;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }
}
