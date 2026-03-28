package lesson10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

public class MtsPaymentTest extends BaseTest {

    private MtsPage mtsPage;
    private PaymentModal paymentModal;

    @BeforeEach
    void setPageObjects() {
        mtsPage = new MtsPage(driver);
        paymentModal = new PaymentModal(driver);
    }

    @Test
    void checkBlockTitle() {
        assertTrue(mtsPage.isBlockTitleDisplayed());
    }

    @Test
    void paymentSystemLogos() {

        assertTrue(mtsPage.isVisaLogoDisplayed());
        assertTrue(mtsPage.isVerifiedVisaLogo());
        assertTrue(mtsPage.isMasterCardLogo());
        assertTrue(mtsPage.isSecureCodeLogo());
        assertTrue(mtsPage.isBelkartLogo());
    }

    @Test
    void checkMoreInfoLink() {

        mtsPage.clickCookieAgreeButton();
        mtsPage.clickMoreInfoLink();
        mtsPage.waitForUrlContains("poryadok-oplaty");

        assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty"));

    }

    @Test
    void checkContinueButtonForCommunicationServices() {

        mtsPage.clickCookieAgreeButton();
        mtsPage.enterPhone("297777777");
        mtsPage.enterSum("100");
        mtsPage.clickContinue();

        paymentModal.switchToPaymentFrame();

        assertEquals("Оплатить 100.00 BYN", paymentModal.getPayButton());
    }

    @Test
    void checkServiceDropdownOptions() {

        mtsPage.clickCookieAgreeButton();
        mtsPage.openServiceDropdown();

        List<String> actualOptions = mtsPage.getServiceOptionsText();

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

        mtsPage.clickCookieAgreeButton();
        mtsPage.enterPhone("297777777");
        mtsPage.enterSum("100");
        mtsPage.clickContinue();

        paymentModal.switchToPaymentFrame();

        assertEquals("100.00 BYN", paymentModal.getPaySum());
        assertEquals("Оплата: Услуги связи Номер:375297777777", paymentModal.getPhoneNumber());
        assertEquals("Номер карты", paymentModal.getCardNumberLabelText());
        assertEquals("Срок действия", paymentModal.getExpirationDateLabelText());
        assertEquals("CVC", paymentModal.getCvcLabelText());
        assertEquals("Имя и фамилия на карте", paymentModal.getHolderNameLabelText());
        assertTrue(paymentModal.isVisaLogoPDisplayed());
        assertTrue(paymentModal.isMasterCardPDisplayed());
        assertTrue(paymentModal.isBelkartLogoPDisplayed());
        assertTrue(paymentModal.isActiveRandomLogoDisplayed());
    }
}