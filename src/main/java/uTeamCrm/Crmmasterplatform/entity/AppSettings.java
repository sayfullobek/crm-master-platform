package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsNameEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AppSettings extends AbsNameEntity {
    private String bio;
}
