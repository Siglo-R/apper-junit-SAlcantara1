package org.example;

public class Account {
    private String id;
    private String name;
    private Double balance;

    public Account(String id, String name, Double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Double balance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
    public void decreaseBalance(Double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance");
        }
    }
    public void increaseBalance(Double amount) {
        balance += amount;
    }

}