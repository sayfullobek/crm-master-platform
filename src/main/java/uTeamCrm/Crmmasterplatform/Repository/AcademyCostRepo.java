package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import uTeamCrm.Crmmasterplatform.entity.AcademyCost;

import java.util.UUID;

@CrossOrigin
public interface AcademyCostRepo extends JpaRepository<AcademyCost, UUID> {
}
