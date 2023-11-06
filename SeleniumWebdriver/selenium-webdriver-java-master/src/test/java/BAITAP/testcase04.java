package BAITAP;
/*

Test Steps:

1. Go to http://live.techpanda.org/

2. Click on �MOBILE� menu

3. In the list of all mobile , click on �ADD TO CART� for Sony Xperia mobile

4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed

"The requested quantity for "Sony Xperia" is not available.

5. Verify the error message

6. Then click on �EMPTY CART� link in the footer of list of all mobiles. A message "SHOPPING CART IS EMPTY" is shown.

7. Verify cart is empty

 */
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

@Test
public class testcase04 {
    public static void testTC04() {
        int scc = 0;
        StringBuffer verificationErrors = new StringBuffer();
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //2. Click on �MOBILE� menu
            driver.findElement(By.linkText("MOBILE")).click();
            Thread.sleep(2000);
            // 3. In mobile products list, click on "Add To Compare" for 2 mobiles (Sony Xperia & Iphone)
            driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(3) > ul:nth-child(2) > li:nth-child(2) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(2)")).click();
            driver.findElement(By.cssSelector("body > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(3) > div:nth-child(2) > div:nth-child(4) > ul:nth-child(2) > li:nth-child(2) > a:nth-child(2)")).click();

            // 4. Click on "COMPARE" button. A popup window opens

            driver.findElement(By.cssSelector("button[title='Compare'] span span")).click();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            Thread.sleep(2000);

            // 5. Verify the pop-up window and check that the products are reflected in it
            // Heading "COMPARE PRODUCTS" with selected products in it.
            String heading = driver.findElement(By.cssSelector("div[class='page-title title-buttons'] h1")).getText();
            String sony = driver.findElement(By.cssSelector("h2[class='product-name'] a[title='Sony Xperia']")).getText();
            String iphone = driver.findElement(By.cssSelector("h2[class='product-name'] a[title='IPhone']")).getText();
            AssertJUnit.assertEquals(heading,"COMPARE PRODUCTS");
            AssertJUnit.assertEquals(sony,"SONY XPERIA");
            AssertJUnit.assertEquals(iphone,"IPHONE");

            // 6. Close the Popup Window
            driver.findElement(By.cssSelector("button[title='Close Window'] span span")).click();
            Thread.sleep(1000);




        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
