package stepDefinitions;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {
//stepdefinition file
	WebDriver driver;
	String amount = "";
    String orderReference ="";
	@Given("^User is in shopping site$")
	public void user_is_in_shopping_site() {
	    // Write code here that turns the phrase above into concrete actions
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://automationpractice.com");
	}

	@When("^user searches for \"([^\"]*)\"$")
	public void user_searches_for(String arg1) {
	    // Write code here that turns the phrase above into concrete actions
		WebElement searchBox = driver.findElement(By.id("search_query_top"));
		searchBox.clear();
		searchBox.sendKeys(arg1);
		WebElement searchButton = driver.findElement(By.name("submit_search"));
		searchButton.click();
	}

	@When("^select the product$")
	public void select_the_product() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement tshirtProduct = driver.findElement(By.xpath("//h5/a[contains(@title,\"T-shirts\")][contains(text(),\"T-shirts\")]"));
        tshirtProduct.click();
	}

	@When("^add the product to the cart$")
	public void add_the_product_to_the_cart() {
	    // Write code here that turns the phrase above into concrete actions
		 WebElement addToCart = driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"));
	        addToCart.click();
	}

	@When("^choose to checkout the products in the cart$")
	public void choose_to_checkout_the_products_in_the_cart() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement proceedToCheckoutPopup = driver.findElement(By.xpath("//span[contains(text(),'checkout')]"));
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutPopup));
        proceedToCheckoutPopup.click();
	}

	@When("^choose to checkout in summary page$")
	public void choose_to_checkout_in_summary_page() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement proceedToCheckoutSummary = driver.findElement(By.xpath("(//span[contains(text(),'checkout')])[2]"));
        proceedToCheckoutSummary.click();
	}

	@When("^Sign in with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void sign_in_with_username_and_password(String arg1, String arg2) {
	    // Write code here that turns the phrase above into concrete actions
		WebElement email = driver.findElement(By.id("email"));
        email.clear();
        email.sendKeys(arg1);
        WebElement password = driver.findElement(By.id("passwd"));
        password.clear();
        password.sendKeys(arg2);
        WebElement signIn = driver.findElement(By.id("SubmitLogin"));
        signIn.click();
	}

	@When("^choose to checkout in address page$")
	public void choose_to_checkout_in_address_page() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement proceedToCheckoutAddress = driver.findElement(By.xpath("(//span[contains(text(),'checkout')])[2]"));
        proceedToCheckoutAddress.click();
	}

	@When("^agree the terms to proceed to checkout$")
	public void agree_the_terms_to_proceed_to_checkout() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement agreeCheck = driver.findElement(By.id("cgv"));
        agreeCheck.click();
        WebElement proceedToCheckoutShipping = driver.findElement(By.xpath("(//span[contains(text(),'checkout')])[2]"));
        proceedToCheckoutShipping.click();
	}

	@When("^select the payment type$")
	public void select_the_payment_type() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement payByBank = driver.findElement(By.xpath("//*[contains(text(),'Pay by bank')]"));
        payByBank.click();
	}

	@When("^confirm the order in payment page$")
	public void confirm_the_order_in_payment_page() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement confirmOrder = driver.findElement(By.xpath("//*[contains(text(),'I confirm my order')]"));
        confirmOrder.click();
	}

	@Then("^Order details will be displayed$")
	public void order_details_will_be_displayed() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		WebElement orderID = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div"));
        String[] orderDetails = orderID.getText().split(" ");
        for (String string : orderDetails) {
        	Pattern pt =Pattern.compile("^[$].*");
			if(pt.matcher(string).lookingAt()) {
				
				amount = string.replaceAll("[\\n-]"	, "");
			}
			
			if(string.matches("[A-Z]{9}")) {
				orderReference = string;
			}
				
		}
	}

	@Then("^the Order is displayed in order history page$")
	public void the_Order_is_displayed_in_order_history_page()	 {
	    // Write code here that turns the phrase above into concrete actions
		WebElement backToOrders = driver.findElement(By.xpath("//*[contains(text(),'Back to orders')]"));
        backToOrders.click();
        WebElement orderRefHistory = driver.findElement(By.xpath("//*[@id=\"order-list\"]/tbody/tr[1]/td[1]"));
        WebElement totalPriceHistory = driver.findElement(By.xpath("//*[@id=\"order-list\"]/tbody/tr[1]/td[3]"));
        if(orderRefHistory.getText().equals(orderReference)) {
        	System.out.println("Order reference ID matched");
        }
        if(totalPriceHistory.getText().equals(amount)) {
        	System.out.println("Order amount matched");
        }
        driver.quit();
	}
	
	@Given("^User navigates to Sign in page$")
	public void user_navigates_to_Sign_in_page() {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement signInPage = driver.findElement(By.xpath("//*[contains(text(),'Sign in')]"));
	    signInPage.click();
	}

	@Given("^User navigates to My Personal Information page$")
	public void user_navigates_to_My_Personal_Information_page() {
	    // Write code here that turns the phrase above into concrete actions
		WebElement PersonalInfo = driver.findElement(By.xpath("//*[contains(text(),'My personal information')]"));
	    PersonalInfo.click();
	}

	@Given("^Update the First Name as \"([^\"]*)\" with current password as \"([^\"]*)\"$")
	public void update_the_First_Name_as_with_current_password_as(String arg1, String arg2)  {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement firstName = driver.findElement(By.id("firstname"));
	    firstName.clear();
	    firstName.sendKeys(arg1);
	    WebElement currentPassword = driver.findElement(By.id("old_passwd"));
	    currentPassword.clear();
	    currentPassword.sendKeys(arg2);
	}

	@When("^User clicks save$")
	public void user_clicks_save() {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement save = driver.findElement(By.name("submitIdentity"));
	    save.click();
	}

	@Then("^Personel Information is updated$")
	public void personel_Information_is_updated()  {
	    // Write code here that turns the phrase above into concrete actions
	    WebElement successMessage = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/p"));
	    String message = "Your personal information has been successfully updated.";
	    if(successMessage.getText().equalsIgnoreCase(message)) {
	    	System.out.println("Personel Information is updated");
	    }
	    driver.quit();
	}

	
}
