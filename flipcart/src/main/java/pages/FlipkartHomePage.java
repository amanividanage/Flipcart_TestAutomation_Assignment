package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlipkartHomePage {
    private WebDriver driver;
    private By searchBox = By.name("q");

    public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterSearchText(String text) {
        WebElement searchField = driver.findElement(searchBox);
        searchField.sendKeys(text);
        searchField.submit();
    }
}
