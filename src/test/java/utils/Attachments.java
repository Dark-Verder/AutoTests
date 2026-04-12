package utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.ByteArrayInputStream;

public class Attachments {

    public static void saveScreenshot(WebDriver driver) {
        byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Screenshot", "image/png",
                new ByteArrayInputStream(screenshot), ".png");
    }

    public static void saveElementScreenshot(WebElement element) {
        byte[] screenshot = element.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Element screenshot", "image/png",
                new ByteArrayInputStream(screenshot), ".png");
    }
}
