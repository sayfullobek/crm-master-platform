package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;
import uTeamCrm.Crmmasterplatform.entity.template.AbsWallet;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AcademyWallet extends AbsWallet {

    @OneToOne
    private Academy academies; //ushbu lid status qaysi academyaga tegishli

}
