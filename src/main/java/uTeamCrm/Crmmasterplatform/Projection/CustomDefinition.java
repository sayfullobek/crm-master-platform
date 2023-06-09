package uTeamCrm.Crmmasterplatform.Projection;

import org.springframework.data.rest.core.config.Projection;
import uTeamCrm.Crmmasterplatform.entity.Definition;

import java.util.UUID;

@Projection(types = Definition.class,name = "CustomDefinition")
public interface CustomDefinition {
    UUID getId();
    String getName();
    Double getPrice();
    Double getOldPrice();
    Integer getPupilSize();
    Double getSale();
    Integer getHowManyAcademyReferrals();
    Integer getHowMonthSale();
}
