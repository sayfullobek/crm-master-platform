package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatisticDto {
    private Double tolovQildi;
    private Double qoldi;
    private Double dailyfee;
    private Double totalPayment;
}
