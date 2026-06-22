package manoel.fintracker.mappers.impl;

import manoel.fintracker.domain.dtos.WalletDto;
import manoel.fintracker.domain.dtos.WalletDtoResponse;
import manoel.fintracker.domain.entities.Transaction;
import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.mappers.TransactionMapper;
import manoel.fintracker.mappers.WalletMapper;
import org.springframework.stereotype.Component;

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
                //walletDto.transactions().stream().toList());
                Optional.ofNullable(walletDto.transactions()).map(transactions -> transactions.stream().map(transactionMapper::toEntity).toList()).orElse(null)
        );
    }

    @Override
    public WalletDtoResponse toDto(Wallet wallet) {
        return new WalletDtoResponse(
                wallet.getId(),
                wallet.getName(),
                wallet.getDescription(),
                wallet.getCurrentBalance(),
                wallet.getTransactions().stream().map(transactionMapper::toDto).toList()

        );
    }
}
