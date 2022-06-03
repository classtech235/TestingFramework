package tst.sample.selenium.testcases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tst.sample.utils.ExcelDataProvider;

import java.time.Duration;

public class BasicSeleniumValidations {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://yahoo.com");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test(dataProvider = "getData", dataProviderClass = ExcelDataProvider.class)
    public void loginTest(String uName, String pwd) {
        WebElement signInBtn = driver.findElement(By.id("ysignin"));
        signInBtn.click();
        WebElement userNameField = driver.findElement(By.id("login-username"));
        userNameField.clear();
        userNameField.sendKeys(uName);

        WebElement nextBtn = driver.findElement(By.id("login-signin"));
        nextBtn.click();
        WebElement pwdField = null;
        try {
            pwdField = driver.findElement(By.id("login-passwd"));
            pwdField.clear();
            pwdField.sendKeys(pwd);
            //if not provided, we get Stale Element Reference exception
            driver.findElement(By.id("login-signin")).click();

        } catch (Exception e) {
            Assert.assertNotNull(pwdField);
        }
        String username = driver.findElement(By.xpath("//a[@id='ysignout']/div[2]")).getText();
        Assert.assertEquals(username, "firsttestlogin_12");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
