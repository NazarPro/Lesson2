import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SimpleTest {

        @Test
        public void TestGoogleSearchResult() {
        final String SEARCH_WORD = "Apple";
        final int IMAGES_LOADED_AMOUNT = 30;

        System.setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).clear();
        driver.findElement(By.name("q")).sendKeys(SEARCH_WORD, Keys.ENTER);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElement(By.xpath("//div[@id='rso']/div"))));
        Assert.assertTrue(driver.getTitle().contains(SEARCH_WORD));
        driver.findElement(By.xpath("//div[@class='hdtb-mitem']//a[text()='Зображення']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("tbm=isch"));
        Assert.assertTrue(driver.findElement(By.xpath("//span[@aria-label='Пошук за зображенням']")).isDisplayed());
        Assert.assertTrue(driver.findElements(By.xpath("//div[contains(@style,'height: 222px')]//img")).size() > IMAGES_LOADED_AMOUNT);

        driver.quit();
    }

}
