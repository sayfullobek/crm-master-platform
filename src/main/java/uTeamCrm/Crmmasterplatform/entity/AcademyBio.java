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
public class AcademyBio extends AbsEntity {
    @Column(nullable = false, unique = true, name = "academy_bio")
    private String phoneNumber; //har bitta academyning tel raqami

    @Column(nullable = false, length = 999999, name = "academy_bio_description")
    private String bioDescription; //academiyaning ma'lumoti
}
