package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uTeamCrm.Crmmasterplatform.entity.enums.RoleName;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(value = EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }

    public Role(RoleName roleName) {
        this.roleName = roleName;
    }
}
