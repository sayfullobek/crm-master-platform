package uTeamCrm.Crmmasterplatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import uTeamCrm.Crmmasterplatform.Projection.CustomDefinition;
import uTeamCrm.Crmmasterplatform.entity.Definition;

import java.util.UUID;
@CrossOrigin
@RepositoryRestResource(path = "definition",collectionResourceRel = "list",excerptProjection = CustomDefinition.class )
public interface DefinitionRepo extends JpaRepository<Definition, UUID> {
}
