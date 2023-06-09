package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.AttachmentContentRepository;
import uTeamCrm.Crmmasterplatform.Repository.AttachmentRepository;
import uTeamCrm.Crmmasterplatform.Repository.HelpRepo;
import uTeamCrm.Crmmasterplatform.entity.Attachment;
import uTeamCrm.Crmmasterplatform.entity.AttachmentContent;
import uTeamCrm.Crmmasterplatform.entity.Help;
import uTeamCrm.Crmmasterplatform.pyload.ApiResponse;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/help")
@RestController
@RequiredArgsConstructor
public class HelpController {

    private final HelpRepo helpRepo;

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    @GetMapping
    public HttpEntity<?> getHelp() {
        List<Help> all = helpRepo.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public HttpEntity<?> addHelp(@RequestParam(name = "name") String name, @RequestParam(name = "ruName") String ruName, @RequestParam(name = "link") String link){
        Help build = Help.builder().link(link).build();
        build.setUzName(name);
        build.setRuName(ruName);
        return ResponseEntity.ok(helpRepo.save(build));
    }

    @PutMapping("/upload/{id}")
    public HttpEntity<?> uploadVideo(@PathVariable Integer id, @RequestParam(name = "videoId")UUID videoId){
        Help getHelp = helpRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("getHelp"));
        getHelp.setVideoId(videoId);
        helpRepo.save(getHelp);
        return ResponseEntity.ok(new ApiResponse("saqlandi", true));
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteHelp(@PathVariable Integer id){
        Help gethelp = helpRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("gethelp"));
        Attachment getVideo = attachmentRepository.findById(gethelp.getVideoId()).orElseThrow(() -> new ResourceNotFoundException("getVideo"));
        AttachmentContent byAttachmentId = attachmentContentRepository.findByAttachmentId(getVideo.getId());
        attachmentContentRepository.delete(byAttachmentId);
        attachmentRepository.delete(getVideo);
        helpRepo.delete(gethelp);
        return ResponseEntity.ok(new ApiResponse("olib tashlandi", true));
    }
}
