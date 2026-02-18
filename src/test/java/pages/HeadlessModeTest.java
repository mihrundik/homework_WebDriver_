package pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

public class HeadlessModeTest extends AbstractClassTest {

//    Часть 1: TreningTests режим браузера Chrome
//    Запустите Google Chrome в headless режиме.
//    Перейдите на вышеуказанный ресурс.
//    Введите в поле ввода текста значение "ОТУС".
//    Проверьте, что отображенный текст соответствует введённому.

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        return options;
    }

    @Test
    @DisplayName("Google Chrome в headless режиме.\nВвод в текстовое поле значения 'ОТУС'")
    void sendText() {
        page.fillInputField("ОТУС");

        boolean result = page.checkInputValue("ОТУС");
        statusTest(result, "Проверка отправки текста в форму 'sendText'");

        assertTrue(result, "Проверка корректности вложения");
    }
}
