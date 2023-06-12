package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uTeamCrm.Crmmasterplatform.Repository.GroupRepo;
import uTeamCrm.Crmmasterplatform.entity.Group;
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
    public HttpEntity<?> getGroupOfOneAcademy(@PathVariable UUID id){
        List<Group> groupOfOneAcademy = groupService.getGroupOfOneAcademy(id);
        return ResponseEntity.ok(groupOfOneAcademy);
    }

}
