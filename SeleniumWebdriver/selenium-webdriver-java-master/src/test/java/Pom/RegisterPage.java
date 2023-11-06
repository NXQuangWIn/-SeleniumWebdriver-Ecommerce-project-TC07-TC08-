package Pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {
    WebDriver driver;

    By myAccountLink = By.linkText("MY ACCOUNT");

    By createAccountLink = By.linkText("CREATE AN ACCOUNT");
    By fistNameInputLocator = By.id("firstname");
    By lastNameInputLocator = By.id("lastname");
    By emailInputLocator = By.id("email_address");
    By passwordInputLocator = By.id("password");
    By confirmPasswordInputLocator = By.id("confirmation");
    By registerButton = By.xpath("//button[@title='Register']");


    //login
    By loginButton = By.xpath("//span[contains(text(),'Login')]");
    By UpdateButton = By.xpath("//button[@title='Update']//span//span[contains(text(),'Update')]");
    By myWishlistLink = By.linkText("MY WISHLIST");
    By myOderLink = By.linkText("MY ORDERS");
    By emailInputLocator2 = By.id("email");
    By passwordInputLocator2 = By.id("pass");

    By addToCartButton = By.xpath("//span[contains(text(),'Add to Cart')]");
    By ViewOrderLink = By.xpath("//a[normalize-space()='View Order']");
    By PrintOrderLink = By.xpath("//a[@class='link-print']");
    By ReOderLink = By.xpath("//tr[@class='first odd']//a[@class='link-reorder'][normalize-space()='Reorder']");




    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickMyAccountLink() {
        driver.findElement(myAccountLink).click();
    }



    public void clickCreateAccountLink() {
        driver.findElement(createAccountLink).click();
    }

    public void enterFirstName(String firstName) {
        WebElement firstNameElement = driver.findElement(fistNameInputLocator);
        firstNameElement.clear();
        firstNameElement.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        WebElement lastNameElement = driver.findElement(lastNameInputLocator);
        lastNameElement.clear();
        lastNameElement.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        WebElement emailElement = driver.findElement(emailInputLocator);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    //login
    public void verifyGrandTotalIsChanged() {
        // Lấy giá trị Grand Total trước khi cập nhật
        String initialGrandTotal = driver.findElement(By.xpath("//strong[normalize-space()='Grand Total']")).getText();
        // Thay đổi số lượng sản phẩm
        changeQuantity(10); // Thay thế 10 bằng số lượng mới cần kiểm tra

        // Nhấn nút "Update Total"
        clickUpdateButton();

        // Lấy giá trị Grand Total sau khi cập nhật
        String updatedGrandTotal = driver.findElement(By.xpath("//strong[normalize-space()='Grand Total']")).getText();

        // So sánh giá trị Grand Total trước và sau khi cập nhật
        if (!initialGrandTotal.equals(updatedGrandTotal)) {
            System.out.println("PASS: Grand Total is changed");
        } else {
            System.out.println("FAIL: Grand Total is not changed");
        }
    }

    public void changeQuantity(int quantity) {
        WebElement quantityInput = driver.findElement(By.xpath("//input[@title='Qty']")); // Thay thế bằng định danh thích hợp
        quantityInput.clear(); // Xóa giá trị hiện tại trong trường số lượng
        quantityInput.sendKeys(String.valueOf(quantity)); // Nhập số lượng mới
    }
    public void clickReOderLink() {
        driver.findElement(ReOderLink).click();
    }
    public void clickPrintOrderLink() {
        driver.findElement(PrintOrderLink).click();
    }
    public void clickViewOrderLink() {
        driver.findElement(ViewOrderLink).click();
    }

    public void clickMyWishlistLink() {
        driver.findElement(myWishlistLink).click();
    }

    public void clickMyOrderLink() {
        driver.findElement(myOderLink).click();
    }

    public void enterEmail2(String email) {
        WebElement emailElement = driver.findElement(emailInputLocator2);
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    public void enterPassword2(String password) {
        WebElement passwordElement = driver.findElement(passwordInputLocator2);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void clickAddToCartButton() {
        driver.findElement(addToCartButton).click();
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
    public void clickUpdateButton() {
        driver.findElement(UpdateButton).click();
    }

    //

    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordInputLocator);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordElement = driver.findElement(confirmPasswordInputLocator);
        confirmPasswordElement.clear();
        confirmPasswordElement.sendKeys(confirmPassword);
    }

    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }







}
