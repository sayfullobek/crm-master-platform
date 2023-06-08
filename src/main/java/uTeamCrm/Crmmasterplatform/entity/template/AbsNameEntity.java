package uTeamCrm.Crmmasterplatform.entity.template;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbsNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String uzName;

    @Column(nullable = false)
    private String ruName;
}
