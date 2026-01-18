import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TreningTests {

    WebDriver driver;
    private final String TRAINING_URL = "https://otus.home.kartushin.su/training.html";


    @BeforeAll
    public static void init() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void driverStarter() {
//        log.fatal("Браузер запущен!!!!");
    }

    @AfterEach
    public void driverClose() {
        if (driver != null) {
            driver.close();
        }
    }



//        Часть 1: Headless режим браузера Chrome
//        1. Запустите Google Chrome в **headless** режиме.
//        2. Перейдите на вышеуказанный ресурс.
//        3. Введите в поле ввода текста значение **"ОТУС"**.
//        4. Проверьте, что отображенный текст соответствует введённому.¬
    @Test
    public void sendText() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(TRAINING_URL);

        var testInput = driver.findElement(By.xpath("//*[@id=\"textInput\"]"));
        testInput.sendKeys("ОТУС" + Keys.ENTER);
        assertEquals("ОТУС", testInput.getAttribute("value"));
    }


//        Часть 2: Режим Kiosk в браузере Chrome
//        1. Запустите Google Chrome в режиме **Kiosk**.
//        2. Перейдите на ресурс.
//        3. Кликните на кнопку **"Открыть модальное окно"**.
//        4. Удостоверьтесь, что модальное окно успешно открыто.
    @Test
    public void openModalWindow() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(TRAINING_URL);

        var modalWindowBtn = driver.findElement(By.xpath("//*[@id=\"openModalBtn\"]"));
        modalWindowBtn.click();
        String textModal = driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/h2")).getText();
        assertEquals("Это модальное окно", textModal);
        var closeModal = driver.findElement(By.xpath("//*[@id=\"closeModal\"]"));
        closeModal.click();
    }


//        Часть 3: Полноэкранный режим браузера Chrome
//        1. Откройте Google Chrome в **полноэкранном** режиме.
//        2. Перейдите на тестовую страницу.
//        3. Заполните форму, введя произвольные значения имени и электронной почты, нажмите кнопку **"Отправить"**.
//        4. Обратите внимание на появившееся динамическое сообщение, которое должно соответствовать формату:
//            > _"Форма отправлена с именем: {Имя} и e-mail: {E-mail}"_
    @Test
    public void sendForm() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        driver.get(TRAINING_URL);

        var testName = driver.findElement(By.xpath("//*[@id=\"name\"]"));
        var name = "Mariya";
        testName.sendKeys(name);

        var testEmail = driver.findElement(By.xpath("//*[@id=\"email\"]"));
        var email = "mariya@mail.ru";
        testEmail.sendKeys(email);

        var testBtn = driver.findElement(By.xpath("//*[@id=\"sampleForm\"]/button"));
        testBtn.click();

        var testMassage = driver.findElement(By.xpath("//*[@id=\"messageBox\"]")).getText();
        String temp = format("Форма отправлена с именем: %s и email: %s", name, email);
        assertEquals(temp, testMassage);
    }

}
