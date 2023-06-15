package uTeamCrm.Crmmasterplatform.Controller;

import com.sun.source.doctree.IndexTree;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.GroupRepo;
import uTeamCrm.Crmmasterplatform.entity.Group;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PupilSelectDto;
import uTeamCrm.Crmmasterplatform.pyload.ReqGroup;
import uTeamCrm.Crmmasterplatform.service.GroupService;

import java.net.http.HttpClient;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepo groupRepo;

    private final GroupService groupService;

    @GetMapping
    public HttpEntity<?> getGroup(){
        List<Group> all = groupRepo.findAll();
        return ResponseEntity.ok(all);
    }

//    @GetMapping("/{id}")
//    public HttpEntity<?> getGroupOfOneAcademy(@PathVariable UUID id){
//        List<Group> groupOfOneAcademy = groupService.getGroupOfOneAcademy(id);
//        return ResponseEntity.ok(groupOfOneAcademy);
//    }

    @GetMapping("/teacher/{id}")
    public HttpEntity<?> getTeacherGroups(@PathVariable UUID id){
        List<Group> teacherGroups = groupService.getTeacherGroups(id);
        return ResponseEntity.ok(teacherGroups);
    }

    @PostMapping
    public HttpEntity<?> createGroup(@RequestBody ReqGroup reqGroup){
        ApiResponse group = groupService.createGroup(reqGroup);
        return ResponseEntity.status(group.isSuccess() ? 200 : 409).body(group);
    }

    @PostMapping("/addPupil")
    public HttpEntity<?> addPupilToGroup(@RequestParam(name = "groupId")UUID groupId, @RequestBody ReqGroup reqGroup){
        ApiResponse apiResponse = groupService.addPupilToGroup(groupId, reqGroup.getPupils());
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/yoqlama")
    public HttpEntity<?> yoqlama(@RequestParam(name = "pupilId") UUID pupilId, @RequestParam(name = "percentage") Integer percentage, @RequestParam(name = "nowDay")Integer nowDay, @RequestParam(name = "nowMonth")Integer nowMonth, @RequestParam(name = "trueOrFalse")boolean trueOrFalse){
        ApiResponse apiResponse = groupService.keldiKetti(pupilId, nowDay, nowMonth, percentage, trueOrFalse);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}
