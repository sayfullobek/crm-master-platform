package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.AcademyWallet;

import java.util.UUID;

public interface AcademyWalletRepo extends JpaRepository<AcademyWallet, UUID> {
}
