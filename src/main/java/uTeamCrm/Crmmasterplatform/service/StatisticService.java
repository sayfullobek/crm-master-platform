package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import uTeamCrm.Crmmasterplatform.Repository.*;
import uTeamCrm.Crmmasterplatform.entity.AllStatisticForPupil;
import uTeamCrm.Crmmasterplatform.entity.Course;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.entity.Wallet;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.StatisticDto;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatisticService {

    private final StudentDailyStatisticsRepo studentDailyStatisticsRepo;

    private final MonthlyStatisticsRepo monthlyStatisticsRepo;

    private final AllStaticForPupilRepo allStaticForPupilRepo;

    private final WalletRepo walletRepo;

    private final AuthRepository authRepository;

    public StatisticDto tolovlar(UUID pupilId){
        AllStatisticForPupil allStatisticForPupil = allStaticForPupilRepo.findAllStatisticForPupilByUserId(pupilId);
        Wallet wallet = walletRepo.findWalletByUserId(pupilId);
        return StatisticDto.builder()
                .tolovQildi(wallet.getBalance())
                .qoldi(allStatisticForPupil.getAllCost() - wallet.getBalance())
                .dailyfee((double) Math.round(allStatisticForPupil.getDailyFee()))
                .totalPayment(allStatisticForPupil.getAllCost())
                .ketganHarajat(allStatisticForPupil.getAllSum())
                .build();
    }
}
