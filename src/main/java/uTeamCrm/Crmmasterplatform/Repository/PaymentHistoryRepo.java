package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.PaymentHistory;

import java.util.UUID;

public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, UUID> {
}
