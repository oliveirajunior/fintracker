package manoel.fintracker.services;

import manoel.fintracker.domain.entities.Wallet;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalletService {
    List<Wallet> listWallets();
    Wallet createWallet(Wallet wallet);
    Optional<Wallet> getWallet(UUID walletId);
    Wallet updateWallet(UUID walletId, Wallet wallet);
    void deleteWallet(UUID walletId);

}
