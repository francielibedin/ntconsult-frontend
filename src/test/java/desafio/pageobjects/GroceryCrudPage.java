package desafio.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;


public class GroceryCrudPage {
    private final WebDriver driver;

    @FindBy(xpath = "//*[@id=\"switch-version-select\"]")
    WebElement bootstrapVersion;

    @FindBy(xpath = "//div[@class=\"floatL t5\"]//a")
    WebElement buttonAddCustomer;

    @FindBy(xpath = "//*[@id=\"field-customerName\"]")
    WebElement nameCustomer;

    @FindBy(xpath = "//*[@id=\"field-contactLastName\"]")
    WebElement lastNameCustomer;

    @FindBy(xpath = "//*[@id=\"field-contactFirstName\"]")
    WebElement contactFirstName;

    @FindBy(xpath = "//*[@id=\"field-phone\"]")
    WebElement phone;

    @FindBy(xpath = "//*[@id=\"field-addressLine1\"]")
    WebElement addressLineOne;

    @FindBy(xpath = "//*[@id=\"field-addressLine2\"]")
    WebElement addressLineTwo;

    @FindBy(xpath = "//*[@id=\"field-city\"]")
    WebElement city;

    @FindBy(xpath = "//*[@id=\"field-state\"]")
    WebElement state;

    @FindBy(xpath = "//*[@id=\"field-postalCode\"]")
    WebElement postalCode;

    @FindBy(xpath = "//*[@id=\"field-country\"]")
    WebElement country;

    @FindBy(xpath = "//*[@id=\"field_salesRepEmployeeNumber_chosen\"]//a")
    WebElement salesRepEmployeeNumber;

    @FindBy(xpath = "//*[@id=\"field-creditLimit\"]")
    WebElement creditLimit;

    @FindBy(xpath = "//*[@id=\"field-deleted\"]")
    WebElement deleted;

    @FindBy(xpath = "//*[@id=\"form-button-save\"]")
    WebElement buttonSave;

    @FindBy(xpath = "//*[@id=\"save-and-go-back-button\"]")
    WebElement buttonSaveAndGoBack;

    @FindBy(xpath = "//*[@id=\"report-success\"]//p")
    WebElement messageSuccess;

    @FindBy(xpath = "//input[@name=\"customerName\"]")
    WebElement searchNameCustomer;

    @FindBy(xpath = "//thead//input[@type=\"checkbox\"]")
    WebElement checkbox;

    @FindBy(xpath = "//div[@class=\"floatL\"]//a[@title=\"Delete\"]")
    WebElement buttonDelete;

    @FindBy(xpath = "//*[@class=\"modal-body\"]//p[@class=\"alert-delete-multiple-one\"]")
    WebElement messsagePopDelete;

    @FindBy(xpath = "//*[@class=\"modal-body\"]//p[@class=\"alert-delete-multiple\"]")
    WebElement messsagePopDeleteMultiplesRows;

    @FindBy(xpath = "//button[@class=\"btn btn-danger delete-multiple-confirmation-button\" and contains(text(), \"Delete\")]")
    WebElement buttonMultipleConfirmDelete;

    @FindBy(xpath = "//span[@data-growl=\"message\"]")
    WebElement messageConfirmDelete;

    public GroceryCrudPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickButtonAddCustomer(){
        new Actions(this.driver)
                .click(this.buttonAddCustomer)
                .perform();
    }

    public void sendKeysNameCustomer(String nameCustomer) {
        this.nameCustomer.sendKeys(nameCustomer);
    }

    public void sendKeysLastNameCustomer(String lastNameCustomer){
        this.lastNameCustomer.sendKeys(lastNameCustomer);
    }

    public void sendKeysContactFirstName(String contactFirstName){
        this.contactFirstName.sendKeys(contactFirstName);
    }

    public void sendKeysPhone(String phone){
        this.phone.sendKeys(phone);
    }

    public void sendKeysAddressLineOne(String addressLineOne){
        this.addressLineOne.sendKeys(addressLineOne);
    }

    public void sendKeysAddressLineTwo(String addressLineTwo){
        this.addressLineTwo.sendKeys(addressLineTwo);
    }

    public void sendKeysCity(String city){
        this.city.sendKeys(city);
    }

    public void sendKeysState(String state){
        this.state.sendKeys(state);
    }

    public void sendKeysPostalCode(String postalCode){
        this.postalCode.sendKeys(postalCode);
    }

    public void sendKeysCountry(String country){
        this.country.sendKeys(country);
    }

    public void sendKeysSalesRepEmployeeNumber(String salesRepEmployeeNumber) {
        new Actions(this.driver).click(this.salesRepEmployeeNumber)
                .sendKeys(this.salesRepEmployeeNumber, salesRepEmployeeNumber)
                .click(this.salesRepEmployeeNumber)
                .perform();
    }

    public void sendKeysCreditLimit(String creditLimit){
        this.creditLimit.sendKeys(creditLimit);
    }

    public void sendKeysDeleted(String deleted){
        this.deleted.sendKeys(deleted);
    }

    public void clickButtonSave(){
        this.buttonSave.click();
    }

    public void clickButtonSaveAndGoBack(){
        this.buttonSaveAndGoBack.click();
    }

    public void existMessageSuccess(String messageSuccess){
        var messageSuccessCurrent = new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.visibilityOf(this.messageSuccess))
                .getText();
        assertEquals(messageSuccess, messageSuccessCurrent);
    }

    public void selectBootstrapVersion(String bootstrapVersion){
        this.bootstrapVersion.click();
        WebElement version = this.bootstrapVersion
                .findElement(By.xpath("//option[text()=\""+bootstrapVersion+"\"]"));
        version.click();
    }

    public void sendKeysSearchNameCustomer(String searchNameCustomer){
        new Actions(this.driver)
                .click(this.searchNameCustomer)
                .sendKeys(this.searchNameCustomer, searchNameCustomer)
                .sendKeys(Keys.ENTER)
                .perform();
        new WebDriverWait(driver, Duration.ofSeconds(10L)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver drive) {
                JavascriptExecutor js = (JavascriptExecutor) drive;
                String readyState = js.executeScript("return !!window.jQuery && window.jQuery.active == 0")
                        .toString();
                return readyState.equals("true");
            }
        });
    }

    public void clickCheckbox(){
        this.checkbox.click();
    }

    public void clickButtonDelete(){
        this.buttonDelete.click();
    }

    public void assertMesssagePopDelete(String messsagePopDelete){
        new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions
                        .or(ExpectedConditions.visibilityOf(this.messsagePopDelete),
                                ExpectedConditions.visibilityOf(this.messsagePopDeleteMultiplesRows)));
        String messagePopDeleteCurrent = null;
        try {
            messagePopDeleteCurrent = this.messsagePopDelete.getText();
        } catch (NoSuchElementException e) {
            messagePopDeleteCurrent = this.messsagePopDeleteMultiplesRows.getText();
        } finally {
            assertEquals(messsagePopDelete, messagePopDeleteCurrent);
        }
    }

    public void clickButtonMultipleConfirmDelete(){
        this.buttonMultipleConfirmDelete.click();
    }

    public void assertMessageConfirmDelete(String messageConfirmDelete){
        var messageConfirmDeleteCurrent = new WebDriverWait(driver, Duration.ofSeconds(10L))
                .until(ExpectedConditions.visibilityOf(this.messageConfirmDelete))
                .getText();
        assertEquals(messageConfirmDelete, messageConfirmDeleteCurrent);
    }
}
