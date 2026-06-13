package manoel.fintracker.domain.dtos;

import manoel.fintracker.domain.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record WalletDto(
        UUID id,
        String name,
        String description,
        BigDecimal currentBalance,
        List<Transaction> transactions
) {

}
