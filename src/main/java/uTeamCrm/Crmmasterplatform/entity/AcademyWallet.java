package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;
import uTeamCrm.Crmmasterplatform.entity.template.AbsWallet;

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
