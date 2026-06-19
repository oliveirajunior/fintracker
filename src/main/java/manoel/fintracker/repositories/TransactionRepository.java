package manoel.fintracker.repositories;

import manoel.fintracker.domain.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByWalletId(UUID walletId);
    Optional<Transaction> findByWalletIdAndId(UUID walletId, UUID id);
    void deleteByWalletIdAndId(UUID walletId, UUID id);
}
