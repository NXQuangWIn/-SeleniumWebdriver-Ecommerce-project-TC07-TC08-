/* Verify user is able to purchase product using registered email id (USE CHROME BROWSER)

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Login in application using previously created credential

4. Click on MY WISHLIST link

5. In next page, Click ADD TO CART link

 */


package BAITAP;

import Pom.CartPage;
import Pom.CheckoutPage;
import Pom.LoginPage;
import Pom.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;


//Login

public class testcase06 {
    @Test
    public static void testTC06() {
        int scc = 0;
        StringBuffer verificationErrors = new StringBuffer();
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            RegisterPage registerPage = new RegisterPage(driver);

            //2. Click on my account link
            registerPage.clickMyAccountLink();


            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //3. Login in application using previously created credential

            registerPage.enterEmail2("quangvipcc1234@gmail.com");
            registerPage.enterPassword2("quangvip123");

            registerPage.clickLoginButton();

            //4. Click on MY WISHLIST link
            registerPage.clickMyWishlistLink();

            Thread.sleep(1000);
            //5. In next page, Click ADD TO CART link
            registerPage.clickAddToCartButton();

            // 6. Enter general shipping country, state/province and zip for the shipping cost estimate
            CartPage cartPage = new CartPage(driver);
            cartPage.selectCountryByXPath("//*[@id=\"country\"]/option[234]");
            cartPage.selectStateByXPath("//*[@id='region_id']/option[13]");
            cartPage.enterZipCode("700");
            Thread.sleep(2000);

            // 7. Click Estimate
            cartPage.clickEstimateShipping();

            // 8. Verify Shipping cost generated
            By shippingCostLocator = By.xpath("//*[@id='co-shipping-method-form']/dl/dd/ul/li/label/span");
            WebElement shippingCostElement = driver.findElement(shippingCostLocator);
            String shippingCost = shippingCostElement.getText();
            assertEquals(5.00 * parseCurrencyToDouble(cartPage.getValueOfQuantityLocator()), parseCurrencyToDouble(shippingCost));

            // 9. Select Shipping Cost, Update Total
            cartPage.selectFlatRateShipping();
            cartPage.clickUpdateTotal();

            // 10. Verify shipping cost is added to total
            double totalCost = parseCurrencyToDouble(cartPage.getTotalCost());
            double subtotal = parseCurrencyToDouble(cartPage.getSubtotalLabel());
            double shippingHandling = parseCurrencyToDouble(cartPage.getShippingHandlingLabel());

            assertEquals(totalCost, subtotal + shippingHandling, 0.01);


            // 11. Click "Proceed to Checkout"
            cartPage.clickProceedToCheckout();

            // 12a. Enter Billing Information, and click Continue
            CheckoutPage checkoutPage = new CheckoutPage(driver);
            checkoutPage.selectXByXPath();
            checkoutPage.enterBillingAddress("Your Billing Address");
            checkoutPage.enterBillingCity("Your Billing City");
            checkoutPage.selectBillingCountry("//*[@id=\"billing:country_id\"]/option[234]");
            checkoutPage.selectBillingState("//*[@id=\"billing:region_id\"]/option[13]");
            checkoutPage.enterBillingZipCode("Your Billing Zip Code");
            checkoutPage.enterBillingTelephone("Your Billing Telephone");
            checkoutPage.clickContinueButton();
            Thread.sleep(2000);
            // 12b. Enter Shipping Information, and click Continue
            checkoutPage.clickShippingInformationLink();
            Thread.sleep(2000);
            checkoutPage.clickShippingInformationContinueButton();Thread.sleep(5000);


//            // 12
//            CheckoutPage checkoutPage = new CheckoutPage(driver);
//            checkoutPage.clickShipToThisAddressButton();
//            checkoutPage.clickBillingInformationButtonLocator();
//            Thread.sleep(2000);

            // 13. In Shipping Method, Click Continue
            checkoutPage.clickShippingMethodContinueButton();
            Thread.sleep(5000);

            // 14. In Payment Information select 'Check/Money Order' radio button. Click Continue
            checkoutPage.selectPaymentMethodCheckMoneyOrder();
            checkoutPage.clickPaymentInformationContinueButton();
            Thread.sleep(2000);

            // 15. Click 'PLACE ORDER' button
            checkoutPage.clickPlaceOrderButton();
            Thread.sleep(2000);

            // 16. Verify Order is generated. Note the order number
            String orderVerificationMessage = checkoutPage.getOrderVerificationMessage();
            String expectedOrderVerificationMessage = "YOUR ORDER HAS BEEN RECEIVED.";
            assertEquals(expectedOrderVerificationMessage, orderVerificationMessage);
            System.out.println("Order number: "+ checkoutPage.getOrderNumberLinkText());

            // Capture a screenshot
            captureScreenshot(driver, "TC06.png");


            // Debug purpose only
            Thread.sleep(2000);


        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();

    }
    // Include the captureScreenshot method from TC01
    public static void captureScreenshot(WebDriver driver, String fileName) {
        if (driver instanceof TakesScreenshot) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File(fileName);

            try {
                // Check if the target file already exists, and if so, delete it.
                if (targetFile.exists()) {
                    targetFile.delete();
                }

                Files.copy(screenshotFile.toPath(), targetFile.toPath());
                System.out.println("Screenshot saved as: " + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // Define a helper method to parse currency strings to double
    public static double parseCurrencyToDouble(String currencyString) {
        // Remove currency symbols, commas, and other non-numeric characters
        String cleanedString = currencyString.replaceAll("[^0-9.]", "");
        // Parse the cleaned string as a double
        return Double.parseDouble(cleanedString);
    }
}



