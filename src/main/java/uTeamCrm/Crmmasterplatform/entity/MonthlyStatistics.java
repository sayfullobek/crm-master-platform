package uTeamCrm.Crmmasterplatform.entity;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.*;
import org.springframework.data.auditing.IsNewAwareAuditingHandler;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class MonthlyStatistics extends AbsEntity {
    @OneToMany
    private List<StudentDailyStatistics> studentDailyStatistics; //kunlik statistikaning ro'yxati

    @Column
    private Integer totalPercentage; //ushbu oyda toplagan foizi

    @Column
    private Integer numberOfAbsences; //kelmagan darslar soni

    @Column
    private Double monthlyPayment; //oylik to'laydigan summasi


    @Column
    private Integer howMuchLesson; // ushbu oyda necha marta dars bo'lishi
}
