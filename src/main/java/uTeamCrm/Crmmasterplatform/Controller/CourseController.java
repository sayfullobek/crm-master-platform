package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.CourseRepo;
import uTeamCrm.Crmmasterplatform.entity.Course;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.service.CourseServise;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/course")
public class CourseController {
    private final CourseServise courseServise;
    private final CourseRepo courseRepo;

    @GetMapping
    public HttpEntity<?> getCourse() {
        List<Course> all = courseRepo.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneCourse(@PathVariable Integer id) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getCourse"));
        return ResponseEntity.ok(course);
    }

    @PostMapping
    public HttpEntity<?> addCourse(@RequestParam(name = "uzName") String uzName, @RequestParam(name = "ruName") String ruName, @RequestParam(name = "coursePrice") Double coursePrice, @RequestParam(name = "courseDuration") Double courseDuration, @RequestParam(name = "description") String description) {
        Course build = Course.builder().courseDuration(courseDuration).coursePrice(coursePrice).description(description).build();
        build.setRuName(ruName);
        build.setUzName(uzName);
        return ResponseEntity.ok(courseRepo.save(build));
    }

    @PutMapping("/upload/{id}")
    public HttpEntity<?> uploadPhoto(@PathVariable Integer id, @RequestParam(name = "photoId") UUID photoId) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getCourse"));
        course.setPhotoId(photoId);
        courseRepo.save(course);
        return ResponseEntity.ok(new ApiResponse("saqlandi", true));
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCourse(@PathVariable Integer id, @RequestParam(name = "uzName") String uzName, @RequestParam(name = "ruName") String ruName, @RequestParam(name = "coursePrice") Double coursePrice, @RequestParam(name = "courseDuration") Double courseDuration, @RequestParam(name = "description") String description) {
        Course course = courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getCourse"));
        course.setCourseDuration(courseDuration);
        course.setCoursePrice(coursePrice);
        course.setDescription(description);
        course.setRuName(ruName);
        course.setUzName(uzName);
        courseRepo.save(course);
        return ResponseEntity.ok(new ApiResponse("saqlandi", true));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deletCourse(@PathVariable Integer id) {
        ApiResponse apiResponse = courseServise.deleteCourse(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/removeFromPupil/{id}")
    public HttpEntity<?> removeFromPupil(@PathVariable UUID id, @RequestParam(name = "courseId") Integer courseId) {
        ApiResponse apiResponse = courseServise.removeCourseFromPupil(id, courseId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/buyCourse/{courseId}")
    public HttpEntity<?> buyCourse(@PathVariable Integer courseId, @RequestParam(name = "pupilId") UUID pupilId) {
        ApiResponse apiResponse = courseServise.buyCourse(courseId, pupilId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

}