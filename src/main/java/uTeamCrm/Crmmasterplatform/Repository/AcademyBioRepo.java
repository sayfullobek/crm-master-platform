package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import uTeamCrm.Crmmasterplatform.Projection.CustomAcademyBio;
import uTeamCrm.Crmmasterplatform.entity.AcademyBio;

import java.util.UUID;

@CrossOrigin
@RepositoryRestResource(path = "bio", collectionResourceRel = "list", excerptProjection = CustomAcademyBio.class)
public interface AcademyBioRepo extends JpaRepository<AcademyBio, UUID> {
}
