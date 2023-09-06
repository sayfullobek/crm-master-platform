package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import uTeamCrm.Crmmasterplatform.entity.PaymentHistory;

import java.util.List;
import java.util.UUID;
@CrossOrigin
public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, UUID> {

    List<PaymentHistory> findPaymentHistoriesByUserId(UUID user_id);

    List<PaymentHistory> findPaymentHistoriesByGroupId(UUID group_id);
}
