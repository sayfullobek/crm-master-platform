package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Wallet extends AbsEntity {

    @OneToOne
    private User user; // homyon sohibi

    @Column
    private double balance; // pupil balansi

    @Column
    private double saleProtsent; // foydalanuvchi chegirmasi

    @Column
    private boolean frozen = true;


}
