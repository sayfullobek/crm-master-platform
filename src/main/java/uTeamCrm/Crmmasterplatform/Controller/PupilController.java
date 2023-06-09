package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PupilDto;
import uTeamCrm.Crmmasterplatform.pyload.ReqPupil;
import uTeamCrm.Crmmasterplatform.service.PupilService;

import java.util.List;

@RestController
@RequestMapping("/api/pupil")
@RequiredArgsConstructor
public class PupilController {

    private final AuthRepository authRepository;

    private final PupilService pupilService;
    @GetMapping("/newpupil")
    public HttpEntity<?> getNewPupil(){
        List<PupilDto> newPupil = pupilService.getNewPupil();
        return ResponseEntity.ok(newPupil);
    }

    @PostMapping
    public HttpEntity<?> addNewPupil(@RequestBody ReqPupil reqPupil){
        ApiResponse apiResponse = pupilService.addNewPupil(reqPupil);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
