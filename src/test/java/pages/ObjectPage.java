package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ObjectPage {

    public final WebDriverWait wait;

    // используемые в тестах элементы страницы
    @FindBy(id = "textInput")
    private WebElement inputField;

    @FindBy(id = "openModalBtn")
    private WebElement openModalButton;

    @FindBy(xpath = "//*[@id='myModal']/div/h2")
    private WebElement modalTitle;

    @FindBy(id = "closeModal")
    private WebElement closeModalButton;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='sampleForm']/button")
    private WebElement submitButton;

    @FindBy(id = "messageBox")
    private WebElement messageBox;

    // инициализируем класс и связываем объекты элементов на странице
    public ObjectPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        PageFactory.initElements(driver, this);
    }

    // функция заполнения поля ввода текстом
    public void fillInputField(String value) {
        inputField.clear();
        inputField.sendKeys(value);
    }

    // проверка значений поля ввода
    public boolean checkInputValue(String expectedValue) {
        return inputField.getAttribute("value").equals(expectedValue);
    }

    // кнопка поднятия модального окна
    public void clickOpenModalButton() {
        openModalButton.click();
    }

    // заголовка модального окна? чтоб убедится что это именно то, что нужно
    public String getModalTitle() {
        return modalTitle.getText();
    }

    // закрытие модального окна
    public void closeModal() {
        closeModalButton.click();
    }

    // заполнение полей формы (имя и почта)
    public void fillForm(String name, String email) {
        nameField.clear();
        nameField.sendKeys(name);
        emailField.clear();
        emailField.sendKeys(email);
    }

    // отправка заполненной формы
    public void submitForm() {
        submitButton.click();
    }

    // проверка результата отправки формы
    public String readSubmitMessage() {
        return messageBox.getText();
    }

}