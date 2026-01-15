//        Часть 2: Режим Kiosk в браузере Chrome
//        1. Запустите Google Chrome в режиме **Kiosk**.
//        2. Перейдите на ресурс.
//        3. Кликните на кнопку **"Открыть модальное окно"**.
//        4. Удостоверьтесь, что модальное окно успешно открыто.


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Kiosk {

    WebDriver driver;
//    private static final Logger log = LogManager.getLogger(Kiosk.class);

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
