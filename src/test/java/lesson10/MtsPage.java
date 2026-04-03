package lesson10;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MtsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By blockTitle =
            By.xpath("//section[@class='pay']//h2[contains(text(),'Онлайн пополнение')]");

    private final By visaLogo = By.xpath("//section[@class='pay']//img[@alt='Visa']");
    private final By verifiedVisaLogo = By.xpath("//section[@class='pay']//img[@alt='Verified By Visa']");
    private final By masterCardLogo = By.xpath("//section[@class='pay']//img[@alt='MasterCard']");
    private final By secureCodeLogo = By.xpath("//section[@class='pay']//img[@alt='MasterCard Secure Code']");
    private final By belkartLogo = By.xpath("//section[@class='pay']//img[@alt='Белкарт']");
    private final By paymentLogosBlock = By.xpath("//section[@class='pay']");

    private final By cookiesAgreeButton = By.id("cookie-agree");
    private final By moreInfoLink =
            By.xpath("//section[contains(@class,'pay')]//a[contains(text(),'Подробнее о сервисе')]");

    private final By serviceDropdown = By.xpath("//button[@class='select__header']");
    private final By serviceOptions = By.xpath("//ul[@class='select__list']//p[@class='select__option']");
    private final By serviceDropdownContainer =
            By.xpath("//section[contains(@class,'pay')]//div[@class='select']");

    private final By phoneInput = By.id("connection-phone");
    private final By sumInput = By.id("connection-sum");
    private final By continueButton = By.xpath("//form[@id='pay-connection']//button");

    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isBlockTitleDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle)).isDisplayed();
    }

    public WebElement getBlockTitleElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(blockTitle));
    }

    public boolean isVisaLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(visaLogo)).isDisplayed();
    }

    public boolean isVerifiedVisaLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(verifiedVisaLogo)).isDisplayed();
    }

    public boolean isMasterCardLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(masterCardLogo)).isDisplayed();
    }

    public boolean isSecureCodeLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(secureCodeLogo)).isDisplayed();
    }

    public boolean isBelkartLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(belkartLogo)).isDisplayed();
    }

    public WebElement getPaymentLogosBlock() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(paymentLogosBlock));
    }

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

    public void openServiceDropdown() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(serviceDropdown));
        element.click();
    }

    public List<String> getServiceOptionsText() {
        List<WebElement> options =
                wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(serviceOptions));

        return options.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getServiceDropdownContainerElement() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(serviceDropdownContainer));
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});",
                element
        );
    }

    public void enterPhone(String phone) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneInput));
        input.sendKeys(phone);
    }

    public void enterSum(String sum) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(sumInput));
        input.sendKeys(sum);
    }

    public void clickContinue() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        button.click();
    }
}




