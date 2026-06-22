package manoel.fintracker.mappers;

import manoel.fintracker.domain.dtos.TransactionDto;
import manoel.fintracker.domain.dtos.TransactionDtoResponse;
import manoel.fintracker.domain.entities.Transaction;

public interface TransactionMapper {

    Transaction toEntity(TransactionDto transactionDto);

    TransactionDtoResponse toDto(Transaction transaction);
}
