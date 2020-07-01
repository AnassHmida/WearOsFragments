package com.example.android.wearable.bank.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Accounts {
    int id;
    String name;
    String Description;
            String balance;
            List<Transactions> transactions;
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

    public Accounts(){

    }
    public Accounts(int id, String name, String description, String balance, String incoming, String date,List<Transactions> transactions) {
        this.id = id;
        this.name = name;
        this.Description = description;
        this.balance = balance;
        this.incoming = incoming;
        this.Date = date;
        this.transactions = transactions;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

    public List<Accounts> dummyAccounts (){
        List<Accounts> accounts =new ArrayList<>();
        accounts.add(new Accounts(50783,"Standard","First account","335,5 €","-35,5 €","12/09/2022", Arrays.asList(new Transactions(1,"35,58 €","FACTURE CARTE DE CREDIT AGILE , ADRESSE 55862 / RU"),new Transactions(2,"189,34 €","FACTURE DE AMAZON FRANCE , ADRESSE 55862 / RU"),new Transactions(3,"-21,58 €","FACTURE CARTE DE CREDIT AGILE , ADRESSE 55862 / BR"))));
        accounts.add(new Accounts(89984,"Standard","Second account","440,6 €","-38,5 €","25/08/2021", Arrays.asList(new Transactions(1,"-250,50 €","FACTURE CARTE DE PAIMENT SATORIPOP 5686 , RUE SMETHING"),new Transactions(1,"87,58 €","FACTURE CARTE DE CREDIT AGILE , ADRESSE 55862 / RU"),new Transactions(1,"78,58 €","FACTURE CARTE DE CREDIT AGILE , ADRESSE 55862 / TN"))));
        accounts.add(new Accounts(98555,"Standard","Third account","-555,6 €","138,5 €","1/07/2020", Arrays.asList(new Transactions(1,"330,50 €","FACTURE CARTE DE PAIMENT CASTORAMA , ROUTE DES LUCIOLES 5568 /FR "),new Transactions(1,"35,58 €","FACTURE CARTE DE SHELL INC. , ADRESSE 55862 / RU"),new Transactions(1,"-669,58 €","FACTURE CARTE DE CREDIT AGILE , ADRESSE 55862 / FR"))));
        accounts.add(new Accounts(15993,"Premium","Fourth account","1000,06 €","500,5 €","1/07/2020", Arrays.asList(new Transactions(1,"-46,01 €","TRANSFER DE LA BANQUE CENTRALE , ADRESSE 55862 / SOUSSE"),new Transactions(1,"-69,58 €","FACTURE D'ACHAT DE MICROMANIA , ADRESSE 55862 / RU"),new Transactions(1,"443,58 €","FACTURE CARTE DE CENT , ADRESSE 555  - 88978 / US"))));


        return accounts;
    }
}
