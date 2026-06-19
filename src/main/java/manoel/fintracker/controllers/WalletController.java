package manoel.fintracker.controllers;

import jakarta.websocket.server.PathParam;
import manoel.fintracker.domain.dtos.WalletDto;
import manoel.fintracker.domain.entities.Wallet;
import manoel.fintracker.mappers.WalletMapper;
import manoel.fintracker.services.WalletService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/wallets")
public class WalletController {

    private final WalletService walletService;
    private final WalletMapper walletMapper;

    public WalletController(
            WalletService walletService,
            WalletMapper walletMapper) {
        this.walletService = walletService;
        this.walletMapper = walletMapper;
    }

    @GetMapping
    public List<WalletDto> listWallets() {
        return walletService.listWallets().stream().map(walletMapper::toDto).toList();
    }

    @PostMapping
    public WalletDto createWallet(@RequestBody WalletDto walletDto){
        Wallet wallet = walletMapper.toEntity(walletDto);
        wallet = walletService.createWallet(wallet);
        return walletMapper.toDto(wallet);
    }


    @GetMapping(path = "/{wallet_id}")
    public Optional<WalletDto> getWallet(@PathVariable("wallet_id")
    UUID walletId){
        Optional<Wallet> wallet = walletService.getWallet(walletId);
        return wallet.map(walletMapper::toDto);
    }

    //
    @PutMapping(path = "/{wallet_id}")
    public WalletDto updateWallet(
            @PathVariable("wallet_id") UUID walletId,
            @RequestBody WalletDto walletDto
    ){
       Wallet wallet = walletMapper.toEntity(walletDto);
       wallet = walletService.updateWallet(walletId, wallet);
       return walletMapper.toDto(wallet);
    }

    @DeleteMapping(path = "/{wallet_id}")
    public void deleteWallet(@PathVariable("wallet_id") UUID walletId){
        walletService.deleteWallet(walletId);
    }

}
