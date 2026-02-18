package pages;

public class EnvConfig {

    // читаем переменные из файла типа setenv.sh
    public static String getUrl() {
        return System.getenv("TEST_URL");
    }

}
