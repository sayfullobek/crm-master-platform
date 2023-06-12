package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.AllStatisticForPupil;

import java.util.UUID;

public interface AllStaticForPupilRepo extends JpaRepository<AllStatisticForPupil, UUID> {

    AllStatisticForPupil findAllStatisticForPupilByUserId(UUID user_id);
}
