package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.Definition;

import java.util.UUID;

public interface DefinitionRepo extends JpaRepository<Definition, UUID> {
}
