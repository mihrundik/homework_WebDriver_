import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

public abstract class AbstractClassTest {

    protected WebDriver basicDriver;        // для принимать копию из тестов
    static final Logger log = LogManager.getLogger(AbstractClassTest.class);

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
    public void driverStart(TestInfo testInfo) {
        log.info(format("Запуск теста: %s", testInfo.getDisplayName()));
    }

    @AfterEach
    public void driverClose() {
        if (basicDriver != null) {
            basicDriver.quit();      // quit очищает весь браузер, а close очищает вкладку
            basicDriver = null;      // из-за инициализации в каждом тесте принудительно очищаем драйвер и закрываем
        }
    }

    @AfterAll
    public static void endTests() {
        log.info("Конец тестирования\n");
    }

}
