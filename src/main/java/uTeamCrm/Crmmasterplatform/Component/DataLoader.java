package uTeamCrm.Crmmasterplatform.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Repository.RoleRepository;
import uTeamCrm.Crmmasterplatform.entity.Role;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.entity.enums.RoleName;

@Component
@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final AuthRepository authRepository;

    private final RoleRepository roleRepo;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;


    @Override
    public void run(String... args) throws Exception {
        if (init.equals("create-drop") || init.equals("create")) {
            for (RoleName value : RoleName.values()) {
                roleRepo.save(new Role(value));
            }
            Role role = roleRepo.findById(2).get();
            authRepository.save(
                    new User(
                            "userjon",
                            "userbek",
                            "980009792",
                            passwordEncoder.encode("0009792"),
                            role));
        }
    }
}
