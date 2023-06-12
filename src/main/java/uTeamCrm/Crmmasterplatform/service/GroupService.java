package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.GroupRepo;
import uTeamCrm.Crmmasterplatform.entity.Academy;
import uTeamCrm.Crmmasterplatform.entity.Group;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepo groupRepo;

    public List<Group> getGroupOfOneAcademy(UUID id){
        List<Group> groups  = new ArrayList<>();
        for (Group group : groupRepo.findAll()) {
            for (Academy academy : group.getAcademies()) {
                if (id == academy.getId()){
                    Group build = Group.builder()
                            .pupils(group.getPupils())
                            .rooms(group.getRooms())
                            .teacher(group.getTeacher())
                            .course(group.getCourse())
                            .time(group.getTime())
                            .weekDays(group.getWeekDays())
                            .active(group.isActive())
                            .timeType(group.getTimeType())
                            .build();
                    groups.add(build);
                }
            }
        }
        return groups;
    }
}
