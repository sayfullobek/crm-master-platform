package uTeamCrm.Crmmasterplatform.entity;

import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.enums.DayType;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "groups")
public class Group extends AbsEntity { //guruhlar


    @Column(nullable = false)
    private String name; //guruh nomi

    @ManyToOne
    private Room room;//guruhga qaysi xonada dars bolishi

    @ManyToOne(optional = false)
    private User teacher;

    @ManyToMany
    @JoinTable(name = "group_pupils",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id")})
    private List<User> pupils;

    @ManyToOne(optional = false)
    private Course course;

    @Enumerated(value = EnumType.STRING)
    private DayType timeType; //guruhning vaqti

    @ManyToMany
    @JoinTable(name = "group_week_day",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "week_day_id")})
    private List<WeekDay> weekDays;

    @Column(nullable = false)
    private String time; //soat nechidan nechigacha dars bolishi


    private boolean active = true;
}
