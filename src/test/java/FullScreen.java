//        Часть 3: Полноэкранный режим браузера Chrome
//        1. Откройте Google Chrome в **полноэкранном** режиме.
//        2. Перейдите на тестовую страницу.
//        3. Заполните форму, введя произвольные значения имени и электронной почты, нажмите кнопку **"Отправить"**.
//        4. Обратите внимание на появившееся динамическое сообщение, которое должно соответствовать формату:
//            > _"Форма отправлена с именем: {Имя} и e-mail: {E-mail}"_


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class FullScreen {

    WebDriver driver;
//    private static final Logger log = LogManager.getLogger(FullScreen.class);

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
