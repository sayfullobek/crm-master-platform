package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.User;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
}
