package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Security.JwtTokenProvider;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.entity.enums.RoleName;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.GetMal;
import uTeamCrm.Crmmasterplatform.pyload.ReqLogin;
import uTeamCrm.Crmmasterplatform.pyload.ResToken;
import uTeamCrm.Crmmasterplatform.service.AuthService;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {
    private final AuthRepository authRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private final AuthenticationManager authenticationManager;

    private final AuthService authService;


    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody ReqLogin request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword())
        );
        User user = authRepository.findUserByPhoneNumber(request.getPhoneNumber()).get();
        ResToken resToken = new ResToken(generateToken(request.getPhoneNumber()));
        if (user.getRole().getRoleName().equals(RoleName.PUPIL)) {
            return ResponseEntity.ok(getmalumot(user, resToken, "/auth/user"));
        }
        return ResponseEntity.ok(getmalumot(user, resToken, "/auth/admin"));
    }



    @PutMapping("/upload/{id}")
    public HttpEntity<?> uploadPhotojon(@PathVariable UUID id, @RequestParam(name = "photoId") UUID photoId) {
        User user = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        user.setImg(photoId);
        authRepository.save(user);
        return ResponseEntity.ok(new ApiResponse("taxrirlandi", true));
    }





    public GetMal getmalumot(User user, ResToken resToken, String path) {
        return new GetMal(user, resToken);
    }


    public String generateToken(String phoneNumber) {
        User user = authRepository.findUserByPhoneNumber(phoneNumber).get();
        return jwtTokenProvider.generateToken(user.getId());
    }
}
