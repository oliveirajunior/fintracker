package manoel.fintracker.mappers.impl;

import manoel.fintracker.domain.dtos.TransactionDto;
import manoel.fintracker.domain.entities.Transaction;
import manoel.fintracker.mappers.TransactionMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public Transaction toEntity(TransactionDto transactionDto) {
        return new Transaction(
                transactionDto.id(),
                transactionDto.description(),
                transactionDto.amount(),
                transactionDto.date(),
                transactionDto.type(),
                transactionDto.wallet()
        );
    }

    @Override
    public TransactionDto toDto(Transaction transaction) {
        return new TransactionDto(
                transaction.getId(),
                transaction.getDescription(),
                transaction.getAmount(),
                transaction.getDate(),
                transaction.getType(),
                transaction.getWallet()
        );
    }
}
