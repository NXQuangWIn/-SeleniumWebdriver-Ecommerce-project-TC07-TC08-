package BAITAP;

/*

Test Steps

Step 1. Go to http://live.techpanda.org/

Step 2. Verify Title of the page

Step 3. Click on -> MOBILE -> menu

Step 4. In the list of all mobile , select SORT BY -> dropdown as name

Step 5. Verify all products are sorted by name

*/


import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.checkerframework.common.value.qual.StaticallyExecutable;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class testcase01 {
    public static void testTC01() {
        int scc = 0;
        StringBuffer verificationErrors = new StringBuffer();
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //Step 1. Go to http://live.techpanda.org/
            driver.get("http://live.techpanda.org/");
            //Step 2. Verify Title of the page
            String demoSite = driver.findElement(By.cssSelector("h2")).getText();
            System.out.println(demoSite);
            try {
                AssertJUnit.assertEquals("THIS IS DEMO SITE FOR  ", demoSite);
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }

            Thread.sleep(2000);

            //Step 3. Click on -> MOBILE -> menu
            driver.findElement(By.linkText("MOBILE")).click();

            Thread.sleep(2000);

            //Step 4. In the list of all mobile , select SORT BY -> dropdown as name
            new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]"))).selectByVisibleText("Name");

            Thread.sleep(2000);

            //Step 5. Verify all products are sorted by name
            scc = (scc + 1);
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String png = "D:\\NGUYENXUANQUANG\\2023.FFF\\SeleniumWebdriver\\selenium-webdriver-java-master\\target.png";
            FileUtils.copyFile(srcFile, new File(png));


        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
