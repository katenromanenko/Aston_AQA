import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MTSTests {
    private static ChromeDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
        Duration duration = Duration.ofSeconds(10);
        long delayInSeconds = duration.getSeconds();
        wait = new WebDriverWait(driver, delayInSeconds);
        driver.get("https://www.mts.by/");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void testServices() {
        String[] services = {"Услуги связи", "Домашний интернет", "Рассрочка", "Задолженность"};
        for (String pay : services) {
            WebElement serviceInput = driver.findElement(By.id(pay));
            Assert.assertNotNull("Надпись в незаполненном поле " + pay + " не найдена", serviceInput.getAttribute("placeholder"));
        }
    }

    @Test
    public void testPhoneDisplay() {
        WebElement phoneDisplay = driver.findElement(By.id("connection-phone"));
        Assert.assertEquals("Некорректное отображение номера телефона", "Номер телефона: 297777777", phoneDisplay.getText());
    }

    @Test
    public void testSumDisplay() {
        WebElement sumDisplay = driver.findElement(By.id("connection-sum"));
        Assert.assertEquals("Некорректное отображение суммы", "Сумма: 1000 руб.", sumDisplay.getText());
    }

    @Test
    public void testCardDetails() {
        WebElement cardDetails = driver.findElement(By.xpath("//div[@class='app-wrapper__content-container app-wrapper__content-container_fulls']"));
        Assert.assertNotNull("Надпись в незаполненном поле для ввода реквизитов карты не найдена", cardDetails.getAttribute("placeholder"));
    }

    @Test
    public void testPaymentLogos() {
        WebElement paymentLogos = driver.findElement(By.xpath("//div[@class='pay__partners']"));
        List<WebElement> logos = paymentLogos.findElements(By.tagName("img"));
        Assert.assertTrue("Логотипы платежных систем не найдены", logos.size() > 0);
        for (WebElement logo : logos) {
            Assert.assertTrue("Логотип платежной системы не отображается", logo.isDisplayed());
        }
    }

    @Test
    public void testContinueButton() {
        // Замените "continueButtonId" на реальный id элемента
        WebElement continueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        continueButton.click();
    }
}
