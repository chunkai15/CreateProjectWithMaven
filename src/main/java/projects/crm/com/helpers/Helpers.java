package projects.crm.com.helpers;

public class Helpers {
    public static String getCurrentDir() {
        String current = System.getProperty("user.dir") + "/";
        return current;
    }
}
