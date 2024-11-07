import Utilities.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class FlipkartLoginTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup ChromeDriver (make sure to set the path to your driver executable)
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() throws IOException {
        String filePath = "src/test/resources/test.xlsx";
        String sheetName = "Sheet1";
        return ExcelUtils.getExcelData(filePath, sheetName);
    }

    @Test(dataProvider = "loginData")
    public void testFlipkartLogin(String username, String password) {
        // Navigate to Flipkart
        driver.get("https://www.flipkart.com/account/login?ret=/");

        // Close the initial login popup if it appears
        try {
            WebElement closeButton = driver.findElement(By.cssSelector("button._2KpZ6l._2doB4z"));
            closeButton.click();
        } catch (Exception e) {
            System.out.println("Login popup did not appear");
        }

        // Open the login form
        WebElement loginButton = driver.findElement(By.linkText("Login"));
        loginButton.click();

        // Enter the username
        WebElement usernameField = driver.findElement(By.cssSelector("input._2IX_2-"));
        usernameField.clear();
        usernameField.sendKeys(username);

        // Enter the password
        WebElement passwordField = driver.findElement(By.cssSelector("input._3mctLh"));
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click the login button
        WebElement submitButton = driver.findElement(By.cssSelector("button._2KpZ6l._2HKlqd._3AWRsL"));
        submitButton.click();

        // Check if login was successful
        // This is a sample check; you may need to update based on actual Flipkart behavior.
        try {
            WebElement accountMenu = driver.findElement(By.cssSelector("div._28p97w"));
            Assert.assertTrue(accountMenu.isDisplayed(), "Login was successful");
            System.out.println("Login succeeded for username: " + username);
        } catch (Exception e) {
            WebElement errorMessage = driver.findElement(By.cssSelector("span._2YULOR"));
            Assert.assertTrue(errorMessage.isDisplayed(), "Login failed with invalid credentials");
            System.out.println("Login failed for username: " + username);
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
