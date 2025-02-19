package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import uTeamCrm.Crmmasterplatform.entity.AttachmentContent;

import java.util.UUID;

@CrossOrigin
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findByAttachmentId(UUID id);

}
