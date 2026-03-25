package lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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
}