package manoel.fintracker.mappers.impl;

import manoel.fintracker.domain.dtos.WalletDto;
import manoel.fintracker.domain.dtos.WalletDtoResponse;
import manoel.fintracker.domain.entities.Transaction;
import manoel.fintracker.domain.entities.Type;
import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.mappers.TransactionMapper;
import manoel.fintracker.mappers.WalletMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class WalletMapperImpl implements WalletMapper {

    private final TransactionMapper transactionMapper;


    public WalletMapperImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    };

    @Override
    public Wallet toEntity(WalletDto walletDto) {
        return new Wallet(
                walletDto.id(),
                walletDto.name(),
                walletDto.description(),
                walletDto.currentBalance(),
                null,
                null,
                Optional.ofNullable(walletDto.transactions()).map(transactions -> transactions.stream().map(transactionMapper::toEntity).toList()).orElse(null)
        );
    }

    @Override
    public WalletDtoResponse toDto(Wallet wallet) {
        return new WalletDtoResponse(
                wallet.getId(),
                wallet.getName(),
                wallet.getDescription(),
                calculateCurrentBalance(wallet.getTransactions()),
                wallet.getTransactions().stream().map(transactionMapper::toDto).toList()

        );
    }

    private BigDecimal calculateCurrentBalance(List<Transaction> transactions){

        return transactions.stream()
                .map(t -> t.getType() == Type.INCOME ? t.getAmount() : t.getAmount().negate())
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
