package uTeamCrm.Crmmasterplatform.Projection;

import org.springframework.data.rest.core.config.Projection;
import uTeamCrm.Crmmasterplatform.entity.AcademyBio;

import java.util.UUID;

@Projection(types = AcademyBio.class, name = "CustomAcademyBio")
public interface CustomAcademyBio  {

    UUID getId();

    String getPhoneNumber();

    String getBioDescription();

}
