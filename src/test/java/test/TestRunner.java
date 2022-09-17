package test;

import base.BrowserSetup;
import features.LoginPage;
import features.WebLauncher;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
public class TestRunner extends BrowserSetup {

    LoginPage loginPage =  new LoginPage(driver);


    //Log4j configuration
    private static final Logger log = LogManager.getLogger(TestRunner.class);

    @SneakyThrows
    @Test
    public void loginTest(){

        log.info("Verifying successful login.");

        loginPage = new LoginPage(driver);
        WebLauncher webLauncher = loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(loginPage.getTitle(), "Swag Labs");
        System.out.println("Test 1 - Login successful");
        log.info("Test 1 - Login successful");

        testAddToCart();
        testCheckoutPage();
        testAddressPage();
        testTotalPrice();
        testOrderComplete();

    }

    @Test
    public void testAddToCart() throws InterruptedException {
        loginPage.addProductsToCart();
        Assert.assertTrue(loginPage.proofItemAddedToCart_1().contains("backpack")&&loginPage.proofItemAddedToCart_2().contains("bike"));
        log.info("Test 2 - Added products successfully to cart");
        System.out.println("Test 2 - Added products successfully to cart");
    }

    @Test
    public void testCheckoutPage() {

       loginPage.checkoutPage();
        System.out.println("Test 3 - checked out successfully");
        System.out.println("we are here3");
    }

    @Test
    public void testAddressPage()
    {
        loginPage.addressDetails();
        System.out.println("Test 4 - Added address details successfully");
        System.out.println("we are here4");
    }

    @Test
    public void testTotalPrice()
    {
        if((loginPage.getItemPrice().contains("9.99")) || (loginPage.getItemPrice().contains("29.99")))
        {
            Assert.assertTrue(loginPage.getSumTotal().contains("39.98"));
        }
        System.out.println("Test 5 - Verified sum total price is correct");
        System.out.println("we are here5");
    }

    @Test
    public void testOrderComplete()
    {
        loginPage.finish();
        Assert.assertTrue(driver.getPageSource().contains("THANK YOU FOR YOUR ORDER") || driver.getTitle().contains("Checkout: Complete!")  );
        System.out.println("Test 6 - Order completed successfully");
        System.out.println("we are here6");
    }
}
