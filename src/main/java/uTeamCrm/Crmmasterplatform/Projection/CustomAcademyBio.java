package uTeamCrm.Crmmasterplatform.Projection;

import org.springframework.data.rest.core.config.Projection;
import uTeamCrm.Crmmasterplatform.entity.AcademyBio;

import java.util.UUID;

@Projection(types = AcademyBio.class)
public interface CustomAcademyBio  {

    UUID getId();

}
