package pages;

import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;


public class TrainingTests {

//    Часть 1: TreningTests режим браузера Chrome
//    Запустите Google Chrome в headless режиме.
//    Перейдите на вышеуказанный ресурс.
//    Введите в поле ввода текста значение "ОТУС".
//    Проверьте, что отображенный текст соответствует введённому.
    @Nested
    @DisplayName("Google Chrome в headless режиме")
    class HeadlessTrainingTest extends AbstractClassTest {

        @Override
        public ChromeOptions createChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            return options;
        }

        @Test
        @DisplayName("Ввод в текстовое поле значения 'ОТУС'")
        void sendText() {
            page.fillInputField("ОТУС");

            boolean result = page.checkInputValue("ОТУС");
            statusTest(result, "Проверка отправки текста в форму 'sendText'");

            assertTrue(result, "Check correctness of input data");
        }
    }

//    Часть 2: Режим Kiosk в браузере Chrome
//    Запустите Google Chrome в режиме Kiosk.
//    Перейдите на ресурс.
//    Кликните на кнопку "Открыть модальное окно".
//    Удостоверьтесь, что модальное окно успешно открыто.
    @Nested
    @DisplayName("Google Chrome в kiosk режиме")
    class KioskModeTrainingTest extends AbstractClassTest {

        @Override
        public ChromeOptions createChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--kiosk");
            return options;
        }

        @Test
        @DisplayName("Открытие модального окна")
        void openModalWindow() {
            page.clickOpenModalButton();

            boolean result = "Это модальное окно".equals(page.getModalTitle());
            statusTest(result, "Проверка открытия модального окна 'openModalWindow'");

            assertEquals("Это модальное окно", page.getModalTitle(), "Modal window title");
            page.closeModal();
        }
    }

//    Часть 3: Полноэкранный режим браузера Chrome
//    Откройте Google Chrome в полноэкранном режиме.
//    Перейдите на тестовую страницу.
//    Заполните форму, введя произвольные значения имени и электронной почты, нажмите кнопку "Отправить".
//    Обратите внимание на появившееся динамическое сообщение, которое должно соответствовать формату:
//            "Форма отправлена с именем: {Имя} и e-mail: {E-mail}"
    @Nested
    @DisplayName("Google Chrome в fullscreen режиме")
    class FullScreenTrainingTest extends AbstractClassTest {

        @Override
        public ChromeOptions createChromeOptions() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-fullscreen");
            return options;
        }

        @Test
        @DisplayName("Заполнение и отправка формы")
        void sendForm() {
            page.fillForm("Марья", "mail@mail.ru");
            page.submitForm();
            String resultMessage = page.readSubmitMessage();

            boolean checkName = resultMessage.contains("Форма отправлена с именем:");
            boolean checkMariya = resultMessage.contains("Марья");
            boolean checkEmail = resultMessage.contains("email:");
            boolean checkMail = resultMessage.contains("mail@mail.ru");

            statusTest(checkName && checkMariya && checkEmail && checkMail,
                    "Проверка отправки формы с именем и электронной почтой 'sendForm'");

            assertAll(
                    () -> assertTrue(resultMessage.contains("Форма отправлена с именем:"), "Form sent with name"),
                    () -> assertTrue(resultMessage.contains("Марья"), "Name present in message"),
                    () -> assertTrue(resultMessage.contains("email:"), "Email mentioned in message"),
                    () -> assertTrue(resultMessage.contains("mail@mail.ru"), "Correct email passed in the message")
            );
        }
    }
}