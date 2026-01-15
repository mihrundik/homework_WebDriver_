//        Часть 1: Headless режим браузера Chrome
//        1. Запустите Google Chrome в **headless** режиме.
//        2. Перейдите на вышеуказанный ресурс.
//        3. Введите в поле ввода текста значение **"ОТУС"**.
//        4. Проверьте, что отображенный текст соответствует введённому.


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Headless {

    WebDriver driver;
//    private static final Logger log = LogManager.getLogger(Headless.class);

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void driverStarter() {
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

//        log.fatal("Браузер запущен!!!!");

        driver.get("https://otus.home.kartushin.su/training.html");
    }

    @AfterEach
    public void driverClose() {
        if (driver != null) {
            driver.close();
        }

    }
}
