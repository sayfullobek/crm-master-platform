package uTeamCrm.Crmmasterplatform.Controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Http2;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.PaymentHistoryRepo;
import uTeamCrm.Crmmasterplatform.entity.PaymentHistory;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PaymentHistoryDto;
import uTeamCrm.Crmmasterplatform.service.PaymentHistoryService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/paymentHistory")
@RequiredArgsConstructor
public class PaymentHistoryController {

    private final  PaymentHistoryRepo paymentHistoryRepo;

    private final PaymentHistoryService paymentHistoryService;

    @PostMapping
    public HttpEntity<?> activatePupilAccount(@RequestBody PaymentHistoryDto paymentHistoryDto){
        ApiResponse apiResponse = paymentHistoryService.activatePupilAccount(paymentHistoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/list")
    public HttpEntity<?> getAll(){
        List<PaymentHistory> all = paymentHistoryRepo.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getMyPaymentHistory(@PathVariable UUID id){
        List<PaymentHistory> paymentHistories = paymentHistoryRepo.findPaymentHistoriesByUserId(id);
        return ResponseEntity.ok(paymentHistories);
    }

    @GetMapping("/lastMonth/{id}")
    public HttpEntity<?> getLastMonth(@PathVariable UUID id){
        List<PaymentHistory> lastMonthPayment = paymentHistoryService.getLastMonthPayment(id);
        return ResponseEntity.ok(lastMonthPayment);
    }

    @GetMapping("/pastMonth/{id}")
    public HttpEntity<?> getPastMonth(@PathVariable UUID id){
        List<PaymentHistory> paymentHistories = paymentHistoryRepo.findPaymentHistoriesByUserId(id);
        List<PaymentHistory> paymentHistories1 = new ArrayList<>();
        Date date = new Date();
        for (PaymentHistory paymentHistory : paymentHistories) {
            if (date.getMonth() - 1 == paymentHistory.getHowTime().getMonth()){
                paymentHistories1.add(paymentHistory);
            }
        }
        return ResponseEntity.ok(paymentHistories1);
    }
}
