package pages;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullScreenModeTest extends AbstractClassTest {

//      Часть 3: Полноэкранный режим браузера Chrome
//    Откройте Google Chrome в полноэкранном режиме.
//    Перейдите на тестовую страницу.
//    Заполните форму, введя произвольные значения имени и электронной почты, нажмите кнопку "Отправить".
//    Обратите внимание на появившееся динамическое сообщение, которое должно соответствовать формату:
//            "Форма отправлена с именем: {Имя} и e-mail: {E-mail}"

    @Override
    public ChromeOptions createChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-fullscreen");
        return options;
    }

    @Test
    @DisplayName("Google Chrome в fullscreen режиме.\nЗаполнение и отправка формы")
    void sendForm() {
        page.fillForm("Мария", "mail@mail.ru");
        page.submitForm();
        String resultMessage = page.readSubmitMessage();

        boolean checkName = resultMessage.contains("Форма отправлена с именем:");
        boolean checkMariya = resultMessage.contains("Мария");
        boolean checkEmail = resultMessage.contains("email:");
        boolean checkMail = resultMessage.contains("mail@mail.ru");

        statusTest(checkName && checkMariya && checkEmail && checkMail,
                "Проверка отправки формы с именем и электронной почтой 'sendForm'");

        assertAll(
                () -> assertTrue(resultMessage.contains("Форма отправлена с именем:"), "Форма отправлена с именем"),
                () -> assertTrue(resultMessage.contains("Мария"), "Имя присутствует в форме"),
                () -> assertTrue(resultMessage.contains("email:"), "Email присутствует в форме"),
                () -> assertTrue(resultMessage.contains("mail@mail.ru"), "Email передан корректный")
        );
    }
}
