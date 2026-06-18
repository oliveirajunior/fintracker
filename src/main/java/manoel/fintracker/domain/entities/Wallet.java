package manoel.fintracker.domain.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "currentBalance", nullable = false)
    private BigDecimal currentBalance;

    @Column(name = "createdAt", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "wallet", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Transaction> transactions;

    public Wallet() {
    }

    public Wallet(UUID id, String name, String description, BigDecimal currentBalance, LocalDateTime createdAt, LocalDateTime updatedAt, List<Transaction> transactions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.currentBalance = currentBalance;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.transactions = transactions;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id) && Objects.equals(name, wallet.name) && Objects.equals(description, wallet.description) && Objects.equals(currentBalance, wallet.currentBalance) && Objects.equals(createdAt, wallet.createdAt) && Objects.equals(updatedAt, wallet.updatedAt) && Objects.equals(transactions, wallet.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, currentBalance, createdAt, updatedAt, transactions);
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currentBalance=" + currentBalance +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", transactions=" + transactions +
                '}';
    }
}
