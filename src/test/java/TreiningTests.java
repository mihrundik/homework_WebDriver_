import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TreiningTests {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(TreiningTests.class);
    private String PAGE = "https://otus.home.kartushin.su/training.html";
    Integer SECONDS = 5;

    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
        log.info("Начало тестирования");
    }

    @BeforeEach
    public void driverStarter(TestInfo testInfo) {
        log.fatal(format("Запуск теста: %s", testInfo.getDisplayName()));
    }

    @AfterEach
    public void driverClose() {
        if (driver != null) {
            driver.close();
        }
    }

    @AfterAll
    public static void endTests() {
        log.info("Конец тестирования\n");
    }


    public void chromeOptions(String args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(args);
        driver = new ChromeDriver(options);
        driver.get(PAGE);
        weitPage();
    }

    // запись результатов прохождения тестов в логи
    public void statusTest(boolean result, String message) {
        if (result) {
            log.info(message + ": Успешно пройден.");
        } else {
            log.error(message + ": Ошибка!");
        }
    }

    // явное ожидание
    public void weitPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SECONDS));
    }


//        Часть 1: Headless режим браузера Chrome
//        1. Запустите Google Chrome в **headless** режиме.
//        2. Перейдите на вышеуказанный ресурс.
//        3. Введите в поле ввода текста значение **"ОТУС"**.
//        4. Проверьте, что отображенный текст соответствует введённому.¬
    @Test
    public void sendText() {
        chromeOptions("--headless=new");

        var testInput = driver.findElement(By.xpath("//*[@id=\"textInput\"]"));
        testInput.sendKeys("ОТУС" + Keys.ENTER);
        assertEquals("ОТУС", testInput.getAttribute("value"));

        boolean isCorrect = "ОТУС".equals(testInput.getAttribute("value"));
        statusTest(isCorrect, "Проверка заполнения поля 'sendText'");
    }


//        Часть 2: Режим Kiosk в браузере Chrome
//        1. Запустите Google Chrome в режиме **Kiosk**.
//        2. Перейдите на ресурс.
//        3. Кликните на кнопку **"Открыть модальное окно"**.
//        4. Удостоверьтесь, что модальное окно успешно открыто.
    @Test
    public void openModalWindow() {
        chromeOptions("--kiosk");

        var modalWindowBtn = driver.findElement(By.xpath("//*[@id=\"openModalBtn\"]"));
        modalWindowBtn.click();
        String textModal = driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/h2")).getText();
        assertEquals("Это модальное окно", textModal);
        var closeModal = driver.findElement(By.xpath("//*[@id=\"closeModal\"]"));
        closeModal.click();

        boolean isCorrect = "Это модальное окно".equals(textModal);
        statusTest(isCorrect, "Проверка открытия модального окна 'openModalWindow'");
    }


//        Часть 3: Полноэкранный режим браузера Chrome
//        1. Откройте Google Chrome в **полноэкранном** режиме.
//        2. Перейдите на тестовую страницу.
//        3. Заполните форму, введя произвольные значения имени и электронной почты, нажмите кнопку **"Отправить"**.
//        4. Обратите внимание на появившееся динамическое сообщение, которое должно соответствовать формату:
//            > _"Форма отправлена с именем: {Имя} и e-mail: {E-mail}"_
    @Test
    public void sendForm() {
        chromeOptions("--start-fullscreen");

        var testName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        var name = "Mariya";
        testName.sendKeys(name);

        var testEmail = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        var email = "mariya@mail.ru";
        testEmail.sendKeys(email);

        var testBtn = driver.findElement(By.xpath("//*[@id=\"sampleForm\"]/button"));
        testBtn.click();

        String testMassage = driver.findElement(By.xpath("//*[@id=\"messageBox\"]")).getText();
        String temp = format("Форма отправлена с именем: %s и email: %s", name, email);
        assertEquals(temp, testMassage);

        boolean isCorrect = temp.equals(testMassage);
        statusTest(isCorrect, "Проверка отправки формы 'sendForm'");
    }

}


