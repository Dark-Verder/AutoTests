package lesson10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

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
        mtsPage.switchToPaymentFrame();

        assertEquals("Оплатить 100.00 BYN", mtsPage.getPayButton());
    }

    @Test
    void checkServiceDropdownOptions() {

        MtsPage page = new MtsPage(driver);

        page.clickCookieAgreeButton();

        page.openServiceDropdown();
        List<String> actualOptions = page.getServiceOptionsText();

        List<String> expectedOptions = List.of(
                "Услуги связи",
                "Домашний интернет",
                "Рассрочка",
                "Задолженность"
        );

        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    void paymentDetails() {

        MtsPage page = new MtsPage(driver);

        page.openPaymentForm("297777777", "100");

        assertEquals("100.00 BYN", page.getPaySum());
        assertEquals("Оплата: Услуги связи Номер:375297777777", page.getPhoneNumber());
        assertEquals("Номер карты", page.getCardNumberLableText());
        assertEquals("Срок действия", page.getExpirationDataLabelText());
        assertEquals("CVC", page.getCvcLabelText());
        assertEquals("Имя и фамилия на карте", page.getHolderNameLableText());
        assertTrue(page.isVisaLogoPDisplayed());
        assertTrue(page.isMasterCardPDisplayed());
        assertTrue(page.isBelkartLogoPDisplayed());
        assertTrue(page.isActiverandomLogo());
    }
}