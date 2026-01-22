import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class ChromeSetup extends AbstractClassTest {
    private static final String PAGE = "https://otus.home.kartushin.su/training.html";
    Integer SECONDS = 5;

    private void weitPage(ChromeDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(SECONDS));
    }

    public WebDriver setup(List<String> arguments) {
        ChromeOptions options = new ChromeOptions();
        for (String args : arguments) {
            options.addArguments(args);
        }
        ChromeDriver driver = new ChromeDriver(options);
        driver.get(PAGE);
        weitPage(driver);
        return driver;
    }

}
