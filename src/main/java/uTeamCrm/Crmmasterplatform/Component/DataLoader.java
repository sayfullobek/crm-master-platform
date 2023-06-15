package uTeamCrm.Crmmasterplatform.Component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uTeamCrm.Crmmasterplatform.Repository.AuthRepository;
import uTeamCrm.Crmmasterplatform.Repository.ConditionRepository;
import uTeamCrm.Crmmasterplatform.Repository.RoleRepository;
import uTeamCrm.Crmmasterplatform.Repository.WeekDayRepo;
import uTeamCrm.Crmmasterplatform.entity.Condtion;
import uTeamCrm.Crmmasterplatform.entity.Role;
import uTeamCrm.Crmmasterplatform.entity.User;
import uTeamCrm.Crmmasterplatform.entity.WeekDay;
import uTeamCrm.Crmmasterplatform.entity.enums.ConditionName;
import uTeamCrm.Crmmasterplatform.entity.enums.RoleName;
import uTeamCrm.Crmmasterplatform.entity.enums.WeekDayName;

@Component
@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;

    private final AuthRepository authRepository;

    private final RoleRepository roleRepo;

    private final ConditionRepository conditionRepository;

    private final WeekDayRepo weekDayRepo;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;


    @Override
    public void run(String... args) throws Exception {
        if (init.equals("create-drop") || init.equals("create")) {
            for (RoleName value : RoleName.values()) {
                roleRepo.save(new Role(value));
            }
            for (ConditionName value : ConditionName.values()) {
                conditionRepository.save(new Condtion(value));
            }
            for (WeekDayName value : WeekDayName.values()) {
                weekDayRepo.save(new WeekDay(value));
            }
        }
    }
}
