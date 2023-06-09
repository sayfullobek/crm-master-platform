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
    private final AuthRepository authRepository;

    @GetMapping
    public HttpEntity<?> getWallet() {
        try {
            List<Wallet> all = walletRepo.findAll();
            return ResponseEntity.ok(all);
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneWallet(@PathVariable UUID id) {
        try {
            Wallet getWallet = walletRepo.findById(id).orElseThrow(() -> new RuntimeException("getWallet"));
            return ResponseEntity.ok(getWallet);

        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping
    public HttpEntity<?> addWallet(@RequestParam(name = "user") UUID userId,
                                   @RequestParam(name = "balance") double balance,
                                   @RequestParam(name = "saleProtsent") double saleProtsent) {
        Wallet wallet = Wallet.builder()
                .balance(balance)
                .user(authRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("getUser")))
                .saleProtsent(saleProtsent)
                .build();
        walletRepo.save(wallet);
        return ResponseEntity.ok(new ApiResponse("saqlandi",true));
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editWallet(@PathVariable UUID id,
                                    @RequestParam(name = "balance") double balance,
                                    @RequestParam(name = "saleProtsent") double saleProtsent){
        Wallet getWallet = walletRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getWallet"));
        getWallet.setBalance(balance);
        getWallet.setSaleProtsent(saleProtsent);
        walletRepo.save(getWallet);
        return ResponseEntity.ok(new ApiResponse("saqlandi",true));
    }
    @PutMapping("/frozen/{id}")
    public HttpEntity<?> editFrozen(@PathVariable UUID id){
        Wallet getWallet = walletRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getWallet"));
        getWallet.setFrozen(true);
        walletRepo.save(getWallet);
        return ResponseEntity.ok(new ApiResponse("muzlatildi",true));
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteWallet(@PathVariable UUID id){
        Wallet getWallet = walletRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getWallet"));
        walletRepo.delete(getWallet);
        return ResponseEntity.ok(new ApiResponse("o'chirildi",true));
    }
}
