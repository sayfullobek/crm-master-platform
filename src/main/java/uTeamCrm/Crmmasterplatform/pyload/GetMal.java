package uTeamCrm.Crmmasterplatform.pyload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uTeamCrm.Crmmasterplatform.entity.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetMal {
    private User user;
    private ResToken resToken;
}
