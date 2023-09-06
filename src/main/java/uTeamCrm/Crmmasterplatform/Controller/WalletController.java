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
import uTeamCrm.Crmmasterplatform.service.WalletService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/wallet")
public class WalletController {
    private final WalletRepo walletRepo;

    private final WalletService walletService;
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

    @GetMapping("/howMuch")
    public HttpEntity<?> getHowMuch(@RequestParam(name = "pupilId") UUID pupilId, @RequestParam(name = "groupId")UUID groupId){
        ApiResponse howMuch = walletService.getHowMuch(pupilId, groupId);
        return ResponseEntity.status(howMuch.isSuccess() ? 200 : 409).body(howMuch);
    }

    @PutMapping
    public HttpEntity<?> payWithWallet(@RequestParam(name = "groupId")UUID groupId, @RequestParam(name = "pupilId")UUID pupilId){
        ApiResponse apiResponse = walletService.payWithWallet(groupId, pupilId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
