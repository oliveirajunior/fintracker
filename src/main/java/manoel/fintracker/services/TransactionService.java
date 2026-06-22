package manoel.fintracker.services;

import manoel.fintracker.domain.entities.Transaction;
import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.repositories.TransactionRepository;
import manoel.fintracker.repositories.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionService(TransactionRepository transactionRepository, WalletRepository walletRepository){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    public List<Transaction> listTransactions(UUID walletId){
        return transactionRepository.findByWalletId(walletId);
    }

    public Transaction createTransaction(UUID walletId, Transaction transaction){
        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException(("Invalid Wallet ID provided!")));

        BigDecimal amount = new BigDecimal(transaction.getAmount().toString());

        Transaction transactionToSave = new Transaction(
                null,
                transaction.getDescription(),
                amount,
                LocalDateTime.now(),
                transaction.getType(),
                wallet
        );

        return transactionRepository.save(transactionToSave);
    }

    public Optional<Transaction> getTransaction(UUID walletId, UUID transactionId){
        return transactionRepository.findByWalletIdAndId(walletId, transactionId);
    }

    public Transaction updateTransaction(UUID walletId, UUID transactionId, Transaction transaction){
        Transaction existingTransaction = transactionRepository.findByWalletIdAndId(walletId, transactionId).orElseThrow(() -> new IllegalArgumentException("transaction not found!"));

        existingTransaction.setDescription(transaction.getDescription());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setDate(transaction.getDate());
        existingTransaction.setType(transaction.getType());

        return transactionRepository.save(existingTransaction);

    }

    public void deleteTransaction(UUID walletId, UUID transactionId){
        transactionRepository.deleteByWalletIdAndId(walletId, transactionId);
    }

}
