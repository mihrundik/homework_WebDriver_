package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public abstract class AbstractClassTest {

    static final Logger log = LogManager.getLogger(String.valueOf(AbstractClassTest.class));
    protected static WebDriver driver;
    protected static ObjectPage page;
    private static final String URL = EnvConfig.getUrl();

    protected abstract ChromeOptions createChromeOptions();

    // логирование успешности/неуспешности прохождения теста
    public void statusTest(boolean isCorrect, String message) {
        if (!isCorrect) {
            log.error("{}: Ошибка!", message);
        } else {
            log.info("{}: Успешно пройден.", message);
        }
    }


    @BeforeAll
    public static void startTests() {
        WebDriverManager.chromedriver().setup();
        log.info("Начало тестирования");
    }

    @BeforeEach
    public void driverStart(TestInfo testInfo) throws IOException {
        log.info("Запуск теста: {}", testInfo.getDisplayName());

        ChromeOptions options = createChromeOptions();  // опции браузера исходя из реализации конкретного теста
        driver = new ChromeDriver(options);
        driver.get(URL);
        page = new ObjectPage(driver);
    }

    @AfterEach
    public void driverClose() {
        if (driver != null) {
            driver.quit();              // закрываем браузер
            driver = null;              // сбрасываем ссылку на драйвер (на всякий непредвиденный случай)
        }
    }

    @AfterAll
    public static void endTests() {
        log.info("Конец тестирования\n");
    }

}
