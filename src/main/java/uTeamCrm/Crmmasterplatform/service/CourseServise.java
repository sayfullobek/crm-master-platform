package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.AllStaticForPupilRepo;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Repository.CourseRepo;
import uTeamCrm.Crmmasterplatform.Repository.GroupRepo;
import uTeamCrm.Crmmasterplatform.entity.*;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;

import java.util.Calendar;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class CourseServise {
    private final CourseRepo courseRepo;

    private final AuthRepository authRepository;

    private final AllStaticForPupilRepo allStaticForPupilRepo;

    private final GroupService groupService;

    private final GroupRepo groupRepo;
    public ApiResponse deleteCourse(Integer id) {
        try {
            Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("get Course"));
            courseRepo.delete(course);
            return new ApiResponse("successfully daleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }


    public ApiResponse removeCourseFromPupil(UUID uuid, Integer courseId){
        User user = authRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("getPpuil"));
        Course getCourse = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("getCourse"));
        AllStatisticForPupil allStatisticForPupilByUserId = allStaticForPupilRepo.findAllStatisticForPupilByUserId(uuid);
        allStatisticForPupilByUserId.setAllCost(allStatisticForPupilByUserId.getAllCost() - getCourse.getCoursePrice());
        allStatisticForPupilByUserId.setDailyFee(allStatisticForPupilByUserId.getDailyFee());
        user.getCourses().remove(getCourse);
        allStaticForPupilRepo.save(allStatisticForPupilByUserId);
        authRepository.save(user);
        return new ApiResponse("course ushbu o'quvchidan olib tashlandi", true);
    }

    public ApiResponse buyCourse(Integer courseId, UUID pupilId){
        User user = authRepository.findById(pupilId).orElseThrow(() -> new ResourceNotFoundException("getPupil"));
        Course getCourse = courseRepo.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("getCourse"));
        user.getCourses().add(getCourse);
        authRepository.save(user);
        AllStatisticForPupil allStatisticForPupil = allStaticForPupilRepo.findAllStatisticForPupilByUserId(pupilId);
        allStatisticForPupil.setAllCost(allStatisticForPupil.getAllCost() + getCourse.getCoursePrice());
        allStaticForPupilRepo.save(allStatisticForPupil);
        return new ApiResponse("course muvaffaqiyatli qo'shildi", true);
    }
}
