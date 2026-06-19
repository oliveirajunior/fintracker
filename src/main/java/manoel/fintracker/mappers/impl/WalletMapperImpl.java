package manoel.fintracker.mappers.impl;

import manoel.fintracker.domain.dtos.WalletDto;
import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.mappers.WalletMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WalletMapperImpl implements WalletMapper {

    public WalletMapperImpl() {
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
                Optional.ofNullable(walletDto.transactions()).map(t -> t.stream().toList()).orElse(null)
        );
    }

    @Override
    public WalletDto toDto(Wallet wallet) {
        return new WalletDto(
                wallet.getId(),
                wallet.getName(),
                wallet.getDescription(),
                wallet.getCurrentBalance(),
                wallet.getTransactions());
    }
}
