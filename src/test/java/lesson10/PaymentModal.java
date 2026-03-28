package lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class PaymentModal {

    private WebDriver driver;
    private WebDriverWait wait;

    public PaymentModal(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By paymentFrame = By.xpath("//iframe[contains(@class,'payment-widget-iframe')]");

    public void switchToPaymentFrame() {
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(paymentFrame));
        driver.switchTo().frame(frame);
    }

    private By payButton = By.xpath("//button[.//span[contains(text(), 'Оплатить')]]");

    public String getPayButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(payButton)).getText();
    }

    private By paySum = By.xpath("//div[contains(@class,'pay-description__cost')]/span");

    public String getPaySum() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paySum)).getText();
    }

    private By phoneNumber = By.xpath("//div[contains(@class, 'pay-description__text')]/span");

    public String getPhoneNumber() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumber)).getText();
    }

    private By cardNumberLabel = By.xpath("//input[@id='cc-number']/following-sibling::label");
    private By expirationDateLabel = By.xpath("//input[@formcontrolname = 'expirationDate']/following-sibling::label");
    private By cvcLabel = By.xpath("//input[@formcontrolname = 'cvc']//following-sibling::label");
    private By holderNameLabel = By.xpath("//input[@formcontrolname = 'holder']/following-sibling::label");
    private By visaLogoP = By.xpath("//img[contains(@src, 'visa')]");
    private By masterCardP = By.xpath("//img[contains(@src, 'mastercard')]");
    private By belkartLogoP = By.xpath("//img[contains(@src, 'belkart')]");
    private By activerandomLogo = By.xpath("//div[contains(@class, 'cards-brands_random')]//img[contains(@style,'opacity: 1')]");

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

    public boolean isVisaLogoPDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(visaLogoP)).isDisplayed();
    }

    public boolean isMasterCardPDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(masterCardP)).isDisplayed();
    }

    public boolean isBelkartLogoPDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(belkartLogoP)).isDisplayed();
    }

    public boolean isActiveRandomLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activerandomLogo)).isDisplayed();
    }
}

