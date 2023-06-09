package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.hql.internal.classic.AbstractParameterInformation;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Repository.ConditionRepository;
import uTeamCrm.Crmmasterplatform.Repository.RoleRepository;
import uTeamCrm.Crmmasterplatform.entity.Condtion;
import uTeamCrm.Crmmasterplatform.entity.Role;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PupilDto;
import uTeamCrm.Crmmasterplatform.pyload.ReqPupil;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PupilService {

    private final AuthRepository authRepository;

    private final RoleRepository roleRepository;

    private final ConditionRepository conditionRepository;
    public List<PupilDto> getNewPupil() {
        List<PupilDto> pupilDtos  = new ArrayList<>();
        for (User user : authRepository.findAll()) {
            PupilDto build = PupilDto.builder()
                    .uuid(user.getId())
                    .name(user.getName())
                    .surname(user.getUserSurname())
                    .phoneNumber(user.getPhoneNumber())
                    .role(user.getRole())
                    .condtion(user.getCondition())
                    .courseList(user.getCourses())
                    .build();
            pupilDtos.add(build);
        }
        return pupilDtos;
    }
    public ApiResponse addNewPupil(ReqPupil reqPupil){
        Condtion getCondition = conditionRepository.findById(reqPupil.getConditionId()).orElseThrow(() -> new ResourceNotFoundException("getCondition"));
        Role getRole = roleRepository.findById(5).orElseThrow(() -> new ResourceNotFoundException("getRole"));
        User build = User.builder().name(reqPupil.getName()).userSurname(reqPupil.getSurname()).phoneNumber(reqPupil.getPhoneNumber()).condition(getCondition).role(getRole).build();
        authRepository.save(build);
        return new ApiResponse("saqlandi", true);
    }
}
