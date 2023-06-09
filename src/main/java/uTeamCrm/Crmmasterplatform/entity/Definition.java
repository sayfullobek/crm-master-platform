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
@Entity(name = "definitions")
public class Definition extends AbsEntity { //tariflar
    @Column(nullable = false, name = "definition_name")
    private String name; //ushbu tarifning nomi

    @Column(nullable = false, name = "definition_price")
    private Double price; //oylik narxi

    @Column(name = "definition_old_price")
    private Double oldPrice; //eski narx ustiga chiziladi

    @Column(nullable = false, name = "definition_pupil_size")
    private Integer pupilSize; //o'quvchilar soni

    @Column(nullable = false, name = "definition_sale")
    private Double sale; //ushbu tarifning referral codi chegirmasi

    @Column(nullable = false)
    private Integer howManyAcademyReferrals; //nechta academiyani referali orqali kirita olishi

    @Column(nullable = false)
    private Integer howMonthSale; //referal orqali kirgan academiya uchun necha oy chegirma bolishi
}
