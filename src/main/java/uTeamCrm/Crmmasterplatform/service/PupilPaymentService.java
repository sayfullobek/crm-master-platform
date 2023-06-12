package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Repository.PaymentHistoryRepo;
import uTeamCrm.Crmmasterplatform.Repository.PupilPaymentRepo;
import uTeamCrm.Crmmasterplatform.entity.PaymentHistory;
import uTeamCrm.Crmmasterplatform.entity.PupilPayment;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PupilPaymentDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PupilPaymentService {

    private final PupilPaymentRepo pupilPaymentRepo;

    private final AuthRepository authRepository;

    private final PaymentHistoryRepo paymentHistoryRepo;

    public ApiResponse payment(UUID uuid, UUID pupilId){
        User user = authRepository.findById(pupilId).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
        PupilPayment build = PupilPayment.builder().screenPay(uuid).user(user).build();
        pupilPaymentRepo.save(build);
        return new ApiResponse("Sizning to'lov chekingiz qabul qilindi ma'lumotlar tekshirilib to'lovlar to'g'ri bo'lsa  sizning accountingiz 1kun ichida activlashtiriladi", true);
    }

    public List<PupilPaymentDto> getPayments(){
        List<PupilPaymentDto> pupilPayments = new ArrayList<>();
        for (PupilPayment pupilPayment : pupilPaymentRepo.findAll()) {
            PupilPaymentDto build = PupilPaymentDto.builder()
                    .screenPay(pupilPayment.getScreenPay())
                    .phoneNumber(pupilPayment.getUser().getPhoneNumber())
                    .surname(pupilPayment.getUser().getUserSurname())
                    .name(pupilPayment.getUser().getName())
                    .userName(pupilPayment.getUser().getUsername())
                    .userId(pupilPayment.getUser().getId())
                    .build();
            pupilPayments.add(build);
        }
        return pupilPayments;
    }
}
