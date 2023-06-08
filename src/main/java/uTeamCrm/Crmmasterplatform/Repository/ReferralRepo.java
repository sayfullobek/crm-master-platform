package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.Referral;

import java.util.UUID;

public interface ReferralRepo extends JpaRepository<Referral, UUID> {
}
