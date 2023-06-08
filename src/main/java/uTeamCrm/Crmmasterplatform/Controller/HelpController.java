package uTeamCrm.Crmmasterplatform.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uTeamCrm.Crmmasterplatform.Repository.HelpRepo;
import uTeamCrm.Crmmasterplatform.entity.Help;

import java.util.List;

@RequestMapping("/api/help")
@RestController
@RequiredArgsConstructor
public class HelpController {

    private final HelpRepo helpRepo;

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

}
