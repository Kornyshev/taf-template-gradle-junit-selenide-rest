package taf.template.core.utils;

import taf.template.fe.models.User;

public class DataUtils {

    public static final String SPACES_REG_EXP = "[\\r\\n]+";

    public static synchronized String timestamp() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static User getUserFromEnvironmentVariables() {
        return User.builder()
                .login(System.getProperty("user.email"))
                .password(System.getProperty("user.password"))
                .build();
    }

}
