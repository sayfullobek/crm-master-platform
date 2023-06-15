package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uTeamCrm.Crmmasterplatform.entity.LidStatus;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqGroup {
    private UUID academyId;
    private String name;
    private Integer roomId;
    private UUID teacher;
    private List<PupilSelectDto> pupils;
    private Integer courseId;
    private List<SelectDto> weekdays;
    private String time;
    private Integer timeType;
}
