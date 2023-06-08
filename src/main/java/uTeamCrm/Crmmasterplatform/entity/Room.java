package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;
import uTeamCrm.Crmmasterplatform.entity.template.AbsNameEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Room extends AbsNameEntity {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "academy_in_rooms",
            joinColumns = {@JoinColumn(name = "room_is")},
            inverseJoinColumns = {@JoinColumn(name = "academy_id")})
    private List<Academy> academies; //ushbu xona qaysi academyga tegishli
}
