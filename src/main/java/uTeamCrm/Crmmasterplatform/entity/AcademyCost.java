package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

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
