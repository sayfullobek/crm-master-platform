package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uTeamCrm.Crmmasterplatform.Repository.PaymentHistoryRepo;

@RestController
@RequestMapping("/api/paymentHistory")
@RequiredArgsConstructor
public class PaymentHistoryController {

    private PaymentHistoryRepo paymentHistoryRepo;

}
