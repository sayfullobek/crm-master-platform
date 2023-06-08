package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface AuthRepository extends JpaRepository<User, UUID> {

    Optional<User> findUserByPhoneNumber(String phoneNumber);

}
