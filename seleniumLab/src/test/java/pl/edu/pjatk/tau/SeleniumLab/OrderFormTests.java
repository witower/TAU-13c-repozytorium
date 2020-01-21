package pl.edu.pjatk.tau.SeleniumLab;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class OrderFormTests {
    private static WebDriver driver;
    private  String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);

    @BeforeClass
    public static void setUpBc(){
        driver = new ChromeDriver();
    }

    @Before
    public void setUp() throws Exception {
        baseUrl = "https://roberts.pl/index.php?l=en&p=_katalog&i=_neutron_plus";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
        driver.findElement(By.name("SubmitNext")).click();
        try {
            assertTrue(driver.getCurrentUrl().contains("p=_zamowienie&i=_neutron_plus"));
            assertEquals("1", driver.findElement(By.name("step")).getAttribute("value"));
        } catch (Exception e) {
            verificationErrors.append(e.getMessage());
        }
    }

    @Test
    public void testOrderFormSuccess() throws Exception {

        driver.findElement(By.name("krok1-qty-required")).click();
        driver.findElement(By.name("krok1-qty-required")).clear();
        driver.findElement(By.name("krok1-qty-required")).sendKeys("2");
        driver.findElement(By.name("krok1-info1")).click();
        driver.findElement(By.name("krok1-info1")).clear();
        driver.findElement(By.name("krok1-info1")).sendKeys("Example comment");
        driver.findElement(By.cssSelector("[type='submit']")).click();
        try {
            assertEquals("2", driver.findElement(By.name("step")).getAttribute("value"));
        } catch (Exception e) {
            verificationErrors.append(e.getMessage());
        }

        driver.findElement(By.xpath("(//input[@name='krok2-sposob_dostawy-required'])[3]")).click();
        driver.findElement(By.id("datepicker")).click();
        driver.findElement(By.cssSelector(".ui-datepicker-calendar [data-handler='selectDay']")).click();
        driver.findElement(By.xpath("(//input[@name='krok2-rodzaj_platnosci-required'])[2]")).click();
        driver.findElement(By.xpath("(//input[@name='krok2-dokument_zakupu-required'])[2]")).click();
        driver.findElement(By.name("krok2-imie-required")).click();
        driver.findElement(By.name("krok2-imie-required")).clear();
        driver.findElement(By.name("krok2-imie-required")).sendKeys("Johnny");
        driver.findElement(By.name("krok2-nazwisko-required")).clear();
        driver.findElement(By.name("krok2-nazwisko-required")).sendKeys("Test");
        driver.findElement(By.name("krok2-email-required")).clear();
        driver.findElement(By.name("krok2-email-required")).sendKeys("johnny@test.com");
        driver.findElement(By.name("krok2-emailv-required")).clear();
        driver.findElement(By.name("krok2-emailv-required")).sendKeys("johnny@test.com");
        driver.findElement(By.name("krok2-telefon-required")).clear();
        driver.findElement(By.name("krok2-telefon-required")).sendKeys("123456789");
        driver.findElement(By.name("SubmitNext")).click();
        try {
            assertEquals("3", driver.findElement(By.name("step")).getAttribute("value"));
        } catch (Exception e) {
            verificationErrors.append(e.getMessage());
        }

        driver.findElement(By.xpath("(//input[@name='akceptuje_regulamin-required'])[2]")).click();
        driver.findElement(By.name("SubmitNext")).click();
        try {
            assertEquals("4", driver.findElement(By.name("step")).getAttribute("value"));
        } catch (Exception e) {
            verificationErrors.append(e.getMessage());
        }

        driver.findElement(By.linkText("Click here to go back to the page before starting inquiry.")).click();
        try {
            assertTrue(driver.getCurrentUrl().matches("^https://roberts\\.pl/index\\.php[\\s\\S]l=en&p=_katalog&i=_neutron_plus$"));
        } catch (Exception e) {
            verificationErrors.append(e.getMessage());
        }
    }

    @After
    public void tearDown() throws Exception {
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @AfterClass
    public static void cleanUp(){
        if (driver != null){
            driver.close();
            driver.quit();
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
