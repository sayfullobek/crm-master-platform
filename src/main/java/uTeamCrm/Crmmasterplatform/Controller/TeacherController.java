package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Repository.RoleRepository;
import uTeamCrm.Crmmasterplatform.Repository.TeacherWalletRepo;
import uTeamCrm.Crmmasterplatform.entity.Role;
import uTeamCrm.Crmmasterplatform.entity.TeacherWallet;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.service.TeacherService;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private final TeacherService teacherService;
    private final AuthRepository authRepository;
    private final RoleRepository repository;
    private final TeacherWalletRepo teacherWalletRepo;
    Role getRole = repository.findById(3).orElseThrow(() -> new ResourceNotFoundException("getRole"));

    @PostMapping
    public HttpEntity<?> addTeacher(@RequestParam(name = "academyId") UUID academyId, @RequestParam(name = "name") String name, @RequestParam(name = "midlName") String midlName, @RequestParam(name = "phoneNumber") String phoneNumber, @RequestParam(name = "surName") String surName, @RequestParam(name = "userName") String userName, @RequestParam(name = "userPassword") String userPassword, @RequestParam(name = "courseId") Integer courseId) {
        ApiResponse apiResponse = teacherService.addTeacher(academyId, name, midlName, phoneNumber, surName, userName, userPassword, courseId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping
    public HttpEntity<?> getTeacher() {
        Optional<User> userByRole = authRepository.findUserByRole(getRole);
        return ResponseEntity.ok(userByRole);
    }

    @GetMapping("{id}")
    public HttpEntity<?> getOneTeacher(@PathVariable UUID id) {
        User teacher = authRepository.findUserByRoleAndId(getRole, id);
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("{id}")
    public HttpEntity<?> editTeacher(@PathVariable UUID id, @RequestParam(name = "name") String name, @RequestParam(name = "midlName") String midlName, @RequestParam(name = "phoneNumber") String phoneNumber, @RequestParam(name = "surName") String surName, @RequestParam(name = "userName") String userName, @RequestParam(name = "userPassword") String userPassword, @RequestParam(name = "courseId") Integer courseId) {
        ApiResponse apiResponse = teacherService.editTeacher(id, name, midlName, phoneNumber, surName, userName, userPassword, courseId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteTeacher(@PathVariable UUID id) {
        ApiResponse apiResponse = teacherService.deleteTeacher(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PostMapping("/tWallet")
    public HttpEntity<?> addTeacherWallet(@RequestParam(name = "monthlyFee") Double monthlyFee, @RequestParam(name = "udrId") UUID userId) {
        ApiResponse apiResponse = teacherService.addTeacherWallet(monthlyFee, userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getTeacherWallet(@PathVariable UUID id){
        TeacherWallet teacherWallet = teacherWalletRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("get"));
        return ResponseEntity.ok(teacherWallet);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edtTeacherWallet(@PathVariable UUID id,@RequestParam(name = "monthlyFee") Double monthlyFee){
        ApiResponse apiResponse = teacherService.editTeacherWallet(id, monthlyFee);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

}