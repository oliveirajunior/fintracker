package manoel.fintracker.services.impl;

import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.repositories.WalletRepository;
import manoel.fintracker.services.WalletService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    public WalletServiceImpl(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> listWallets(){
        return walletRepository.findAll();
    }

    @Override
    public Wallet createWallet(Wallet wallet){
        return walletRepository.save(new Wallet(
                null,
                wallet.getName(),
                wallet.getDescription(),
                new BigDecimal("0.0"),
                LocalDateTime.now(),
                LocalDateTime.now(),
                null
        ));
    }

    @Override
    public Optional<Wallet> getWallet(UUID walletId){
        return walletRepository.findById(walletId);
    }

    @Override
    public Wallet updateWallet(UUID walletId, Wallet wallet){
       Wallet existingWallet = walletRepository.findById(walletId).orElseThrow(() -> new IllegalArgumentException("Wallet not found!"));

       existingWallet.setName(wallet.getName());
       existingWallet.setDescription(wallet.getDescription());
       //existingWallet.setCurrentBalance(wallet.getCurrentBalance());
       existingWallet.setUpdatedAt(LocalDateTime.now());

       return walletRepository.save(existingWallet);
    }

    @Override
    public void deleteWallet(UUID walletId){
        walletRepository.deleteById(walletId);
    }

}
