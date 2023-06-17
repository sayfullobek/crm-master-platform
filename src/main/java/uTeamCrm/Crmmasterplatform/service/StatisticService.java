package uTeamCrm.Crmmasterplatform.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.*;
import uTeamCrm.Crmmasterplatform.entity.AllStatisticForPupil;
import uTeamCrm.Crmmasterplatform.entity.PaymentHistory;
import uTeamCrm.Crmmasterplatform.entity.Wallet;
import uTeamCrm.Crmmasterplatform.pyload.StatisticDto;

import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StudentDailyStatisticsRepo studentDailyStatisticsRepo;

    private final MonthlyStatisticsRepo monthlyStatisticsRepo;

    private final AllStaticForPupilRepo allStaticForPupilRepo;

    private final WalletRepo walletRepo;

    private final AuthRepository authRepository;

    private final PaymentHistoryRepo paymentHistoryRepo;

    public StatisticDto tolovlar(UUID pupilId) {
        Date date = new Date();
        AllStatisticForPupil allStatisticForPupil = allStaticForPupilRepo.findAllStatisticForPupilByUserId(pupilId);
        Double howMuch = 0.0;
        for (PaymentHistory paymentHistory : paymentHistoryRepo.findPaymentHistoriesByUserId(pupilId)) {
            if (paymentHistory.getHowTime().getMonth() == date.getMonth()) {
                howMuch = howMuch + paymentHistory.getHowMuch();
            }
        }
        return StatisticDto.builder()
                .tolovQildi(howMuch)
                .qoldi(allStatisticForPupil.getAllCost() < howMuch ? 0.0 : allStatisticForPupil.getAllCost() - howMuch)
                .dailyfee((double) Math.round(allStatisticForPupil.getDailyFee()))
                .totalPayment(allStatisticForPupil.getAllCost())
                .ketganHarajat(allStatisticForPupil.getAllSum())
                .build();
    }
}
