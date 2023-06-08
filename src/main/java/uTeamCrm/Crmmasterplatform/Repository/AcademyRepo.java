package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.Academy;

import java.util.UUID;

public interface AcademyRepo extends JpaRepository<Academy, UUID> {
}
