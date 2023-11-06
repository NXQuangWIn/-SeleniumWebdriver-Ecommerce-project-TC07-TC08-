package BAITAP;

/*
Verify that you will be able to save previously placed order as a pdf file

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on My Account link

3. Login in application using previously created credential

4. Click on 'My Orders'

5. Click on 'View Order'

6. Click on 'Print Order' link
 */

import Pom.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class testcase07 {
    @Test
    public static void testTC07() {
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

            //4. Click on 'My Orders' link
            registerPage.clickMyOrderLink();
            Thread.sleep(1000);

            //5. Click on 'View Order'
            registerPage.clickViewOrderLink();
            Thread.sleep(1000);

            //6. Click on 'Print Order' link
            registerPage.clickPrintOrderLink();
            Thread.sleep(3000);





        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
