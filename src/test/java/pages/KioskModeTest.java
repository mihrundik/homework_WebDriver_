package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KioskModeTest extends AbstractClassTest {

//    Часть 2: Режим Kiosk в браузере Chrome
//    Запустите Google Chrome в режиме Kiosk.
//    Перейдите на ресурс.
//    Кликните на кнопку "Открыть модальное окно".
//    Удостоверьтесь, что модальное окно успешно открыто.

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--kiosk");
        return options;
    }

    @Test
    @DisplayName("Google Chrome в kiosk режиме.\nОткрытие модального окна")
    void openModalWindow() {
        page.clickOpenModalButton();

        boolean result = "Это модальное окно".equals(page.getModalTitle());
        statusTest(result, "Проверка открытия модального окна 'openModalWindow'");

        assertEquals("Это модальное окно", page.getModalTitle(), "Проверка модального окна");
        page.closeModal();
    }
}
