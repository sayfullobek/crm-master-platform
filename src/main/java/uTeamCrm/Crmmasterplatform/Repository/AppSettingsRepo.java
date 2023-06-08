package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.AppSettings;

public interface AppSettingsRepo extends JpaRepository<AppSettings, Integer> {
}
