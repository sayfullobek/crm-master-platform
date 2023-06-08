package uTeamCrm.Crmmasterplatform.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uTeamCrm.Crmmasterplatform.entity.template.AbsEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@RequiredArgsConstructor
public class Academy extends AbsEntity implements UserDetails {
    @Column(nullable = false, name = "academy_name")
    private String name; //academy nomi

    @Column(nullable = false, name = "academy_logo")
    private UUID logo; //ushbu academiyaning rasmi yani logosi

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Definition definition; //academiya qaysi terifdaligi

    @Column(nullable = false, name = "director_name")
    private String directorName; //directorining ismi

    @Column(nullable = false, name = "director_surname")
    private String directorSurname; //director familiyasi

    @Column(nullable = false, name = "director_middle_name")
    private String directorMiddleName; //director ochestva

    @Column(nullable = false, name = "director_phone_number")
    private String phoneNumber; //director tel raqami

    @Column(nullable = false, name = "director_username")
    private String userName; //director username

    @Column(nullable = false, name = "director_password")
    private String directorPassword;//director paroli

    @ManyToOne(optional = false)
    private Role role; //director roli

    @Column
    private Double balance;

    @OneToOne
    private AcademyBio academyBio; //academy biosi

    @Column(unique = true)
    private String link; // aynan bitta academiya uchun link

    private boolean enabled = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean accountNonExpired = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getPassword() {
        return directorPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }
}
