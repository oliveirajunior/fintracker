package manoel.fintracker.domain.dtos;

import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.domain.entities.Type;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        String description,
        BigDecimal amount,
        Type type,
        Wallet wallet
) {
}
