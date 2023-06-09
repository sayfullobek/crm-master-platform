package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.RoomRepo;
import uTeamCrm.Crmmasterplatform.entity.Room;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;
import uTeamCrm.Crmmasterplatform.service.RoomService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/room")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final RoomRepo roomRepo;

    @GetMapping
    public HttpEntity<?> getRoom() {
        List<Room> all = roomRepo.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getRoomRepo(@PathVariable Integer id) {
        Room room = roomRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getRoom"));
        return ResponseEntity.ok(room);
    }

    @PostMapping
    public HttpEntity<?> addRoom(@RequestParam(name = "uzName") String uzName, @RequestParam(name = "ruName") String ruName, @RequestParam(name = "academyId") UUID academyId) {
        ApiResponse apiResponse = roomService.addRoom(uzName, ruName, academyId);
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editRoom(@PathVariable Integer id, @RequestParam(name = "uzName") String uzName, @RequestParam(name = "ruName") String ruName) {
        ApiResponse apiResponse = roomService.editRoom(id, uzName, ruName);
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteRoom(@PathVariable Integer id) {
        ApiResponse apiResponse = roomService.deleteRoom(id);
        return ResponseEntity.ok(apiResponse);
    }

}
