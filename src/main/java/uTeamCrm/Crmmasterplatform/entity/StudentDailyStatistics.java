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
public class StudentDailyStatistics extends AbsEntity {
    @Column
    private boolean todayActive;//bugun darsga keldimi

    @Column
    private Integer dailyPercentage;//kunlik 0 foizdan 100 foizgacha baholash

    @Column
    private Double dailyFee; //har darsda to'laydigan summasi
}
