package manoel.fintracker.mappers.impl;

import manoel.fintracker.domain.dtos.WalletDto;
import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.mappers.TransactionMapper;
import manoel.fintracker.mappers.WalletMapper;
import org.springframework.stereotype.Component;

@Component
public class WalletMapperImpl implements WalletMapper {

    private final TransactionMapper transactionMapper;

    public WalletMapperImpl(TransactionMapper transactionMapper) {
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Wallet toEntity(WalletDto walletDto) {
        return new Wallet(
                walletDto.id(),
                walletDto.name(),
                walletDto.description(),
                walletDto.currentBalance(),
                null,
                null,
                walletDto.transactions().stream().toList()//TODO
        );
    }

    @Override
    public WalletDto toDto(Wallet wallet) {
        return new WalletDto(
                wallet.getId(),
                wallet.getName(),
                wallet.getDescription(),
                wallet.getCurrentBalance(),
                wallet.getTransactions()
        );
    }
}
