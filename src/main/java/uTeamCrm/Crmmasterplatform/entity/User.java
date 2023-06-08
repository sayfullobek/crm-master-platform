package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uTeamCrm.Crmmasterplatform.entity.enums.ConditionName;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "users")
public class User extends AbsEntity implements UserDetails {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "academy_in_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "academy_id")})
    private List<Academy> academies; //ushbu user qaysi academyaga tegishli

    @Column(name = "user_img")
    private UUID img; //ushbu userning rasmi

    @Column(nullable = false, name = "user_name")
    private String name; //userning ismi

    @Column(name = "user_surname")
    private String userSurname; //user familiyasi

    @Column(name = "user_middle_name")
    private String userMiddleName; //user ochestva

    @Column(nullable = false, name = "user_phone_number")
    private String phoneNumber; //user tel raqami

    @Column(name = "user_username")
    private String userName; //user username

    @Column(name = "user_password")
    private String userPassword;//user paroli

    @ManyToOne(optional = false)
    private Role role; //user roli

    @Enumerated(value = EnumType.STRING)
    private ConditionName conditionName; //o'quvchining holati

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_course",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    private List<Course> courses; //ushbu oquvchi yoki oqituvchi qaysi kurs boyicha

    private boolean enabled = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}