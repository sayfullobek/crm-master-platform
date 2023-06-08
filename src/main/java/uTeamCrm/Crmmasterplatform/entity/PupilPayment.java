package uTeamCrm.Crmmasterplatform.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

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
