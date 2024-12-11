import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class form2 {

    private static WebDriver driver;
    private static final String URL = "https://demo.wpeverest.com/user-registration/guest-registration-form/";

    @BeforeAll
    public static void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Order(1)
    @DisplayName("Open Registration Form")
    public void openRegistrationForm() {
        driver.get(URL);
    }

    @Test
    @Order(2)
    @DisplayName("Fill Personal Information")
    public void fillPersonalInformation() {
        List<WebElement> form = waitForElements(By.className("form-row"));
        validateFormSize(form, 13);
        fillField(form.get(0), "Ramisa Sharar");
        fillField(form.get(1), "ramisa.sharar.nidhi@g.bracu.ac.bd");
        fillField(form.get(2), "aboltabol#");
        fillField(form.get(3), "Nidhi");
    }

    @Test
    @Order(3)
    @DisplayName("Select Gender")
    public void selectGender() {
        selectRadioOption(By.name("radio_1665627729"), 0);
    }

    @Test
    @Order(4)
    @DisplayName("Fill Contact Details")
    public void fillContactDetails() {
        List<WebElement> form = driver.findElements(By.className("form-row"));
        fillField(form.get(6), "01774483905");
        fillField(form.get(7), "01999225347");
        fillField(form.get(8), "Bangladeshi");
    }

    @Test
    @Order(5)
    @DisplayName("Select Country")
    public void selectCountry() {
        selectDropdown(By.id("country_1665629257"), "Bangladesh");
    }

    @Test
    @Order(6)
    @DisplayName("Fill Additional Information")
    public void fillAdditionalInformation() {
        List<WebElement> form = driver.findElements(By.className("form-row"));
        fillField(form.get(11), "9");
        fillField(form.get(12), "Room number-302 and Bed Number-02");
    }

    @Test
    @Order(7)
    @DisplayName("Select Preferences")
    public void selectPreferences() {
        selectRadioOption(By.name("radio_1665627932_field"), 0);
        selectRadioOption(By.name("radio_1665627997_field"), 0);
        selectRadioOption(By.name("radio_1665628131_field"), 0);
    }

    @Test
    @Order(8)
    @DisplayName("Select Activities")
    public void selectActivities() {
        selectDropdownByIndex(By.id("select_1665628361_field"), 1);
    }

    @Test
    @Order(9)
    @DisplayName("Agree to Terms and Submit")
    public void agreeAndSubmit() {
        clickElement(By.cssSelector("input[type='checkbox']"));
        clickElement(By.cssSelector("button[type='submit']"));
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private List<WebElement> waitForElements(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    private void validateFormSize(List<WebElement> form, int expectedSize) {
        if (form.size() < expectedSize) {
            throw new IllegalStateException("Not enough form fields detected.");
        }
    }

    private void fillField(WebElement element, String value) {
        element.findElement(By.tagName("input")).sendKeys(value);
    }

    private void selectRadioOption(By locator, int index) {
        List<WebElement> options = driver.findElements(locator);
        if (options.size() > index) {
            options.get(index).click();
        }
    }

    private void selectDropdown(By locator, String visibleText) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByVisibleText(visibleText);
    }

    private void selectDropdownByIndex(By locator, int index) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByIndex(index);
    }

    private void clickElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}
