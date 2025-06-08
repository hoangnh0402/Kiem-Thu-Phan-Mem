import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingRoomTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBookingRoomSuccess() {
        try {
            driver.get("http://localhost:3000/auth/login");

            driver.findElement(By.cssSelector("input[placeholder='Email']")).clear();
            driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("hoangnh4204@gmail.com");
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input[placeholder='Password']")).clear();
            driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys("DieuLinh86@");

            Thread.sleep(3000);
            driver.findElement(By.cssSelector("input.forms_buttons-action")).click();

            Thread.sleep(2000);

            driver.get("http://localhost:3000/booking");

            LocalDate today = LocalDate.now();
            LocalDate checkInDate = today.plusDays(3);
            LocalDate checkOutDate = checkInDate.plusDays(2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String checkInDateStr = checkInDate.format(formatter);
            String checkOutDateStr = checkOutDate.format(formatter);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//div[1]//div[1]//i[@class='fa-regular fa-calendar']")));
            driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//div[1]//div[1]//i[@class='fa-regular fa-calendar']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-datepicker"))); // Thay bằng selector của widget lịch

            String checkInDay = String.valueOf(checkInDate.getDayOfMonth());
            String checkInMonthYear = checkInDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
            String checkInMonthYearSelector = String.format("//div[contains(@class, 'react-datepicker')]//div[contains(text(), '%s')]", checkInMonthYear);
            String checkInDaySelector = String.format("//div[contains(@class, 'react-datepicker__day') and text()='%s']", checkInDay);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkInMonthYearSelector)));
            driver.findElement(By.xpath(checkInDaySelector)).click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[2]//div[1]//div[1]//div[1]//div[1]//i[@class='fa-regular fa-calendar']")));
            driver.findElement(By.xpath("//div[2]//div[1]//div[1]//div[1]//div[1]//i[@class='fa-regular fa-calendar']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".react-datepicker"))); // Thay bằng selector của widget lịch

            String checkOutDay = String.valueOf(checkOutDate.getDayOfMonth());
            String checkOutMonthYear = checkOutDate.format(DateTimeFormatter.ofPattern("MMMM yyyy"));
            String checkOutMonthYearSelector = String.format("//div[contains(@class, 'react-datepicker')]//div[contains(text(), '%s')]", checkOutMonthYear);
            String checkOutDaySelector = String.format("//div[contains(@class, 'react-datepicker__day') and text()='%s']", checkOutDay);

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(checkOutMonthYearSelector)));
            driver.findElement(By.xpath(checkOutDaySelector)).click();

            WebElement checkInInput = driver.findElement(By.xpath("//div[@class='row']//div[1]//div[1]//div[1]//div[1]//div[1]//input[1]"));
            WebElement checkOutInput = driver.findElement(By.xpath("//div[2]//div[1]//div[1]//div[1]//div[1]//input[1]"));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", checkInInput, checkInDateStr);
            js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));", checkOutInput, checkOutDateStr);

            Thread.sleep(3000);
            Select guestSelect = new Select(driver.findElement(By.id("guest")));
            guestSelect.selectByValue("2"); // Select 2 persons

            Thread.sleep(3000);
            Select roomTypeSelect = new Select(driver.findElement(By.xpath("(//select[@id='guest'])[2]")));
            roomTypeSelect.selectByValue("Deluxe"); // Select Deluxe room

            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[normalize-space()='Check available']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body//div[@id='root']//div[@class='row']//div//div//div[1]//div[1]//div[2]//button[1]")));

            Thread.sleep(3000);
            driver.findElement(By.xpath("//body//div[@id='root']//div[@class='row']//div//div//div[1]//div[1]//div[2]//button[1]")).click();

            Thread.sleep(3000);
            driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='row']//div[1]//div[2]//div[1]//button[1]")));

            Thread.sleep(3000);
            driver.findElement(By.xpath("//div[@class='row']//div[1]//div[2]//div[1]//button[1]")).click();

            driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();

            wait.until(ExpectedConditions.urlToBe("http://localhost:3000/booking-cart"));
            assertEquals("http://localhost:3000/booking-cart", driver.getCurrentUrl(), "Redirect to booking cart failed");

        } catch (Exception e) {
            e.printStackTrace();
            throw new AssertionError("Test failed due to an exception: " + e.getMessage());
        }
    }
}