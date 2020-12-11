package pt.isel.ls.model.utils;

public class Utils {

    /**
     * Verify if the string is an Integer or not
     *
     * @param str String to verify
     * @return if the string is an Integer or not
     */
    public static boolean isInteger(String str) {
        return str != null && str.matches("-??[0-9]+");
    }

    /**
     * Verify if the string is a header component
     *
     * @param str String to verify
     * @return if the string is a header component
     */
    public static boolean isHeader(String str) {
        String[] pairs = str.split("\\|");
        return pairs.length > 1 || pairs[0].split(":").length == 2;
    }

    /**
     * Verify if the string is a parameter component
     *
     * @param str String to verify
     * @return if the string is a parameter component
     */
    public static boolean isParameter(String str) {
        String[] pairs = str.split("&");
        return pairs.length > 1 || pairs[0].split("=").length == 2;
    }
}
