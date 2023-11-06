package BAITAP;
/*
*  Verify you are able to change or reorder previously added product

 *  Test Data = QTY = 10

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Login in application using previously created credential

4. Click on 'REORDER' link , change QTY & click Update

5. Verify Grand Total is changed

6. Complete Billing & Shipping Information

7. Verify order is generated and note the order number

Expected outcomes:

1) Grand Total is Changed

2) Order number is generated

 */

import Pom.CartPage;
import Pom.CheckoutPage;
import Pom.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;

public class testcase08 {
    @Test
    public static void testTC08() {
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

            //4. Click on 'REORDER' link , change QTY & click Update
            //4a. Click on 'My Orders' link
            registerPage.clickMyOrderLink();
            Thread.sleep(1000);


            // Click on 'REORDER' link, change QTY & click Update
            registerPage.clickReOderLink();
            Thread.sleep(1000);

            registerPage.changeQuantity(10);

            registerPage.clickUpdateButton();

            //5. Verify Grand Total is changed
            //so sanh gia tien truoc va sau khi update quantity
            //double cai so dau = By.get....
            //double cai so sau = By.get...
            //if(cai so dau != cai so sau) --> system.out.println("PASS");
            //ELSE

            registerPage.verifyGrandTotalIsChanged();


            //6. Complete Billing & Shipping Information

            CartPage cartPage = new CartPage(driver);
            cartPage.selectCountryByXPath("//*[@id=\"country\"]/option[234]");
            cartPage.selectStateByXPath("//*[@id='region_id']/option[13]");
            cartPage.enterZipCode("700");
            Thread.sleep(2000);

            //6a. Click Estimate
            cartPage.clickEstimateShipping();

            // 9. Select Shipping Cost, Update Total
            cartPage.selectFlatRateShipping();
            cartPage.clickUpdateTotal();

            //click Proceed to checkout
            WebElement ProceedToCheckout = driver.findElement(By.xpath("//li[@class='method-checkout-cart-methods-onepage-bottom']//button[@title='Proceed to Checkout']//span//span[contains(text(),'Proceed to Checkout')]"));
            ProceedToCheckout.click();

            //BILLING INFORMATION
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
            //Enter Shipping Information, and click Continue
            checkoutPage.clickShippingInformationLink();
            Thread.sleep(2000);
            checkoutPage.clickShippingInformationContinueButton();Thread.sleep(5000);
            //In Shipping Method, Click Continue
            checkoutPage.clickShippingMethodContinueButton();
            Thread.sleep(5000);

            //In Payment Information select 'Check/Money Order' radio button. Click Continue
            checkoutPage.selectPaymentMethodCheckMoneyOrder();
            checkoutPage.clickPaymentInformationContinueButton();
            Thread.sleep(2000);

            //Click 'PLACE ORDER' button
            checkoutPage.clickPlaceOrderButton();
            Thread.sleep(2000);

            //7. Verify order is generated and note the order number
            String orderVerificationMessage = checkoutPage.getOrderVerificationMessage();
            String expectedOrderVerificationMessage = "YOUR ORDER HAS BEEN RECEIVED.";
            assertEquals(expectedOrderVerificationMessage, orderVerificationMessage);
            System.out.println("Order number: "+ checkoutPage.getOrderNumberLinkText());


        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();

    }

}
