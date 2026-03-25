package lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MtsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By blockTitle = By.xpath("//section[@class='pay']//h2[contains(text(),'Онлайн пополнение')]");

    public boolean isBlockTitleDisplayed() {
        return driver.findElement(blockTitle).isDisplayed();
    }

    private By visaLogo = By.xpath("//section[@class='pay']//img[@alt='Visa']");
    private By verifiedVisaLogo = By.xpath("//section[@class='pay']//img[@alt='Verified By Visa']");
    private By masterCardLogo = By.xpath("//section[@class='pay']//img[@alt='MasterCard']");
    private By secureCodeLogo = By.xpath("//section[@class='pay']//img[@alt='MasterCard Secure Code']");
    private By belkartLogo = By.xpath("//section[@class='pay']//img[@alt='Белкарт']");

    public boolean isVisaLogoDisplayed() {
        return driver.findElement(visaLogo).isDisplayed();
    }

    public boolean isVerifiedVisaLogo() {
        return driver.findElement(verifiedVisaLogo).isDisplayed();
    }

    public boolean isMasterCardLogo() {
        return driver.findElement(masterCardLogo).isDisplayed();
    }

    public boolean isSecureCodeLogo() {
        return driver.findElement(secureCodeLogo).isDisplayed();
    }

    public boolean isBelkartLogo() {
        return driver.findElement(belkartLogo).isDisplayed();
    }

    private By cookiesAgreeButton = By.id("cookie-agree");
    private By moreInfoLink = By.xpath("//section[contains(@class,'pay')]//a[contains(text(),'Подробнее о сервисе')]");

    public void clickCookieAgreeButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cookiesAgreeButton));
        button.click();
    }

    public void clickMoreInfoLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
        link.click();
    }

    public void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    private By tabService = By.xpath("//*[contains(text(),'Услуги связи')]");

    public void selectService(String serviceName) {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@class,'select__header')]")));
        dropdown.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[normalize-space()='" + serviceName + "']")));
        option.click();
    }

    private By cookiesButton = By.id("cookie-agree");
    private By phoneInput = By.id("connection-phone");
    private By sumInput = By.id("connection-sum");
    private By continueButton = By.xpath("//form[@id='pay-connection']//button");
    private By emailInput = By.id("connection-email");

    public void enterPhone(String phone) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        input.sendKeys(phone);
    }

    public String getPhonePlaceholder() {
        return driver.findElement(phoneInput).getAttribute("placeholder");
    }

    public void enterSum(String sum) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(sumInput));
        input.sendKeys(sum);
    }

    public String getSumPlaceholder() {
        return driver.findElement(sumInput).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput))
                .getAttribute("placeholder");
    }

    public void clickContinue() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
    }
}




