package lesson10;

import java.io.ByteArrayInputStream;
import java.time.Duration;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentModal {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By paymentFrame = By.xpath("//iframe[contains(@class,'payment-widget-iframe')]");
    private final By paymentModalContainer =
            By.cssSelector(".app-wrapper__content-container.app-wrapper__content-container_full");

    private final By payButton = By.xpath("//button[.//span[contains(text(), 'Оплатить')]]");
    private final By paySum = By.xpath("//div[contains(@class,'pay-description__cost')]/span");
    private final By paymentDescription = By.xpath("//div[contains(@class, 'pay-description__text')]/span");

    private final By cardNumberLabel = By.xpath("//input[@id='cc-number']/following-sibling::label");
    private final By expirationDateLabel =
            By.xpath("//input[@formcontrolname='expirationDate']/following-sibling::label");
    private final By cvcLabel =
            By.xpath("//input[@formcontrolname='cvc']/following-sibling::label");
    private final By holderNameLabel =
            By.xpath("//input[@formcontrolname='holder']/following-sibling::label");

    private final By visaLogo = By.xpath("//img[contains(@src, 'visa')]");
    private final By mastercardLogo = By.xpath("//img[contains(@src, 'mastercard')]");
    private final By belkartLogo = By.xpath("//img[contains(@src, 'belkart')]");
    private final By activeRandomLogo =
            By.xpath("//div[contains(@class, 'cards-brands_random')]//img[contains(@style, 'opacity: 1')]");

    public PaymentModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void switchToPaymentFrame() {
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentFrame));
        driver.switchTo().frame(frame);
    }

    public String getPayButtonText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payButton)).getText();
    }

    public WebElement getPayButtonElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payButton));
    }

    public String getPaySum() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paySum)).getText();
    }

    public String getPaymentDescriptionText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentDescription)).getText();
    }

    public String getCardNumberLabelText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cardNumberLabel)).getText();
    }

    public String getExpirationDateLabelText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(expirationDateLabel)).getText();
    }

    public String getCvcLabelText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cvcLabel)).getText();
    }

    public String getHolderNameLabelText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(holderNameLabel)).getText();
    }

    public boolean isVisaLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(visaLogo)).isDisplayed();
    }

    public boolean isMastercardLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(mastercardLogo)).isDisplayed();
    }

    public boolean isBelkartLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(belkartLogo)).isDisplayed();
    }

    public boolean isActiveRandomLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeRandomLogo)).isDisplayed();
    }

    public void savePaymentModalScreenshot() {
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentModalContainer));
        byte[] screenshot = modal.getScreenshotAs(OutputType.BYTES);
        Allure.addAttachment("Payment modal", new ByteArrayInputStream(screenshot));
    }
}


