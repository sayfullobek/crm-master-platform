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
public class AcademyBio extends AbsEntity {
    @Column(nullable = false, unique = true, name = "academy_bio")
    private String phoneNumber; //har bitta academyning tel raqami

    @Column(nullable = false, length = 999999, name = "academy_bio_description")
    private String bioDescription; //academiyaning ma'lumoti
}
