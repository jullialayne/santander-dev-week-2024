package me.dio.domain.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 15, scale = 2)
    private BigDecimal balance;

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();


    public Account() {

    }

    public Account(BigDecimal balance, String category) {
        this.balance = balance;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    // Deposit method
    public void deposit(Transaction transaction) {
        transaction.setType(TransactionType.INCOME);
        transactions.add(transaction);
        transaction.setAccount(this);
        balance = balance.add(transaction.getBalance());
    }

    // Withdraw method
    public void withdraw(Transaction transaction) {
        transaction.setType(TransactionType.EXPENSE);
        transactions.add(transaction);
        transaction.setAccount(this);
        balance = balance.subtract(transaction.getBalance());
    }
}
