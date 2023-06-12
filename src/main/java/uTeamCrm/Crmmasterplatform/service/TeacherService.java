package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.*;
import uTeamCrm.Crmmasterplatform.entity.*;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final AuthRepository authRepository;
    private final AcademyRepo academyRepo;
    private final CourseRepo courseRepo;
    private final RoleRepository repository;
    private final TeacherWalletRepo teacherWalletRepo;

    public ApiResponse addTeacher(UUID academyId, String name, String midlName, String phoneNumber, String surName, String userName, String userPassword, Integer courseId) {
        try {
            List<Academy> getAcademy = Collections.singletonList(academyRepo.findById(academyId).orElseThrow(() -> new ResourceNotFoundException("getAcademy")));
            List<Course> getCourse = Collections.singletonList(courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("getCourse")));
            Role getRole = repository.findById(3).orElseThrow(() -> new ResourceNotFoundException("getRole"));
            User build = User.builder()
                    .userPassword(userPassword)
                    .userSurname(surName)
                    .courses(getCourse)
                    .academies(getAcademy)
                    .name(name)
                    .userMiddleName(midlName)
                    .phoneNumber(phoneNumber)
                    .userName(userName)
                    .role(getRole)
                    .build();
            User save = authRepository.save(build);
            return new ApiResponse("saqlandi", true);
        } catch (Exception e) {
            return new ApiResponse("xato", false);
        }
    }

    public ApiResponse editTeacher(UUID id, String name, String midlName, String phoneNumber, String surName, String userName, String userPassword, Integer courseId) {
        try {
            User getTeacher = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getTeacher"));
            List<Course> getCourse = Collections.singletonList(courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("getCourse")));
            getTeacher.setName(name);
            getTeacher.setCourses(getCourse);
            getTeacher.setUserName(userName);
            getTeacher.setUserSurname(surName);
            getTeacher.setUserPassword(userPassword);
            getTeacher.setPhoneNumber(phoneNumber);
            getTeacher.setUserMiddleName(midlName);
            authRepository.save(getTeacher);
            return new ApiResponse("saqlandi", true);
        } catch (Exception e) {
            return new ApiResponse("xato", false);
        }

    }

    public ApiResponse deleteTeacher(UUID id) {
        try {
            User getTeacher = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getTeacher"));
            authRepository.delete(getTeacher);
            return new ApiResponse("O'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("xato", false);
        }
    }

    public ApiResponse addTeacherWallet(Double monthlyFee, UUID userId) {
        Role getRole = repository.findById(3).orElseThrow(() -> new ResourceNotFoundException("getRole"));
        User userByRoleAndId = authRepository.findUserByRoleAndId(getRole, userId);
        try {
            TeacherWallet build = TeacherWallet.builder()
                    .monthlyFee(monthlyFee)
                    .teacher(userByRoleAndId)
                    .build();
            teacherWalletRepo.save(build);
            return new ApiResponse("saqlandi", true);
        }catch (Exception e) {
            return new ApiResponse("error", false);
        }
    }
    public ApiResponse editTeacherWallet(UUID userId,Double monthlyFee){
        TeacherWallet teacherWallet = teacherWalletRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("getWallet"));
        teacherWallet.setMonthlyFee(monthlyFee);
        teacherWalletRepo.save(teacherWallet);
        return new ApiResponse("saqlandi",true);
    }
}
