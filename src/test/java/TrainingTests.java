import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrainingTests extends AbstractClassTest {

    // ввод текста в Headless режиме
    @Test
    public void sendText() {
        ChromeSetup customChromeSetup = new ChromeSetup();
        List<String> options = Arrays.asList("--headless=new");
        WebDriver driver = customChromeSetup.setup(options);
        basicDriver = driver;        // передача в базовый класс созданного драйвера для очистки

        var testInput = driver.findElement(By.xpath("//*[@id=\"textInput\"]"));
        testInput.sendKeys("ОТУС" + Keys.ENTER);
        assertEquals("ОТУС", testInput.getAttribute("value"));

        boolean isCorrect = "ОТУС".equals(testInput.getAttribute("value"));
        statusTest(isCorrect, "Проверка заполнения поля 'sendText'");
    }


    // открытие модального окна в режиме kiosk
    @Test
    public void openModalWindow() {
        ChromeSetup customChromeSetup = new ChromeSetup();
        List<String> options = Arrays.asList("--kiosk");
        WebDriver driver = customChromeSetup.setup(options);
        basicDriver = driver;

        var modalWindowBtn = driver.findElement(By.xpath("//*[@id=\"openModalBtn\"]"));
        modalWindowBtn.click();
        String textModal = driver.findElement(By.xpath("//*[@id=\"myModal\"]/div/h2")).getText();
        assertEquals("Это модальное окно", textModal);
        var closeModal = driver.findElement(By.xpath("//*[@id=\"closeModal\"]"));
        closeModal.click();

        boolean isCorrect = "Это модальное окно".equals(textModal);
        statusTest(isCorrect, "Проверка открытия модального окна 'openModalWindow'");
    }


    // Часть 3: Отправляем форму в полноэкранном режиме
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
        assertEquals(temp, testMassage);

        boolean isCorrect = temp.equals(testMassage);
        statusTest(isCorrect, "Проверка отправки формы 'sendForm'");
    }
}
