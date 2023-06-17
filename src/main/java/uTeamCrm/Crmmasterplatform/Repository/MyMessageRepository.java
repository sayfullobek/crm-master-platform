package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import uTeamCrm.Crmmasterplatform.entity.MyMessage;

import java.util.List;
import java.util.UUID;

@CrossOrigin
public interface MyMessageRepository extends JpaRepository<MyMessage, Integer> {

    List<MyMessage> findMyMessageByUserId(UUID user_id);
}
