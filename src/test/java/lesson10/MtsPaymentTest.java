package lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MtsPaymentTest extends BaseTest {

    @Test
    void checkBlockTitle() {
        MtsPage mtsPage = new MtsPage(driver);

        assertTrue(mtsPage.isBlockTitleDisplayed());
    }

    @Test
    void paymentSystemLogos() {
        MtsPage mtsPage = new MtsPage(driver);

        assertTrue(mtsPage.isVisaLogoDisplayed());
        assertTrue(mtsPage.isVerifiedVisaLogo());
        assertTrue(mtsPage.isMasterCardLogo());
        assertTrue(mtsPage.isSecureCodeLogo());
        assertTrue(mtsPage.isBelkartLogo());
    }

    @Test
    void checkMoreInfoLink() {
        MtsPage mtsPage = new MtsPage(driver);

        mtsPage.clickCookieAgreeButton();
        mtsPage.clickMoreInfoLink();
        mtsPage.waitForUrlContains("poryadok-oplaty");

        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty"));

    }

    @Test
    void checkContinueButtonForCommunicationServices() {

        MtsPage mtsPage = new MtsPage(driver);

        mtsPage.clickCookieAgreeButton();
        mtsPage.enterPhone("297777777");
        mtsPage.enterSum("100");
        mtsPage.clickContinue();

        assertTrue(driver.getPageSource().contains("Оплата")
                || driver.getPageSource().contains("Банковской картой")
                || driver.getCurrentUrl().contains("pay"));

    }

    @Test
    void checkPlaceholders() {

        MtsPage page = new MtsPage(driver);

        page.clickCookieAgreeButton();

        page.selectService("Услуги связи");
        assertEquals("Номер телефона", page.getPhonePlaceholder());
        assertEquals("Сумма", page.getSumPlaceholder());
        assertEquals("E-mail для отправки чека", page.getEmailPlaceholder());

        page.selectService("Домашний интернет");
        assertTrue(page.getPhonePlaceholder() != null);

        page.selectService("Рассрочка");
        assertTrue(page.getPhonePlaceholder() != null);

        page.selectService("Задолженность");
        assertTrue(page.getPhonePlaceholder() != null);
    }
}