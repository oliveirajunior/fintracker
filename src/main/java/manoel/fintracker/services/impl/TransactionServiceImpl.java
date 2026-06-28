package manoel.fintracker.services.impl;

import manoel.fintracker.domain.entities.Transaction;
import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.repositories.TransactionRepository;
import manoel.fintracker.repositories.WalletRepository;
import manoel.fintracker.services.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final WalletRepository walletRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, WalletRepository walletRepository){
        this.transactionRepository = transactionRepository;
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Transaction> listTransactions(UUID walletId){
        return transactionRepository.findByWalletId(walletId);
    }

    @Override
    public Transaction createTransaction(UUID walletId, Transaction transaction){

        if(transaction.getId() != null){
            throw new IllegalArgumentException("Transaction already has an ID!");
        }

        if(transaction.getDescription() == null){
            throw new IllegalArgumentException("Transaction must have a description!");
        }

        if(transaction.getAmount() == null){
            throw new IllegalArgumentException("Transaction must have a valid amount!");
        }

        if(transaction.getDate() == null){
            throw new IllegalArgumentException("Transaction must have a valid date!");
        }

        if(transaction.getType() == null){
            throw new IllegalArgumentException("Transaction must have a valid type!");
        }

        Wallet wallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException(("Invalid Wallet ID provided!")));

        BigDecimal amount = new BigDecimal(transaction.getAmount().toString());

        Transaction transactionToSave = new Transaction(
                null,
                transaction.getDescription(),
                amount,
                //LocalDateTime.now(),
                transaction.getDate(),
                transaction.getType(),
                wallet
        );

        return transactionRepository.save(transactionToSave);
    }

    @Override
    public Optional<Transaction> getTransaction(UUID walletId, UUID transactionId){
        return transactionRepository.findByWalletIdAndId(walletId, transactionId);
    }

    @Override
    public Transaction updateTransaction(UUID walletId, UUID transactionId, Transaction transaction){

        if(transaction.getId() == null){
            throw new IllegalArgumentException("Transaction must have an ID");
        }

        if(!Objects.equals(transaction.getId(), transactionId)){
            throw new IllegalArgumentException("Attempting to change transaction ID, this is not permitted!");
        }

        Transaction existingTransaction = transactionRepository.findByWalletIdAndId(walletId, transactionId).orElseThrow(() -> new IllegalArgumentException("transaction not found!"));

        existingTransaction.setDescription(transaction.getDescription());
        existingTransaction.setAmount(transaction.getAmount());
        existingTransaction.setDate(transaction.getDate());
        existingTransaction.setType(transaction.getType());

        return transactionRepository.save(existingTransaction);

    }

    @Override
    public void deleteTransaction(UUID walletId, UUID transactionId){
        transactionRepository.deleteByWalletIdAndId(walletId, transactionId);
    }

}
