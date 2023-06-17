package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.Group;
import uTeamCrm.Crmmasterplatform.entity.LidStatus;

import java.util.List;
import java.util.UUID;

public interface GroupRepo extends JpaRepository<Group, UUID> {

    boolean existsGroupsByNameEqualsIgnoreCase(String name);

    List<Group> findGroupByPupilsId(UUID pupils_id);
}
