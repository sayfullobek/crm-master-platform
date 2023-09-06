package uTeamCrm.Crmmasterplatform.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.GroupRepo;
import uTeamCrm.Crmmasterplatform.Repository.PaymentHistoryRepo;
import uTeamCrm.Crmmasterplatform.Repository.WalletRepo;
import uTeamCrm.Crmmasterplatform.entity.Group;
import uTeamCrm.Crmmasterplatform.entity.PaymentHistory;
import uTeamCrm.Crmmasterplatform.entity.Wallet;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepo walletRepo;

    private final PaymentHistoryRepo paymentHistoryRepo;

    private final GroupRepo groupRepo;

    public ApiResponse getHowMuch(UUID pupilId, UUID groupId) {
        Group getGroup = groupRepo.findById(groupId).orElseThrow(() -> new ResourceNotFoundException("getGroup"));
        Date date = new Date();
        ApiResponse apiResponse = new ApiResponse();
        for (PaymentHistory paymentHistory : paymentHistoryRepo.findPaymentHistoriesByUserId(pupilId)) {
            if (paymentHistory.getHowTime().getMonth() == date.getMonth()) {
                if (paymentHistory.getGroup().getId().equals(getGroup.getId())) {
                    if (paymentHistory.getQoldiq().equals(0.0)) {
                        return new ApiResponse("ushbu oy uchun to'lov qilingan", false);
                    } else {
                        double howMuch = 0;
                        for (PaymentHistory history : paymentHistoryRepo.findPaymentHistoriesByGroupId(getGroup.getId())) {
                            if (history.getUser().getId().equals(pupilId)) {
                                howMuch = history.getQoldiq();
                            }
                        }
                        apiResponse = new ApiResponse("ohirgi marta to'lovdan so'ng qoldi: " + howMuch + "(usz)", true);
                    }
                }
            }
        }
        return apiResponse;
    }

    public ApiResponse payWithWallet(UUID groupId, UUID pupilId) {
        Wallet wallet = walletRepo.findWalletByUserId(pupilId);
        Date date = new Date();
        List<PaymentHistory> paymentHistoriesByGroupId = paymentHistoryRepo.findPaymentHistoriesByGroupId(groupId);
        for (PaymentHistory paymentHistory : paymentHistoriesByGroupId) {
            if (date.getMonth() == paymentHistory.getHowTime().getMonth()) {
                if (paymentHistory.getUser().getId().equals(pupilId)) {
                    if (wallet.getBalance() > paymentHistory.getQoldiq()) {
                        double v = wallet.getBalance() - paymentHistory.getQoldiq();
                        wallet.setBalance(v);
                        paymentHistory.setQoldiq(0.0);
                    } else {
                        double v = paymentHistory.getQoldiq() - wallet.getBalance();
                        paymentHistory.setQoldiq(v);
                        wallet.setBalance(0.0);
                    }
                }
                paymentHistoryRepo.save(paymentHistory);
            }
        }
        walletRepo.save(wallet);
        return new ApiResponse("pul yechib olindi", true);
    }
}
