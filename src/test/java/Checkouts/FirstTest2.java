package Checkouts;

    import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

    public class FirstTest2 {

        public static void main(String[] args) {

            WebDriverManager.chromedriver().setup();

            WebDriver driver = new ChromeDriver();

            driver.get("https://google.com");

            driver.quit();
        }
    }