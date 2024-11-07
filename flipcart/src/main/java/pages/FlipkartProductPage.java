package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class FlipkartProductPage {
    private WebDriver driver;
    private By productLink = By.xpath("//div[normalize-space()='Apple iPhone 15 Pro Max (Blue Titanium, 256 GB)']");
    private By addToCartButton = By.xpath("//button[normalize-space()='Add to cart']");
    private By productClass = By.className("_21Ahn-");

    public FlipkartProductPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectProduct() {
        driver.findElement(productLink).click();

        String mainPage = driver.getWindowHandle();
        Set<String> allPages = driver.getWindowHandles();
        for (String page : allPages) {
            if (!page.equals(mainPage)) {
                driver.switchTo().window(page);
                break;
            }
        }
    }

    public int getNumberOfProducts() {
        List<WebElement> products = driver.findElements(productClass);
        return products.size();
    }

    public void addToCart() {
        driver.findElement(addToCartButton).click();
    }
}
