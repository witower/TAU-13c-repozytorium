package pl.edu.pjatk.tau.SeleniumLab.orderForm;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class CompletedSuccessTest {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testOrderFormSuccess() throws Exception {
        driver.get("https://roberts.pl/index.php?l=en&p=_katalog&i=_neutron_plus");
        driver.findElement(By.name("SubmitNext")).click();
        assertTrue(driver.getCurrentUrl().matches("^https://roberts\\.pl/index\\.php[\\s\\S]l=en&p=_zamowienie&i=_neutron_plus$"));
        assertEquals("1", driver.findElement(By.name("step")).getAttribute("value"));
        driver.findElement(By.name("krok1-qty-required")).click();
        driver.findElement(By.name("krok1-qty-required")).clear();
        driver.findElement(By.name("krok1-qty-required")).sendKeys("2");
        driver.findElement(By.name("krok1-info1")).click();
        driver.findElement(By.name("krok1-info1")).clear();
        driver.findElement(By.name("krok1-info1")).sendKeys("Example comment");
        driver.findElement(By.cssSelector("[type='submit']")).click();
        assertTrue(driver.getCurrentUrl().matches("^https://roberts\\.pl/index\\.php[\\s\\S]l=en&p=_zamowienie$"));
        assertEquals("2", driver.findElement(By.name("step")).getAttribute("value"));
        driver.findElement(By.xpath("(//input[@name='krok2-sposob_dostawy-required'])[3]")).click();
        driver.findElement(By.id("datepicker")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Links'])[2]/following::span[1]")).click();
        driver.findElement(By.linkText("30")).click();
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
        assertTrue(driver.getCurrentUrl().matches("^https://roberts\\.pl/index\\.php[\\s\\S]l=en&p=_zamowienie$"));
        assertEquals("3", driver.findElement(By.name("step")).getAttribute("value"));
        driver.findElement(By.xpath("(//input[@name='akceptuje_regulamin-required'])[2]")).click();
        driver.findElement(By.name("SubmitNext")).click();
        for (int second = 0;; second++) {
            if (second >= 60) fail("timeout");
            try {
                if ("4".equals(driver.findElement(By.name("step")).getAttribute("value"))) break;
            } catch (Exception e) {
                verificationErrors.append(e.getMessage());
            }
            Thread.sleep(1000);
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
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
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
