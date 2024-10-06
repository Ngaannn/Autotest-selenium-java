package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindXpath {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + ".\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }
    @Test
    public void TC_01() {
        driver.findElement(By.id("txtSearch")).sendKeys("");
        driver.findElement(By.xpath("//a[text()='Đăng Nhập']")).click();
        driver.findElement(By.xpath("//a[text()='Đăng Ký']")).click();
    }
    @Test
    public void TC_02(){
        driver.findElement(By.id("txtFirstname")).sendKeys("");
        driver.findElement(By.id("txtEmail")).sendKeys("");
        driver.findElement(By.id("txtCEmail")).sendKeys("");
        driver.findElement(By.id("txtPassword")).sendKeys("");
        driver.findElement(By.id("txtCPassword")).sendKeys("");
        driver.findElement(By.id("txtPhone")).sendKeys("");
        driver.findElement(By.id("chkRight")).click();
        driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

        Assert.assertEquals(driver.findElement(By.id("txtFirstname-error")).getText(),"Vui lòng nhập họ tên");
        Assert.assertEquals(driver.findElement(By.id("txtEmail-error")).getText(),"Vui lòng nhập email");
        Assert.assertEquals(driver.findElement(By.id("txtCEmail-error")).getText(),"Vui lòng nhập lại địa chỉ email");
        Assert.assertEquals(driver.findElement(By.id("txtPassword-error")).getText(),"Vui lòng nhập mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtCPassword-error")).getText(),"Vui lòng nhập lại mật khẩu");
        Assert.assertEquals(driver.findElement(By.id("txtPhone-error")).getText(),"Vui lòng nhập số điện thoại.");
    }
    @Test
    public void TC_03(){
        driver.findElement(By.id("btndknfooter")).click();
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
