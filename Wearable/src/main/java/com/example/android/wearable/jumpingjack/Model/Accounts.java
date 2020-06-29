package com.example.android.wearable.jumpingjack.Model;

public class Accounts {
    int id;
    String name;
    String Description;
            String balance;
            String incoming;
    String Date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getIncoming() {
        return incoming;
    }

    public void setIncoming(String incoming) {
        this.incoming = incoming;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Accounts(int id, String name, String description, String balance, String incoming, String date) {
        this.id = id;
        this.name = name;
        Description = description;
        this.balance = balance;
        this.incoming = incoming;
        Date = date;
    }
}
