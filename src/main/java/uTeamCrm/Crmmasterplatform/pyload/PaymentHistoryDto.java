package uTeamCrm.Crmmasterplatform.pyload;

import com.fasterxml.jackson.databind.DatabindException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.entity.enums.PayTypeName;

import javax.annotation.security.DenyAll;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentHistoryDto {
    private UUID historyId;
    private String name;
    private Double howMuch;
    private Date howTime;
    private Integer payType;
    private User pupil;
    private UUID pupilId;
    private String description;
    private PayTypeName payTypeName;
    private UUID groupId;
}
