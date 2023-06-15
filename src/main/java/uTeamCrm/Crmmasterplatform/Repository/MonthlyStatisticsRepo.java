package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.MonthlyStatistics;

public interface MonthlyStatisticsRepo extends JpaRepository<MonthlyStatistics,Integer> {

    MonthlyStatistics findMonthlyStatisticsByNowMonth(Integer nowMonth);
}
