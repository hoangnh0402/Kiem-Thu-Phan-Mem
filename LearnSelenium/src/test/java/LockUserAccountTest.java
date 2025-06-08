import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LockUserAccountTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testLockUserAccountSuccess() {
        try {
            driver.get("http://localhost:3000/auth/login");

            WebElement emailInput = driver.findElement(By.cssSelector("input[placeholder='Email']"));
            emailInput.clear();
            emailInput.sendKeys("nguyenhuyhoangpt0402@gmail.com");
            Thread.sleep(2000);
            WebElement passwordInput = driver.findElement(By.cssSelector("input[placeholder='Password']"));
            passwordInput.clear();
            passwordInput.sendKeys("admin");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input.forms_buttons-action")).click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.urlContains("/admin"));

            driver.get("http://localhost:3000/admin/users");
            Thread.sleep(2000);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(@class, 'me-3 confirm-text')])[1]")));
            WebElement lockButton = driver.findElement(By.xpath("(//a[contains(@class, 'me-3 confirm-text')])[1]"));
            lockButton.click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".swal2-popup.swal2-modal.swal2-icon-warning")));
            WebElement confirmButton = driver.findElement(By.cssSelector("button.swal2-confirm.swal2-styled"));
            assertTrue(driver.findElement(By.id("swal2-title")).getText().contains("Bạn có chắc chắn xóa ?"), "Confirmation modal title not found");
            confirmButton.click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".swal2-popup.swal2-modal.swal2-icon-success")));
            WebElement successModalTitle = driver.findElement(By.id("swal2-title"));
            assertTrue(successModalTitle.getText().contains("Xóa thành công"), "Success message 'Xóa thành công' not found in modal");
            WebElement okButton = driver.findElement(By.cssSelector("button.swal2-confirm.swal2-styled"));
            okButton.click();
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Test failed due to an exception: " + e.getMessage());
        }
    }
}