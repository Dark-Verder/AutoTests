package lesson10;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.util.stream.Collectors;

import java.time.Duration;


public class MtsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public MtsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // ПРОВЕРКА НАДПИСИ "ОНЛАЙ ПОПОЛНЕНИЕ"
    private By blockTitle = By.xpath("//section[@class='pay']//h2[contains(text(),'Онлайн пополнение')]");

    public boolean isBlockTitleDisplayed() {
        return driver.findElement(blockTitle).isDisplayed();
    }

    // ПРОВЕРКА ЛОГОТИПОВ
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

    // МЕТОД ЗАКРЫВАЕТ КУКИ
    private By cookiesAgreeButton = By.id("cookie-agree");
    private By moreInfoLink = By.xpath("//section[contains(@class,'pay')]//a[contains(text(),'Подробнее о сервисе')]");

    public void clickCookieAgreeButton() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(cookiesAgreeButton));
        button.click();
    }

    // ПРОВЕРКА ИНФОССЫЛКИ
    public void clickMoreInfoLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(moreInfoLink));
        link.click();
    }

    public void waitForUrlContains(String text) {
        wait.until(ExpectedConditions.urlContains(text));
    }

    // ВЫПАДашка
    private By dropdown = By.xpath("//button[@class='select__header']");
    private By serviceOptions = By.xpath("//ul[@class='select__list']//p[@class='select__option']");

    public void openServiceDropdown() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dropdown));
        element.click();
    }

    public List<String> getServiceOptionsText() {
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(serviceOptions));

        return options.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // ЗАПОЛНЕНИЕ ПОЛЕЙ + КНОПКА ПРОДОЛЖИТЬ
    private By phoneInput = By.id("connection-phone");
    private By sumInput = By.id("connection-sum");
    private By continueButton = By.xpath("//form[@id='pay-connection']//button");

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

    // "ОТКРЫВАШКА"
    public void openPaymentForm(String phone, String sum) {
        clickCookieAgreeButton();
        enterPhone(phone);
        enterSum(sum);
        clickContinue();
        switchToPaymentFrame();
    }
}




