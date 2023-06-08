package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AcademyCost extends AbsEntity { // ushbu class academyning kunlik harajati va academiy hisobidan yechilayotgan hisob kitob pullari

    @Column
    private Double dailyCost;

    @Column
    private String bio;
}
