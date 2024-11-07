package test;

import base.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FlipkartHomePage;
import pages.FlipkartProductPage;

public class FlipkartTest extends TestBase {

    @Test
    public void searchAndAddProductToCart() {
        driver.get("https://www.flipkart.com");

        // Close login pop-up if necessary
        // driver.findElement(By.xpath("//button[contains(text(),'✕')]")).click();

        FlipkartHomePage homePage = new FlipkartHomePage(driver);
        homePage.enterSearchText("iphone 15 pro max");

        FlipkartProductPage productPage = new FlipkartProductPage(driver);
        productPage.selectProduct();

        // Assert that the product URL is correct (replace with actual expected URL)
        String expectedUrl = "https://www.flipkart.com/some-product-url";
        Assert.assertEquals(driver.getCurrentUrl(), expectedUrl, "Product page URL did not match.");

        // Assert that there are products displayed
        int productCount = productPage.getNumberOfProducts();
        Assert.assertTrue(productCount > 0, "No products found.");

        // Add product to cart and verify action
        productPage.addToCart();
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"), "Failed to add product to cart.");
    }
}
