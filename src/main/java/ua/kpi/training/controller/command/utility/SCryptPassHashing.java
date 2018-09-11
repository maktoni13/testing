package ua.kpi.training.controller.command.utility;

import com.lambdaworks.crypto.SCryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.kpi.training.logger.LoggerMessages;

/**
 * Class SCrypt pass hashing
 * <p> Contains password crypt and validation process by SCrypt algorithm
 *
 * @author Anton Makukhin
 */
public class SCryptPassHashing {
    private static final Logger LOGGER_SLF4J = LoggerFactory.getLogger(SCryptPassHashing.class);

    private static final int sCryptN = 16384;
    private static final int sCryptR = 8;
    private static final int sCryptP = 1;

    public SCryptPassHashing() {
    }

    public static String cryptPass(String passPlainText){
        return SCryptUtil.scrypt(passPlainText, sCryptN, sCryptR, sCryptP);
    }

    public static boolean validPassword(String passPlainText, String userPassHash){
        boolean result = false;
        try{
            result = SCryptUtil.check(passPlainText, userPassHash);
        } catch (IllegalArgumentException e){
            LOGGER_SLF4J.error(LoggerMessages.ERROR_SCRYPT_PASSWORD_VALIDATION_PROCESS);
        }
        return result;
    }

}
