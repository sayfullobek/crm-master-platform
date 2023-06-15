package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.Wallet;

import java.util.UUID;

public interface WalletRepo extends JpaRepository<Wallet, UUID> {

    Wallet findWalletByUserId(UUID user_id);
}
