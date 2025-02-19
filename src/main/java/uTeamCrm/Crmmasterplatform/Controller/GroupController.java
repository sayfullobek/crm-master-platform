package uTeamCrm.Crmmasterplatform.Controller;

import com.sun.source.doctree.IndexTree;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
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

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable UUID id){
        Group getGroup = groupRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getGroup"));
        return ResponseEntity.ok(getGroup);
    }

    @GetMapping("/teacher/{id}")
    public HttpEntity<?> getTeacherGroups(@PathVariable UUID id){
        List<Group> teacherGroups = groupService.getTeacherGroups(id);
        return ResponseEntity.ok(teacherGroups);
    }

    @GetMapping("/pupil/{id}")
    public HttpEntity<?> getMyGroups(@PathVariable UUID id){
        List<Group> groupByPupilsId = groupRepo.findGroupByPupilsId(id);
        return ResponseEntity.ok(groupByPupilsId);
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


    @PutMapping("/frozen/{id}")
    public HttpEntity<?> frozed(@PathVariable UUID id){
        ApiResponse apiResponse = groupService.groupDisabled(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/removeFrom/{groupId}")
    public HttpEntity<?> removeFromGroup(@PathVariable UUID groupId, @RequestParam(name = "pupilId") UUID pupilId){
        ApiResponse apiResponse = groupService.removeFromGroup(pupilId, groupId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
}
