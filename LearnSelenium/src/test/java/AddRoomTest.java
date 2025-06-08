import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddRoomTest {
    private WebDriver driver;
    private WebDriverWait wait;

    private enum Browser {
        EDGE
    }

    @BeforeEach
    public void setUp() {
    }

    private void initializeDriver(Browser browser) {
        switch (browser) {
            case EDGE:
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void testAddServiceSuccess(Browser browser) {
        try {
            initializeDriver(browser);
            driver.get("http://localhost:3000/auth/login");

            driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
            driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("nguyenhuyhoangpt0402@gmail.com");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
            driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin");
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("input.forms_buttons-action")).click();

            wait.until(ExpectedConditions.urlContains("/admin"));
            Thread.sleep(3000);

            driver.get("http://localhost:3000/admin/rooms");
            Thread.sleep(3000);

            driver.findElement(By.cssSelector("a.btn.btn-added")).click();
            Thread.sleep(4000);

            driver.findElement(By.xpath("//body/div[@id='root']/div[@class='App']/div[@class='main-wrapper']/div[@class='page-wrapper']/div[@class='content']/div[@class='card']/div[@class='card-body']/div[@class='row']/div[1]/div[1]/input[1]")).sendKeys("VIP Room");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[@class='card']//div[2]//div[1]//input[1]")).sendKeys("1000000");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("1");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[@class='page-wrapper']//div[4]//div[1]//input[1]")).sendKeys("12");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[5]//div[1]//input[1]")).sendKeys("1 giường đơn");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[6]//div[1]//input[1]")).sendKeys("Standard Single là loại phòng tiêu chuẩn, đơn giản nhất có diện tích nhỏ 12m2. Phòng dành cho 1 người, được trang bị 1 giường đơn.");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[7]//div[1]//input[1]")).sendKeys("Wifi, Tivi, Phòng tắm,...");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[8]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            WebElement comboBox = driver.findElement(By.xpath("//select"));
            Select select = new Select(comboBox);
            select.selectByVisibleText("Standard Single");
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("input[type='file']")).sendKeys("D:\\Img\\phong.jpg");
            Thread.sleep(3000);

            driver.findElement(By.cssSelector("button.btn.btn-submit.me-2")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
            String modalText = driver.findElement(By.cssSelector("#myModal .modal-body h4")).getText();
            assertTrue(modalText.contains("Tạo thành công"), "Success message 'Tạo thành công' not found in modal");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Test failed due to an exception: " + e.getMessage());
        }
    }

    @ParameterizedTest
    @EnumSource(Browser.class)
    public void testAddServiceFailure(Browser browser) {
        try {
            initializeDriver(browser);
            driver.get("http://localhost:3000/auth/login");

            driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
            driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("nguyenhuyhoangpt0402@gmail.com");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
            driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("admin");
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("input.forms_buttons-action")).click();

            wait.until(ExpectedConditions.urlContains("/admin"));
            Thread.sleep(3000);

            driver.get("http://localhost:3000/admin/rooms");
            Thread.sleep(3000);

            driver.findElement(By.cssSelector("a.btn.btn-added")).click();
            Thread.sleep(4000);

            driver.findElement(By.xpath("//body/div[@id='root']/div[@class='App']/div[@class='main-wrapper']/div[@class='page-wrapper']/div[@class='content']/div[@class='card']/div[@class='card-body']/div[@class='row']/div[1]/div[1]/input[1]")).sendKeys("");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[@class='card']//div[2]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[3]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[@class='page-wrapper']//div[4]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[5]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[6]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[7]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            driver.findElement(By.xpath("//div[8]//div[1]//input[1]")).sendKeys("");
            Thread.sleep(2000);

            WebElement comboBox = driver.findElement(By.xpath("//select"));
            Select select = new Select(comboBox);
            select.selectByVisibleText("Standard Single");
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("input[type='file']")).sendKeys("D:\\Img\\phong.jpg");
            Thread.sleep(3000);

            driver.findElement(By.cssSelector("button.btn.btn-submit.me-2")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("myModal")));
            String modalText = driver.findElement(By.cssSelector("#myModal .modal-body h4")).getText();
            assertTrue(modalText.contains("Tạo thất bại"), "Success message 'Tạo thất bại' not found in modal");
        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Test failed due to an exception: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}