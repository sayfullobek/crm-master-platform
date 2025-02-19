package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.enums.PayTypeName;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PaymentHistory extends AbsEntity {

    @Column
    private Double howMuch; // qancha to'lov qildi

    @Column Double qoldiq; // to'lov qilganda qolib ketgan summa agar to'lov to'liq qilinmagan

    @Column
    private Date howTime; // qachon to'lov qildi

    @Enumerated(value = EnumType.STRING)
    private PayTypeName payTypeName; // To'lov turi

    @ManyToOne
    private User user; // to'lov qilgan shahs

    @ManyToOne
    public Group group; // qaysi gruhga to'lov qilgani

}
