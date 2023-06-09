package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReqPupil {
    private String name;
    private String  surname;
    private String phoneNumber;
    private Integer conditionId;
    private String userName;
    private String password;
}
