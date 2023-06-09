package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectDto {
    private Integer value;
    private String label;
}
