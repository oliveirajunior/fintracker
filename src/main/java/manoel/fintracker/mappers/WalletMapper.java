package manoel.fintracker.mappers;

import manoel.fintracker.domain.dtos.WalletDto;
import manoel.fintracker.domain.dtos.WalletDtoResponse;
import manoel.fintracker.domain.entities.Wallet;

public interface WalletMapper {

    Wallet toEntity(WalletDto walletDto);

    WalletDtoResponse toDto(Wallet wallet);
}
