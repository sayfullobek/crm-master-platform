package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Repository.WalletRepo;
import uTeamCrm.Crmmasterplatform.entity.Wallet;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/wallet")
public class WalletController {
    private final WalletRepo walletRepo;

    @GetMapping("/{id}")
    public HttpEntity<?> getOneWallet(@PathVariable UUID id) {
        Wallet walletByUserId = walletRepo.findWalletByUserId(id);
        return ResponseEntity.ok(walletByUserId);
    }

    @PutMapping("/frozen/{id}")
    public HttpEntity<?> editFrozen(@PathVariable UUID id) {
        Wallet getWallet = walletRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getWallet"));
        getWallet.setFrozen(true);
        walletRepo.save(getWallet);
        return ResponseEntity.ok(new ApiResponse("muzlatildi", true));
    }
}
