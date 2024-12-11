import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class form1 {

    private WebDriver driver;
    private final String url = "https://www.digitalunite.com/practice-webform-learners";

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    private void navigateToForm() {
        driver.get(url);
    }

    private void fillFieldById(String fieldId, String value) {
        driver.findElement(By.id(fieldId)).sendKeys(value);
    }

    @Test
    @Order(1)
    @DisplayName("Navigate to Web Form")
    public void navigateToWebForm() {
        navigateToForm();
        assertTrue(driver.getTitle().contains("Practice webform"), "Page title does not contain expected text!");
    }

    @Test
    @Order(2)
    @DisplayName("Fill Name Field")
    public void fillNameField() {
        navigateToForm();
        fillFieldById("edit-name", "Ramisa Sharar Nidhi");
    }

    @Test
    @Order(3)
    @DisplayName("Fill Number Field")
    public void fillNumberField() {
        navigateToForm();
        fillFieldById("edit-number", "01947586934");
    }

    @Test
    @Order(4)
    @DisplayName("Fill Date Field")
    public void fillDateField() {
        navigateToForm();
        fillFieldById("edit-date", "09/12/2025");
    }

    @Test
    @Order(5)
    @DisplayName("Fill Email Field")
    public void fillEmailField() {
        navigateToForm();
        fillFieldById("edit-email", "ramisa.sharar.nidhi@g.bracu.ac.bd");
    }

    @Test
    @Order(6)
    @DisplayName("Fill About Yourself Field")
    public void fillAboutYourselfField() {
        navigateToForm();
        fillFieldById("edit-tell-us-a-bit-about-yourself-", "My name is Ramisa Sharar Nidhi, and I am currently a student at BRAC University, pursuing my passion for learning and innovation. Alongside my studies, I work as an AI Developer at BD Calling, where I contribute to creating intelligent solutions and advancing the field of technology. ");
    }

    @Test
    @Order(7)
    @DisplayName("Upload Document")
    public void uploadDocument() {
        navigateToForm();
        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Md.Shariar Emon Shaikat.pdf";
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File not found at path: " + filePath);
        }
        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(file.getAbsolutePath());
    }

    @Test
    @Order(8)
    @DisplayName("Click Checkbox")
    public void clickCheckbox() {
        navigateToForm();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='checkbox']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
    }

    @Test
    @Order(9)
    @DisplayName("Submit Form")
    public void submitForm() {
        navigateToForm();
        driver.findElement(By.id("edit-submit")).click();
    }

        @Test
    @Order(10)
    @DisplayName("Verify Success Message")
    public void verifySuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("container")));
        assertEquals("Thank you for your submission!", successMessage.getText(), "The success message does not match!");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}