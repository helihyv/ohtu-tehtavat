package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    //WebDriver driver = new ChromeDriver();
    WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        loginSucceeded();
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        loginFailedWithErrorMessage();
    }    
    
    @When("username {string} and password {string} are given")
    public void usernameAndPasswordAreGiven(String username, String password) throws Throwable {
        logInWith(username, password);
    }   

    @When("nonexisting username {string} and password {string} are given")
    public void nonexistingUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }

    @Then("login fails and an error message is given")
    public void loginFailsAndAnErrorMessageIsGiven() {
        loginFailedWithErrorMessage();
    }

    @Given("command new user is selected")
    public void commandNewUserIsSelected() {

        selectCommandNewUser();
    }

    @When("a valid username {string} and password {string} and matching password confirmation are entered")
    public void aValidUsernameAndPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        registerNewUser(username, password, password);
    }

    @Then("a new user is created")
    public void aNewUserIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }

    @When("a too short username {string} and valid password {string} and matching password confirmation are entered")
    public void aTooShortUsernameAndValidPasswordAndMatchingPasswordConfirmationAreEntered(String username, String password) {
        registerNewUser(username, password, password);
    }

    @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String errorMessage) {
        pageHasContent("Create username and give password");
        pageHasContent(errorMessage);
    }

    @When("a valid username {string} and too short password {string} and matching password confirmation are entered")
    public void aValidUsernameAndTooShortPasswordAndMatchingPasswordConfirmationAreEntered(
            String username, 
            String password
    ) {
        
        registerNewUser(username, password, password);
    }

    @When("a valid username {string} and a valid password {string} and a non-matching password confirmation {string} are entered")
    public void aValidUsernameAndAValidPasswordAndANonMatchingPasswordConfirmationAreEntered(
            String username, 
            String password, 
            String passwordConfirmation
    ) {
        registerNewUser(username, password, passwordConfirmation);
    }
    
        @Given("user with username {string} with password {string} is successfully created")
    public void userWithUsernameWithPasswordIsSuccessfullyCreated(String username, String password) {
        
        selectCommandNewUser();
        registerNewUser(username, password, password);
    }

    @When("succesfully created username {string} and correct password {string} are entered")
    public void succesfullyCreatedUsernameAndCorrectPasswordAreEntered(String username, String password) {

        logInWith(username, password);
    }

    @Given("user with username {string} and password {string} is tried to be created")
    public void userWithUsernameAndPasswordIsTriedToBeCreated(String username, String password) {
        
        selectCommandNewUser();
        registerNewUser(username, password, password);
    }

    @When("credentials username {string} and password {string} that were used in the failed accout creation attempt are entered")
    public void credentialsUsernameAndPasswordThatWereUsedInTheFailedAccoutCreationAttemptAreEntered(String username, String password) {
        logInWith(username, password);
    }

    @Then("user is succesfully logged in")
    public void userIsSuccesfullyLoggedIn() {
        loginSucceeded();
    }

    @Then("login with non-existing user fails  and the error message is shown")
    public void nonExistingUserIsNotLoggedInAndTheErrorMessageIsShown() {
        loginFailedWithErrorMessage();
    }


    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    }
    
    private void loginFailedWithErrorMessage() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");        
    }
    
    private void registerNewUser(String username, String password, String passwordConfirmation) {
               
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();
    }
    
    private void loginSucceeded() {
         pageHasContent("Ohtu Application main page");
    }
    
    private void selectCommandNewUser() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
    
}
