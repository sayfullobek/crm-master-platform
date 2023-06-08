package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import uTeamCrm.Crmmasterplatform.entity.template.AbsNameEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "courses")
public class Course extends AbsNameEntity {
    @Column(nullable = false)
    private Double coursePrice;//kursning narxi

    @Column(nullable = false)
    private Double courseDuration; //kursning davomiyligi

    @Column(nullable = false, length = 99999)
    private String description; //kursni tasvirlash

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "academy_course",
            joinColumns = {@JoinColumn(name = "course_id")},
            inverseJoinColumns = {@JoinColumn(name = "academy_id")})
    private List<Academy> academies; //ushbu course qaysi academyga tegishli

    @Column
    private UUID photoId;

}
