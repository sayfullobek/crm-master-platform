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
public class Referral extends AbsEntity {
    @Column(nullable = false, unique = true, name = "academy_referral_code")
    private String referralCode;

    @Column(nullable = false, name = "referral_how_many_academy")
    private Integer howManyAcademy; //nechta academy bizning referalimiz orqali ro'yxatdan o'tgan
}
