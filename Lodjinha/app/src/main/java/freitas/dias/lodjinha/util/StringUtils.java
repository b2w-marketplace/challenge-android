package freitas.dias.lodjinha.util;


public class StringUtils {

    public static boolean notEmpty(String string) {
        return (string != null && string.trim().length() > 0);
    }
}
