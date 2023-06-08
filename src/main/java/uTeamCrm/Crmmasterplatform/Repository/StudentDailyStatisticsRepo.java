package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.StudentDailyStatistics;

import java.util.UUID;

public interface StudentDailyStatisticsRepo extends JpaRepository<StudentDailyStatistics, UUID> {
}
