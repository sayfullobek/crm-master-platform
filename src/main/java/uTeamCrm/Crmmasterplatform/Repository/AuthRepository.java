package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.Role;
import uTeamCrm.Crmmasterplatform.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);
    Optional<User> findUserByRole(Role role);
    User findUserByRoleAndId(Role role, UUID id);

}
