package uTeamCrm.Crmmasterplatform;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar instance = Calendar.getInstance();
        int actualMaximum = instance.getActualMaximum(Calendar.DAY_OF_MONTH);
        int i = actualMaximum / 7;
        System.out.println(i);
    }
}
