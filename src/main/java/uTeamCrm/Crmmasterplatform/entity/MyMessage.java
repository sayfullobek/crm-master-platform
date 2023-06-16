package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MyMessage extends AbsNameEntity { // ushbu class foydalanuvchini hisobiga yani walletiga pul tushsa habar bo'lib borishi uchun ishlatilinadi

    private Date time; //  bu qisimga pul tashlangan vaqt kiritilani PaymentHistorydagi howTime fieldi

    private String message; // bu admin tomondan yuboriladigan habar

    @ManyToOne
    private User user; // bu Message sohibi
}
