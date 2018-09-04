import ua.kpi.training.controller.command.utility.SCryptPassHashing;
import ua.kpi.training.tracing.SOut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {
        Double d1 = 1d;
        Double d2 = 1d;
        System.out.println(d1 == d2);

        //char a = '514';
        char b = '\u0514';
        System.out.println(b);
        char d = '\u0202';
        System.out.println(d);
//        //char e = '0514';
        //char f = '\0202';
        SOut.println(SCryptPassHashing.cryptPass("user"));

    }
}


