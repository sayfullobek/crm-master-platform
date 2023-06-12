package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.PaymentHistoryRepo;
import uTeamCrm.Crmmasterplatform.Repository.PupilPaymentRepo;
import uTeamCrm.Crmmasterplatform.entity.PupilPayment;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PupilPaymentDto;
import uTeamCrm.Crmmasterplatform.service.PupilPaymentService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentPupilController {

    private final PupilPaymentRepo pupilPaymentRepo;

    private final PupilPaymentService pupilPaymentService;

    @GetMapping
    public HttpEntity<?> getPayments(){
        List<PupilPaymentDto> payments = pupilPaymentService.getPayments();
        return ResponseEntity.ok(payments);
    }
    @PostMapping
    public HttpEntity<?> payment(@RequestParam(name = "screenPay")UUID uuid, @RequestParam(name = "pupilId")UUID pupilId){
        ApiResponse payment = pupilPaymentService.payment(uuid, pupilId);
        return ResponseEntity.status(payment.isSuccess() ? 200 : 409).body(payment);
    }
}
