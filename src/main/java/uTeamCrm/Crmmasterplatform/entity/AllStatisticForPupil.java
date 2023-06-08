package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

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

    @OneToMany
    private List<MonthlyStatistics> monthlyStatistics; //necha oydan beri o'qishi

    @Column
    private Double allSum;//umumiy ketgan harajati

    @Column
    private Double allCost; //umumiy harajat

    private Integer studyDuration; //necha oydan beri o'qishi
}
