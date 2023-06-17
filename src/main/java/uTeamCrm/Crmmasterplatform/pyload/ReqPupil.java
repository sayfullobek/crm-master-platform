package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

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
    private String middleName;
    private List<SelectDto> courses;
    private UUID groupId;
}
