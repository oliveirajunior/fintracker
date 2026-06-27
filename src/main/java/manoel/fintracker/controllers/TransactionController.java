package manoel.fintracker.controllers;

import jakarta.transaction.Transactional;
import manoel.fintracker.domain.dtos.TransactionDto;
import manoel.fintracker.domain.dtos.TransactionDtoResponse;
import manoel.fintracker.domain.entities.Transaction;
import manoel.fintracker.mappers.TransactionMapper;
import manoel.fintracker.services.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/wallets/{wallet_id}/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    public TransactionController(TransactionService transactionService, TransactionMapper transactionMapper){
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping
    public List<TransactionDtoResponse> listTransactions(@PathVariable("wallet_id") UUID walletId){
        List<Transaction> transactionList = transactionService.listTransactions(walletId);
        return transactionList.stream().map(transactionMapper::toDto).toList();
    }

    @PostMapping
    public TransactionDtoResponse createTransaction(@PathVariable("wallet_id") UUID walletId, @RequestBody TransactionDto transactionDto){
        Transaction transaction = transactionMapper.toEntity(transactionDto);
        transaction = transactionService.createTransaction(walletId, transaction);
        return transactionMapper.toDto(transaction);
    }

    @GetMapping(path = "/{transaction_id}")
    public Optional<TransactionDtoResponse> getTransaction(
            @PathVariable("wallet_id") UUID walletId,
            @PathVariable("transaction_id") UUID transactionId
    ){

        Optional<Transaction> transaction = transactionService.getTransaction(walletId, transactionId);

        return transaction.map(transactionMapper::toDto);

    }

    @PutMapping(path = "/{transaction_id}")
    public TransactionDtoResponse updateTransaction(
        @PathVariable("wallet_id") UUID walletId,
        @PathVariable("transaction_id") UUID transactionId,
        @RequestBody TransactionDto transactionDto
    ){

        Transaction transaction = transactionMapper.toEntity(transactionDto);

        transaction = transactionService.updateTransaction(walletId, transactionId, transaction);

        return transactionMapper.toDto(transaction);
    }

    @Transactional
    @DeleteMapping(path = "/{transaction_id}")
    public void deleteTrasaction(
            @PathVariable("wallet_id") UUID walletId,
            @PathVariable("transaction_id") UUID transactionId
    ){
        transactionService.deleteTransaction(walletId, transactionId);
    }
}
