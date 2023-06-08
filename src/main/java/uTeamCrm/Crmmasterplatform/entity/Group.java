package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.enums.DayType;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "groups")
public class Group extends AbsEntity { //guruhlar
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "academy_in_group",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "academy_id")})
    private List<Academy> academies; //ushbu group qaysi academyaga tegishli

    @Column(nullable = false)
    private String name; //guruh nomi

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_rooms",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "room_id")})
    private List<Room> rooms;//guruhga qaysi xonada dars bolishi

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User teacher;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_pupils",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "pupil_id")})
    private List<User> pupils;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Course course;

    @Enumerated(value = EnumType.STRING)
    private DayType timeType; //guruhning vaqti

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "group_week_day",
            joinColumns = {@JoinColumn(name = "group_id")},
            inverseJoinColumns = {@JoinColumn(name = "week_day_id")})
    private List<WeekDay> weekDays;

    @Column(nullable = false)
    private String time; //soat nechidan nechigacha dars bolishi

    private boolean active = true;
}
