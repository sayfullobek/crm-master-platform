package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsNameEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class LidStatus extends AbsNameEntity {//ushbu table o'quvchilar qayerdan kelganini statistikaga olish uchun kk
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "academy_in_lid_status",
            joinColumns = {@JoinColumn(name = "lidStatus_id")},
            inverseJoinColumns = {@JoinColumn(name = "academy_id")})
    private List<Academy> academies; //ushbu lid status qaysi academyaga tegishli
}
