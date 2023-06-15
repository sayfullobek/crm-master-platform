package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.hql.internal.classic.AbstractParameterInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.*;
import uTeamCrm.Crmmasterplatform.entity.*;
import uTeamCrm.Crmmasterplatform.entity.enums.ConditionName;
import uTeamCrm.Crmmasterplatform.entity.enums.RoleName;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.pyload.PupilDto;
import uTeamCrm.Crmmasterplatform.pyload.ReqPupil;
import uTeamCrm.Crmmasterplatform.pyload.SelectDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PupilService {

    private final AuthRepository authRepository;

    private final RoleRepository roleRepository;

    private final ConditionRepository conditionRepository;

    private final CourseRepo courseRepo;


    private final AllStaticForPupilRepo allStaticForPupilRepo;

    private final MonthlyStatisticsRepo monthlyStatisticsRepo;
    private final StudentDailyStatisticsRepo studentDailyStatisticsRepo;

    private final WalletRepo walletRepo;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public List<PupilDto> getNewPupil(String condition) {
        List<PupilDto> pupilDtos = new ArrayList<>();
        for (User user : authRepository.findAll()) {
            if (user.getCondition().getConditionName().name().equals(condition)) {
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
        }
        return pupilDtos;
    }

    public ApiResponse addNewPupil(ReqPupil reqPupil) {
        Condtion getCondition = conditionRepository.findById(reqPupil.getConditionId()).orElseThrow(() -> new ResourceNotFoundException("getCondition"));
        Role getRole = roleRepository.findById(5).orElseThrow(() -> new ResourceNotFoundException("getRole"));
        User build = User.builder().name(reqPupil.getName()).userSurname(reqPupil.getSurname()).phoneNumber(reqPupil.getPhoneNumber()).condition(getCondition).role(getRole).build();
        authRepository.save(build);
        return new ApiResponse("saqlandi", true);
    }

    public ApiResponse changeCondition(UUID uuid, Integer id) {
        Condtion getCondition = conditionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getCondition"));
        User getUser = authRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("getUser"));
        getUser.setCondition(getCondition);
        authRepository.save(getUser);
        return new ApiResponse("change to" + getCondition.getConditionName().name(), true);
    }

    public ApiResponse addRealPupil(ReqPupil reqPupil) {
        List<Course> courses = new ArrayList<>();
        for (SelectDto cours : reqPupil.getCourses()) {
            Course getCourse = courseRepo.findById(cours.getValue()).orElseThrow(() -> new ResourceNotFoundException("getCourse"));
            courses.add(getCourse);
        }
        Role getPupil = roleRepository.findById(5).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
        User user = new User(
                reqPupil.getName(),
                reqPupil.getSurname(),
                reqPupil.getMiddleName(),
                reqPupil.getPhoneNumber(),
                reqPupil.getUserName(),
                passwordEncoder().encode(reqPupil.getPassword()),
                getPupil,
                conditionRepository.findById(3).orElseThrow(() -> new ResourceNotFoundException("getRole")),
                courses
        );
        User save = authRepository.save(user);
        Wallet wallet = Wallet.builder().user(save).balance(0).saleProtsent(0).build();
        walletRepo.save(wallet);
        return new ApiResponse("saqlandi", true);
    }


//    public ApiResponse keldiKetti(UUID uuid){
//        User user = authRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
//        AllStatisticForPupil allStatisticForPupilByUserId = allStaticForPupilRepo.findAllStatisticForPupilByUserId(user.getId());
//    }
}
