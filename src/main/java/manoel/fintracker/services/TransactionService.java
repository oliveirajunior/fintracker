package manoel.fintracker.services;

import manoel.fintracker.domain.entities.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> listTransactions(UUID walletId);
    Transaction createTransaction(UUID walletId, Transaction transaction);
    Optional<Transaction> getTransaction(UUID walletId, UUID transactionId);
    Transaction updateTransaction(UUID walletId, UUID transactionId, Transaction transaction);
    void deleteTransaction(UUID walletId, UUID transactionId);
}
