package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uTeamCrm.Crmmasterplatform.Repository.MyMessageRepository;
import uTeamCrm.Crmmasterplatform.entity.MyMessage;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/myMessage")
@RequiredArgsConstructor
public class MyMessageController {

    private final MyMessageRepository myMessageRepository;


    @GetMapping("/{id}")
    public HttpEntity<?> getMyAllMessage(@PathVariable UUID id){
        List<MyMessage> myMessageByUserId = myMessageRepository.findMyMessageByUserId(id);
        return ResponseEntity.ok(myMessageByUserId);
    }

    @GetMapping("/last/{id}")
    public HttpEntity<?> getMyLastMessage(@PathVariable UUID id){
        List<MyMessage> myMessageByUserId = myMessageRepository.findMyMessageByUserId(id);
        MyMessage message = new MyMessage();
        for (MyMessage myMessage : myMessageByUserId) {
            message = myMessage;
        }
        return ResponseEntity.ok(message);
    }
}
