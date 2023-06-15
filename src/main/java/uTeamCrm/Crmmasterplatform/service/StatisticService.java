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
        User user = authRepository.findById(pupilId).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
        double allPrice = 0;
        for (Course cours : user.getCourses()) {
            allPrice = allPrice + cours.getCoursePrice();
        }
        return StatisticDto.builder()
                .tolovQildi(wallet.getBalance())
                .qoldi(allPrice - wallet.getBalance())
                .dailyfee(0.0)
                .totalPayment(allStatisticForPupil.getAllCost())
                .build();
    }
}
