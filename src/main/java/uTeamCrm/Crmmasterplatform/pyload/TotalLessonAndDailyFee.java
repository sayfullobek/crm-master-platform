package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalLessonAndDailyFee {
    private Double dailyFee;
    private Integer jamiDarslarSoni;
}
