package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.Group;

import java.util.UUID;

public interface GroupRepo extends JpaRepository<Group, UUID> {

    boolean existsGroupsByNameEqualsIgnoreCase(String name);
}
