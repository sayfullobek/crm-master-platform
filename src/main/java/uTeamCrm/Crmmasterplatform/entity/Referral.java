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
public class Referral extends AbsEntity {
    @Column(nullable = false, unique = true, name = "academy_referral_code")
    private String referralCode;

    @Column(nullable = false, name = "referral_how_many_academy")
    private Integer howManyAcademy; //nechta academy bizning referalimiz orqali ro'yxatdan o'tgan
}
