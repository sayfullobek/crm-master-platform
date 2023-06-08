package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.CourseRepo;
import uTeamCrm.Crmmasterplatform.entity.Course;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;


@Service
@RequiredArgsConstructor
public class CourseServise {
    private final CourseRepo courseRepo;

    public ApiResponse deleteCourse(Integer id) {
        try {
            Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("get Course"));
            courseRepo.delete(course);
            return new ApiResponse("successfully daleted", true);
        } catch (Exception e) {
            return new ApiResponse("Error", false);
        }
    }
}
