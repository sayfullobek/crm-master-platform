package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uTeamCrm.Crmmasterplatform.entity.Condtion;
import uTeamCrm.Crmmasterplatform.entity.Course;
import uTeamCrm.Crmmasterplatform.entity.Role;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PupilDto {
    private UUID uuid;
    private String name;
    private String surname;
    private String phoneNumber;
    private Role role;
    private Condtion condtion;
    private List<Course> courseList;
}
