package features;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class LoginPage {

    WebDriver driver;

    private static final Logger log = LogManager.getLogger(LoginPage.class);
    @FindBy(xpath="//input[@id='user-name']")
    WebElement username;

    @FindBy(id="password")
    WebElement password;
    @FindBy(xpath="//input[@id='login-button']")
    WebElement loginButton;

    @FindBy(xpath ="//button[@id='add-to-cart-sauce-labs-backpack']")
    WebElement backPackButton; //$29.99


    @FindBy(xpath="//button[@id='add-to-cart-sauce-labs-bike-light']")
    WebElement bikeButton; //$9.99

    @FindBy(className="shopping_cart_link")
    WebElement addToCartButton;

    @FindBy(xpath="//button[@id='checkout']")
    WebElement checkoutButton;

    @FindBy(xpath ="//button[@id='remove-sauce-labs-backpack']")
    WebElement backPackProof;

    @FindBy(xpath ="//button[@id='remove-sauce-labs-bike-light']")
    WebElement bikeProof;

    @FindBy(xpath="//input[@id='first-name']")
    WebElement firstNameAddressTF;

    @FindBy(xpath="//input[@id='last-name']")
    WebElement lastnameAddressTF;

    @FindBy(xpath="//input[@id='postal-code']")
    WebElement zipcodeAddressTF;

    @FindBy(xpath="//input[@id='continue']")
    WebElement addressContinueButton;

    @FindBy(className="inventory_item_price")
    WebElement backpackPrice;

    @FindBy(className="inventory_item_price")
    WebElement bikePrice;

    @FindBy(className="summary_subtotal_label")
    WebElement sumTotalPrice;

    @FindBy(xpath="//button[@id='finish']")
    WebElement finishButton;

    /**
     * XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
     */

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //functions for the login page elements
    public void setUsername(String uname) {
        username.sendKeys(uname);
    }

    public void setPassword(String pwd) {
        password.sendKeys(pwd);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void clickLoginButton() {
        loginButton.click();
    }


    /**
     * Function for launching browser  and loging in
     * @param username
     * @param password
     * @return
     * @throws InterruptedException
     */
    public WebLauncher login(String username, String password) throws InterruptedException {

        rest();
        setUsername(username);
        rest();
        setPassword(password);
        rest();
        clickLoginButton();

        return new WebLauncher(driver);
    }

    public void addProductsToCart() throws InterruptedException {
        rest();
        clickAnElement(backPackButton);
        rest();
        clickAnElement(bikeButton);
        rest();
        clickAnElement(addToCartButton);

    }

    public String proofItemAddedToCart_1()
    {

        return backPackProof.getAttribute("name");
    }
    public String proofItemAddedToCart_2()
    {

        return bikeProof.getAttribute("name");
    }

    public void checkoutPage()
    {
        rest();
        clickAnElement(checkoutButton);
        rest();
    }
    public void addressDetails()
    {
        rest();
        enterTextField(firstNameAddressTF,generateRandomString());
        rest();
        enterTextField(lastnameAddressTF,generateRandomString());
        rest();
        enterTextField(zipcodeAddressTF, "1234");
        rest();
        clickAnElement(addressContinueButton);
    }

    public String getItemPrice()
    {
        rest();
        return backpackPrice.getText();
    }

    public String getSumTotal()
    {
        rest();
        return sumTotalPrice.getText();
    }


    public void finish()
    {
       rest();
       clickAnElement(finishButton);
       rest();

    }



    public void selectDropDown(WebElement webelement){
        Select select = new Select(webelement);
        select.selectByIndex(0);
    }

    /**
     * General function for randomising a text up to 4 characters in length- can be used for generating a name
     * @return
     */
    public String generateRandomString() {
        byte[] array = new byte[4]; // length is bounded by 7
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        return generatedString;
    }

    /**
     * General function for clicking an element e.g. button or hyperlink
     * @param button
     */
    public void clickAnElement(WebElement button){
        WebDriverWait woah = new WebDriverWait(driver, 10);
        woah.until(ExpectedConditions.elementToBeClickable(button));
        button.click();

    }

    /**
     * General function for entering text in a textfield
     * @param field
     * @param text
     */
    public void enterTextField(WebElement field, String text)
    {
        field.clear();
        field.sendKeys(text);
    }

    public void rest()
    {driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);    }


}
