package uTeamCrm.Crmmasterplatform.entity;


import lombok.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PupilPayment extends AbsEntity {

    @Column(nullable = false)
    private UUID screenPay; // bu to'lov rasmi

    @ManyToOne
    private User user; // bu to'lov qilgan odam

}
