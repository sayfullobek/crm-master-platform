package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.WalletRepo;
import uTeamCrm.Crmmasterplatform.entity.Wallet;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepo walletRepo;

}
