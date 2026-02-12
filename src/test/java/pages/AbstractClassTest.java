package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

abstract class AbstractClassTest {

    static final Logger log = LogManager.getLogger(AbstractClassTest.class);
    protected static WebDriver driver;
    protected static ObjectPage page;

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

        Properties url = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/url.properties")) {
            url.load(fis); // читаем файл свойств
        }
        String trainingUrl = url.getProperty("training.url");

        ChromeOptions options = createChromeOptions();  // опции браузера исходя из реализации конкретного теста
        driver = new ChromeDriver(options);
        driver.get(trainingUrl);
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
