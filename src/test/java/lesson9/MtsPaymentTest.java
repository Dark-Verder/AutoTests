package lesson9;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MtsPaymentTest extends BaseTest {

    @Test
    void checkBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//section[@class='pay']//h2[contains(text(),'Онлайн пополнение')]"));
        assertTrue(blockTitle.isDisplayed());
    }

    @Test
    void checkPaymentSystemLogos() {
        WebElement visa = driver.findElement(
                By.xpath("//section[@class='pay']//img[@alt='Visa']")
        );

        WebElement verifiedVisa = driver.findElement(
                By.xpath("//section[@class='pay']//img[@alt='Verified By Visa']")
        );

        WebElement mastercard = driver.findElement(
                By.xpath("//section[@class='pay']//img[@alt='MasterCard']")
        );

        WebElement secureCode = driver.findElement(
                By.xpath("//section[@class='pay']//img[@alt='MasterCard Secure Code']")
        );

        WebElement belkart = driver.findElement(
                By.xpath("//section[@class='pay']//img[@alt='Белкарт']")
        );

        assertTrue(visa.isDisplayed());
        assertTrue(verifiedVisa.isDisplayed());
        assertTrue(mastercard.isDisplayed());
        assertTrue(secureCode.isDisplayed());
        assertTrue(belkart.isDisplayed());
    }

    @Test
    void checkMoreInfoLink() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement link = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//section[contains(@class,'pay')]//a[contains(text(),'Подробнее о сервисе')]")
        ));

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", link
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);

        wait.until(ExpectedConditions.urlContains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));

        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty-i-bezopasnost-internet-platezhey"));
    }

    @Test
    void checkContinueButtonForCommunicationServices() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement cookieButton = wait.until(
                    ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));
            cookieButton.click();
        } catch (TimeoutException e) {
        }

        WebElement phoneInput = driver.findElement(By.id("connection-phone"));
        phoneInput.sendKeys("297777777");

        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.sendKeys("100");

        WebElement button = driver.findElement(By.xpath("//form[@id='pay-connection']//button"));
        button.click();

        assertTrue(driver.getPageSource().contains("Оплата")
                || driver.getPageSource().contains("Банковской картой")
                || driver.getCurrentUrl().contains("pay"));
    }
}

