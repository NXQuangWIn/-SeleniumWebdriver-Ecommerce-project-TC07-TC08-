package BAITAP;
/* Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on my account link

3. Click Create an Account link and fill New User information excluding the registered Email ID.

4. Click Register

5. Verify Registration is done. Expected account registration done.

6. Go to TV menu

7. Add product in your wish list - use product - LG LCD

8. Click SHARE WISHLIST

9. In next page enter Email and a message and click SHARE WISHLIST

10.Check wishlist is shared. Expected wishlist shared successfully.

 */

import Pom.RegisterPage;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class testcase05 {
    public static void testTC05() {
        String firstName = "Quang";
        String lastName = "Nguyen";
        String email_address = "quangnguyen4202@gmail.com";
        String password = "quangnguyen123";
        String confirmPassword = password;

        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            RegisterPage registerPage = new RegisterPage(driver);
            //2. Click on my account link
            registerPage.clickMyAccountLink();

            Thread.sleep(1000);

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //2. Click on create account link
            registerPage.clickCreateAccountLink();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            registerPage.enterFirstName(firstName);
            registerPage.enterLastName(lastName);
            registerPage.enterEmail(email_address);
            registerPage.enterPassword(password);
            registerPage.enterConfirmPassword(confirmPassword);

            //4. Click Register button
            registerPage.clickRegisterButton();

            Thread.sleep(1000);

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            // Step 5. Verify Registration is done. Expected account registration done.
            WebElement registrationSuccessMsg = driver.findElement(By.xpath("//p[text()='Thank you for registering with Main Website Store.']"));
            Assert.assertTrue(registrationSuccessMsg.isDisplayed(), "Account registration failed.");

            // Step 6. Go to TV menu
            driver.findElement(By.linkText("TV")).click();

            // Step 7. Add product to your wish list - use product - LG LCD
            driver.findElement(By.xpath("//a[text()='LG LCD']")).click();
            WebElement addToWishlistButton = driver.findElement(By.xpath("//button[@title='Add to Wishlist']"));
            addToWishlistButton.click();

            // Step 8. Click SHARE WISHLIST
            driver.findElement(By.linkText("Share Wishlist")).click();

            // Step 9. In the next page, enter Email and a message and click SHARE WISHLIST
            String recipientEmail = "recipient@example.com";
            String message = "Check out this wishlist!";
            driver.findElement(By.id("email_address")).sendKeys(recipientEmail);
            driver.findElement(By.id("message")).sendKeys(message);
            driver.findElement(By.xpath("//button[@title='Share Wishlist']")).click();

            // Step 10. Check wishlist is shared. Expected wishlist shared successfully.
            WebElement shareSuccessMsg = driver.findElement(By.xpath("//li[@class='success-msg']//ul//li"));
            Assert.assertTrue(shareSuccessMsg.isDisplayed(), "Wishlist sharing failed.");






        }catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
