package uTeamCrm.Crmmasterplatform.pyload;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseDto {
    private String uzName;
    private String ruName;
    private Double coursePrice;
    private Double courseDuration;
    private String description;
//    private UUID academy_Id;
}
