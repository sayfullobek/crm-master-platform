package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class AllStatisticForPupil extends AbsEntity {
    @OneToOne
    private User user; //o'quvchining umumiy statistikasi

    @ManyToMany
    private List<MonthlyStatistics> monthlyStatistics; //necha oydan beri o'qishi

    @Column
    private Double allSum;//umumiy ketgan harajati

    @Column
    private Double allCost; //umumiy harajat

    @Column
    private Double dailyFee; //har darsda to'laydigan summasi

    private Integer studyDuration; //necha oydan beri o'qishi
}
