package manoel.fintracker.domain.dtos;

import manoel.fintracker.domain.entities.Type;
import manoel.fintracker.domain.entities.Wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionDtoResponse(
        UUID id,
        String description,
        BigDecimal amount,
        LocalDateTime date,
        Type type,
        UUID walletId,
        String walletName
) {
}
