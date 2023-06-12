package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uTeamCrm.Crmmasterplatform.entity.TeacherWallet;

import java.util.UUID;

public interface TeacherWalletRepo extends JpaRepository<TeacherWallet, UUID> {

}
