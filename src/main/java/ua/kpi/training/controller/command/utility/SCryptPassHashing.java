package ua.kpi.training.controller.command.utility;

import com.lambdaworks.crypto.SCrypt;
import com.lambdaworks.crypto.SCryptUtil;

public class SCryptPassHashing {

    private static final int sCryptN = 16384;
    private static final int sCryptR = 8;
    private static final int sCryptP = 1;

    public SCryptPassHashing() {
    }

    public static String cryptPass(String passPlainText){
        return SCryptUtil.scrypt(passPlainText, sCryptN, sCryptR, sCryptP);
    }

    public static boolean validPassword(String passPlainText, String userPassHash){
        return SCryptUtil.check(passPlainText, userPassHash);
    }

}
