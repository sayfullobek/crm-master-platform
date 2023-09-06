package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import uTeamCrm.Crmmasterplatform.Controller.PaymentHistoryController;
import uTeamCrm.Crmmasterplatform.Repository.*;
import uTeamCrm.Crmmasterplatform.entity.*;
import uTeamCrm.Crmmasterplatform.entity.enums.PayTypeName;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PaymentHistoryDto;
import uTeamCrm.Crmmasterplatform.pyload.PupilPaymentDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentHistoryService {

    private final PaymentHistoryRepo paymentHistoryRepo;

    private final AuthRepository authRepository;

    private final MyMessageRepository myMessageRepository;

    private final WalletRepo walletRepo;

    private final AllStaticForPupilRepo allStaticForPupilRepo;

    private final GroupRepo groupRepo;

    public ApiResponse activatePupilAccount(PaymentHistoryDto paymentHistoryDto) {
        User user = authRepository.findById(paymentHistoryDto.getPupilId()).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
        Group getGroup = groupRepo.findById(paymentHistoryDto.getGroupId()).orElseThrow(() -> new ResourceNotFoundException("getGroup"));
        Date date = new Date();
        PaymentHistory paymentHistory = PaymentHistory.builder()
                .user(user)
                .payTypeName(paymentHistoryDto.getPayType() == 1 ? PayTypeName.CASH : PayTypeName.CLICK)
                .howTime(paymentHistoryDto.getHowTime())
                .howMuch(paymentHistoryDto.getHowMuch())
                .build();
        MyMessage myMessage = MyMessage.builder().message(paymentHistoryDto.getDescription())
                .user(user)
                .time(new Date())
                .build();
        myMessage.setUzName(paymentHistoryDto.getName());
        myMessage.setRuName(paymentHistoryDto.getName());
        Wallet walletByUserId = walletRepo.findWalletByUserId(user.getId());
        double qoldiqSumma = 0;
        double summa = 0;
        List<PaymentHistory> paymentHistoriesByUserId = paymentHistoryRepo.findPaymentHistoriesByUserId(paymentHistoryDto.getPupilId());
        if (!paymentHistoriesByUserId.isEmpty()) {
            for (PaymentHistory history : paymentHistoriesByUserId) {
                if (history.getHowTime().getMonth() == date.getMonth()) {
                    if (history.getGroup().getId().equals(paymentHistoryDto.getGroupId())) {
                        if (paymentHistoryDto.getHowMuch() >= history.getQoldiq()) {
                            summa = paymentHistoryDto.getHowMuch() - history.getQoldiq();
                            qoldiqSumma = 0;
                        }else {
                            qoldiqSumma = history.getQoldiq() - paymentHistoryDto.getHowMuch();
                        }
                    }else {
                        if (paymentHistoryDto.getHowMuch() >= getGroup.getCourse().getCoursePrice()){
                            qoldiqSumma = 0;
                            summa = paymentHistoryDto.getHowMuch() - getGroup.getCourse().getCoursePrice();
                        }else {
                            qoldiqSumma = getGroup.getCourse().getCoursePrice() - paymentHistoryDto.getHowMuch();
                        }
                    }
                }else {
                    if (paymentHistoryDto.getHowMuch() >= getGroup.getCourse().getCoursePrice()){
                        qoldiqSumma = 0;
                        summa = paymentHistoryDto.getHowMuch() - getGroup.getCourse().getCoursePrice();
                    }else {
                        qoldiqSumma = getGroup.getCourse().getCoursePrice() - paymentHistoryDto.getHowMuch();
                    }
                }
            }
        }else {
            if (paymentHistoryDto.getHowMuch() >= getGroup.getCourse().getCoursePrice()){
                qoldiqSumma = 0;
                summa = paymentHistoryDto.getHowMuch() - getGroup.getCourse().getCoursePrice();
            }else {
                qoldiqSumma = getGroup.getCourse().getCoursePrice() - paymentHistoryDto.getHowMuch();
            }
        }
        paymentHistory.setQoldiq(qoldiqSumma);
        paymentHistory.setGroup(getGroup);
        walletByUserId.setBalance(walletByUserId.getBalance() + summa);
        walletRepo.save(walletByUserId);
        AllStatisticForPupil allStatisticForPupilByUserId = allStaticForPupilRepo.findAllStatisticForPupilByUserId(paymentHistoryDto.getPupilId());
        allStatisticForPupilByUserId.setAllSum(allStatisticForPupilByUserId.getAllSum() + paymentHistoryDto.getHowMuch());
        allStaticForPupilRepo.save(allStatisticForPupilByUserId);
        myMessageRepository.save(myMessage);
        paymentHistoryRepo.save(paymentHistory);
        return new ApiResponse("Ushbu o'quvchi accounti activlashtirildi", true);
    }

    public List<PaymentHistory> getLastMonthPayment(UUID pupilId) {
        Date date = new Date();
        List<PaymentHistory> paymentHistories = new ArrayList<>();
        List<PaymentHistory> paymentHistoriesByUserId = paymentHistoryRepo.findPaymentHistoriesByUserId(pupilId);
        if (!paymentHistoriesByUserId.isEmpty()) {
            for (PaymentHistory paymentHistory : paymentHistoriesByUserId) {
                if (date.getMonth() == paymentHistory.getHowTime().getMonth()) {
                    PaymentHistory build = PaymentHistory.builder()
                            .payTypeName(paymentHistory.getPayTypeName())
                            .howMuch(paymentHistory.getHowMuch())
                            .howTime(paymentHistory.getHowTime())
                            .user(paymentHistory.getUser())
                            .qoldiq(paymentHistory.getQoldiq())
                            .group(paymentHistory.getGroup())
                            .build();
                    build.setId(paymentHistory.getId());
                    paymentHistories.add(build);
                }
            }
            return paymentHistories;
        }
        return null;
    }

}
