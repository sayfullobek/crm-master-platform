package uTeamCrm.Crmmasterplatform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uTeamCrm.Crmmasterplatform.Repository.AcademyRepo;
import uTeamCrm.Crmmasterplatform.Repository.RoomRepo;
import uTeamCrm.Crmmasterplatform.entity.Academy;
import uTeamCrm.Crmmasterplatform.entity.Room;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final AcademyRepo academyRepo;
    private final RoomRepo roomRepo;

    public ApiResponse addRoom(String uzName, String ruName, UUID academyId) {
        try {
            List<Academy> getAcademy = Collections.singletonList(
                    academyRepo.findById(academyId).orElseThrow(() -> new ResourceNotFoundException("getAcademy"))
            );
            Room room = new Room();
            room.setAcademies(getAcademy);
            room.setUzName(uzName);
            room.setRuName(ruName);
            roomRepo.save(room);
            return new ApiResponse("saqlandi", true);
        } catch (Exception e) {
            return new ApiResponse("hatolik", false);

        }
    }
    public ApiResponse editRoom(Integer id,String uzName, String ruName){
        try {
            Room room = roomRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getRoom"));
            room.setRuName(ruName);
            room.setUzName(uzName);
            roomRepo.save(room);
            return new ApiResponse("tahrirlandi",true);
        }catch (Exception e){
            return new ApiResponse("hatolik",false);
        }
    }
    public ApiResponse deleteRoom(Integer id){
        Room room = roomRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getRoom"));
        roomRepo.delete(room);
        return new ApiResponse("o'chirildi",true);
    }
}
