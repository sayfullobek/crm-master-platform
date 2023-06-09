package uTeamCrm.Crmmasterplatform.Config;

import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.util.Properties;

public class InitConfig {
    public static boolean isStart(){
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("/application.properties").getInputStream());
            if (properties.getProperty("spring.jpa.hibernate.ddl-auto").equals("update")){
                return true;
            }else {
                String s = JOptionPane.showInputDialog("Sayfulla akadan tashqari qaysi gaday create yoki create-dropda run bermoqchi updatega o'tqaz yoki parolni ter");
                if (s != null && s.equals("gaday123")){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
