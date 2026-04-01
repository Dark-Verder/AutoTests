package lesson10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import utils.Attachments;
import io.qameta.allure.Allure;

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

        Allure.step("Close cookies", () -> {
            mtsPage.clickCookieAgreeButton();
        });

        Allure.step("Verify block title is displayed", () -> {
            assertTrue(mtsPage.isBlockTitleDisplayed());
        });

        Allure.step("Capture block title screenshot", () -> {
            Attachments.saveElementScreenshot(mtsPage.getBlockTitleElement());
        });
    }

    @Test
    void paymentSystemLogos() {

        Allure.step("Close cookies", () -> {
            mtsPage.clickCookieAgreeButton();
        });

        Allure.step("Verify Visa logo is displayed", () -> {
            assertTrue(mtsPage.isVisaLogoDisplayed());
        });

        Allure.step("Verify Verified by Visa logo is displayed", () -> {
            assertTrue(mtsPage.isVerifiedVisaLogo());
        });

        Allure.step("Verify MasterCard logo is displayed", () -> {
            assertTrue(mtsPage.isMasterCardLogo());
        });

        Allure.step("Verify SecureCode logo is displayed", () -> {
            assertTrue(mtsPage.isSecureCodeLogo());
        });

        Allure.step("Verify Belkart logo is displayed", () -> {
            assertTrue(mtsPage.isBelkartLogo());
        });

        Allure.step("Capture payment systems screenshot", () -> {
            Attachments.saveElementScreenshot(mtsPage.getPaymentLogosBlock());
        });
    }

    @Test
    void checkMoreInfoLink() {

        Allure.step("Close cookies", () -> {
            mtsPage.clickCookieAgreeButton();
        });

        Allure.step("Click More info link", () -> {
            mtsPage.clickMoreInfoLink();
        });

        Allure.step("Wait until URL contains expected text", () -> {
            mtsPage.waitForUrlContains("poryadok-oplaty");
        });

        Allure.step("Verify user is redirected to the payment procedure page", () -> {
            assertTrue(driver.getCurrentUrl().contains("poryadok-oplaty"));
        });

        Allure.step("Capture redirected page screenshot", () -> {
            Attachments.saveScreenshot(driver);
        });
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