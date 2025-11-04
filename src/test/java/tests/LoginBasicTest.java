package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginBasicTest {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        // Automatically handles correct driver version
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void login() {
        System.out.println("0. Start");

        System.out.println("1. Open target page");
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().setSize(new Dimension(1350, 637));

        System.out.println("2. Insert username and password");
        driver.findElement(By.cssSelector("*[data-test=\"username\"]")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("*[data-test=\"password\"]")).sendKeys("secret_sauce");

        System.out.println("3. Click submit to perform login");
        driver.findElement(By.cssSelector("*[data-test=\"login-button\"]")).click();

        System.out.println("4. Verify login has been successfully executed");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("5. End");
    }
}
