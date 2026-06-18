package manoel.fintracker.domain.dtos;

import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.domain.entities.Type;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDto(
        UUID id,
        String description,
        BigDecimal amount,
        LocalDateTime date,
        Type type,

        Wallet wallet
) {
}
