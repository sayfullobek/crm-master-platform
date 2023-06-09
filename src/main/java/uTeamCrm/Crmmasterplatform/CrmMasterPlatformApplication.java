package uTeamCrm.Crmmasterplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uTeamCrm.Crmmasterplatform.Config.InitConfig;

@SpringBootApplication
public class CrmMasterPlatformApplication {

	public static void main(String[] args) {
		if (InitConfig.isStart()) {
			SpringApplication.run(CrmMasterPlatformApplication.class, args);
		}else {
			System.err.println("parolni hato kiritding");
		}
	}

}
