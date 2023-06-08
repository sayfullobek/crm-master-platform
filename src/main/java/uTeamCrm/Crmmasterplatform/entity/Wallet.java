package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

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
