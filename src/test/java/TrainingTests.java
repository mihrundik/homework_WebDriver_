import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;

public class TrainingTests extends AbstractClassTest {


    // Часть 1: TreningTests режим браузера Chrome
    //1. Запустите Google Chrome в headless режиме.
    //2. Перейдите на вышеуказанный ресурс.
    //3. Введите в поле ввода текста значение "ОТУС".
    //4. Проверьте, что отображенный текст соответствует введённому.
    @Test
    public void sendText() {
        ChromeSetup customChromeSetup = new ChromeSetup();
        List<String> options = Arrays.asList("--headless=new");
        WebDriver driver = customChromeSetup.setup(options);
        basicDriver = driver;        // передача в базовый класс созданного драйвера для очистки

        var testInput = driver.findElement(By.xpath("//*[@id=\"textInput\"]"));
        testInput.sendKeys("ОТУС" + Keys.ENTER);

        statusTest(
                "ОТУС".equals(testInput.getAttribute("value")),
                "Проверка отправки формы 'sendText'"
        );
    }


    // Часть 2: Режим Kiosk в браузере Chrome
    //1. Запустите Google Chrome в режиме Kiosk.
    //2. Перейдите на ресурс.
    //3. Кликните на кнопку "Открыть модальное окно".
    //4. Удостоверьтесь, что модальное окно успешно открыто.
    @Test
    public void openModalWindow() {
        ChromeSetup customChromeSetup = new ChromeSetup();
        List<String> options = Arrays.asList("--kiosk");
        WebDriver driver = customChromeSetup.setup(options);
        basicDriver = driver;

        var modalWindowBtn = driver.findElement(By.xpath("//*[@id=\"openModalBtn\"]"));
        modalWindowBtn.click();
        String textModal = driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/h2")).getText();

        var closeModal = driver.findElement(By.xpath("//*[@id=\"closeModal\"]"));
        closeModal.click();

        statusTest(
                "Это модальное окно".equals(textModal),
                "Проверка отправки формы 'sendText'"
        );
    }


    // Часть 3: Полноэкранный режим браузера Chrome
    //1. Откройте Google Chrome в полноэкранном режиме.
    //2. Перейдите на тестовую страницу.
    //3. Заполните форму, введя произвольные значения имени и электронной почты, нажмите кнопку "Отправить".
    //4. Обратите внимание на появившееся динамическое сообщение, которое должно соответствовать формату:
    //          "Форма отправлена с именем: {Имя} и e-mail: {E-mail}"
    @Test
    public void sendForm() {
        ChromeSetup customChromeSetup = new ChromeSetup();
        List<String> options = Arrays.asList("--start-fullscreen");
        WebDriver driver = customChromeSetup.setup(options);
        basicDriver = driver;

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

        statusTest(
                temp.equals(testMassage),
                "Проверка отправки формы 'sendForm'"
        );
    }

}
