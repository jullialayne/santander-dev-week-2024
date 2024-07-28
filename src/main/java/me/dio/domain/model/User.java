package me.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Transaction> transactions;

    public User() {
    }

    public User(int id, String name, String email, String password, BigDecimal balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void deposit(Transaction transaction) {
        transaction.setType(TransactionType.INCOME);
        transactions.add(transaction);
        transaction.setUser(this);
        balance = balance.add(transaction.getBalance());
    }

    public void withdraw(Transaction transaction) {
        transaction.setType(TransactionType.EXPENSE);
        transactions.add(transaction);
        transaction.setUser(this);
        balance = balance.subtract(transaction.getBalance());
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(this.transactions);
    }

}
