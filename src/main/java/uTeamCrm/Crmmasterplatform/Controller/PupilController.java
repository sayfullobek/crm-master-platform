package uTeamCrm.Crmmasterplatform.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.entity.enums.RoleName;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PupilDto;
import uTeamCrm.Crmmasterplatform.pyload.ReqPupil;
import uTeamCrm.Crmmasterplatform.service.PupilService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pupil")
@RequiredArgsConstructor
public class PupilController {

    private final AuthRepository authRepository;

    private final PupilService pupilService;

    @GetMapping("/newpupil/waiting")
    public HttpEntity<?> getWaiting(){
        List<PupilDto> newPupil = pupilService.getNewPupil("WAITING");
        return ResponseEntity.ok(newPupil);
    }

    @GetMapping("/newpupil/success")
    public HttpEntity<?> getSuccess(){
        List<PupilDto> newPupil = pupilService.getNewPupil("SUCCESS");
        return ResponseEntity.ok(newPupil);
    }

    @GetMapping("/newpupil/not")
    public HttpEntity<?> getNot(){
        List<PupilDto> newPupil = pupilService.getNewPupil("NOT");
        return ResponseEntity.ok(newPupil);
    }

    @GetMapping("/newpupil/request")
    public HttpEntity<?> getRequest(){
        List<PupilDto> newPupil = pupilService.getNewPupil("REQUEST");
        return ResponseEntity.ok(newPupil);
    }

    @PostMapping("/newpupil")
    public HttpEntity<?> addNewPupil(@RequestBody ReqPupil reqPupil){
        ApiResponse apiResponse = pupilService.addNewPupil(reqPupil);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/newpupil")
    public HttpEntity<?> changeCondition(@RequestParam(name = "pupilId")UUID uuid, @RequestParam(name = "id") Integer id){
        ApiResponse apiResponse = pupilService.changeCondition(uuid, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }


    @PostMapping
    public HttpEntity<?> addRealPupil(@RequestBody ReqPupil reqPupil){
        ApiResponse apiResponse = pupilService.addRealPupil(reqPupil);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
