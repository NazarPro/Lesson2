import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SimpleTest {

        @Test
        public void TestGmailSendingLetter(){
        final String FIRST_EMAIL_LOGIN = "labepamone";
        final String FIRST_EMAIL_PWD = "lab123456epam";
        final String SECOND_EMAIL_LOGIN = "labepamtwo";
        final String SECOND_EMAIL_PWD = "lab123456epam";
        final String MESSAGE_TITLE = "Test_Message";
        final String MESSAGE_TEXT = "Test_Message_Text";


        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys(FIRST_EMAIL_LOGIN, Keys.ENTER);

        driver.findElement(By.xpath("//div[@id='password']//input[@name='password']")).clear();
        driver.findElement(By.xpath("//div[@id='password']//input[@name='password']")).sendKeys(FIRST_EMAIL_PWD, Keys.ENTER);

        driver.findElement(By.xpath("//div[@class='aic']//descendant::*[@role='button']")).click();

        driver.findElement(By.name("to")).clear();
        driver.findElement(By.name("to")).sendKeys(SECOND_EMAIL_LOGIN + "@gmail.com");

        driver.findElement(By.cssSelector("input.aoT[name='subjectbox']")).clear();
        driver.findElement(By.cssSelector("input.aoT[name='subjectbox']")).sendKeys(MESSAGE_TITLE);

        driver.findElement(By.xpath("//div[@aria-label='Текст повідомлення']")).clear();
        driver.findElement(By.xpath("//div[@aria-label='Текст повідомлення']")).sendKeys(MESSAGE_TEXT);

        driver.findElement(By.xpath("//div[@class='dC']/div[contains(@aria-label, 'Надіслати')]")).click();

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//div[@role='alert' and @aria-atomic='true']"))));

        driver.findElement(By.xpath("//a[contains(@aria-label, 'labepamone')]/img")).click();
        driver.findElement(By.xpath("//a[text()='Вийти']")).click();

        driver.navigate().to("https://accounts.google.com/signin/v2/identifier?continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&service=mail&sacu=1&rip=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

        driver.findElement(By.tagName("input")).clear();
        driver.findElement(By.tagName("input")).sendKeys(SECOND_EMAIL_LOGIN, Keys.ENTER);

        driver.findElement(By.xpath("//div[@id='password']//input[@name='password']")).clear();
        driver.findElement(By.xpath("//div[@id='password']//input[@name='password']")).sendKeys(SECOND_EMAIL_PWD, Keys.ENTER);

        new WebDriverWait(driver, 30).until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//tr[@class='zA zE']"))));
        driver.findElements(By.xpath("//tr[@class='zA zE']")).get(0).click();

        Assert.assertEquals(FIRST_EMAIL_LOGIN + "@gmail.com", driver.findElement(By.xpath("//span[@class='go']")).getText().substring(1,driver.findElement(By.xpath("//span[@class='go']")).getText().length()-1));
        Assert.assertEquals(MESSAGE_TITLE, driver.findElement(By.xpath("//h2[@class='hP']")).getText());
        Assert.assertEquals(MESSAGE_TEXT, driver.findElement(By.xpath("//div[@class='a3s aiL ']/div[@dir='ltr']")).getText());

        driver.quit();
    }

}
