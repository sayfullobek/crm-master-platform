package uTeamCrm.Crmmasterplatform.Controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uTeamCrm.Crmmasterplatform.pyload.StatisticDto;
import uTeamCrm.Crmmasterplatform.service.StatisticService;

import java.util.UUID;


@RestController
@RequestMapping("/api/statistic")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping("/myStatistic/{id}")
    public HttpEntity<?> getMyStatistic(@PathVariable UUID id){
        StatisticDto tolovlar = statisticService.tolovlar(id);
        return ResponseEntity.ok(tolovlar);
    }
}
