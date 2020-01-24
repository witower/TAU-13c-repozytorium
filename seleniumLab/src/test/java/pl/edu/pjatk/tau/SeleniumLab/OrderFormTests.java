package pl.edu.pjatk.tau.SeleniumLab;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OrderFormTests {
    private static WebDriver driver;
    private  String baseUrl = "https://roberts.pl/index.php?l=en&p=_katalog&i=_neutron_plus";
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    private static ChromeOptions options = new ChromeOptions();

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);

    @BeforeClass
    public static void setUpBc(){
        options.setPageLoadStrategy(PageLoadStrategy.EAGER); //NONE powoduje INFO: HTTP Status: '404' -> incorrect JSON status mapping for 'stale element reference' (400 expected)
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Before
    public void setUp() throws Exception {
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
    public void successfulOrderTest() throws Exception {
        goToStep2();

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

    @Test
    public void deliveryAddressShowHideTest() throws Exception {
        goToStep2();

        assertTrue(driver.findElement(By.id("answer2")).getAttribute("style").contains("display: none"));
        driver.findElement(By.cssSelector("[value='_formularz_zamowienia_sposob_dostawy_kurier']")).click();
        assertTrue(driver.findElement(By.id("answer2")).getAttribute("style").contains("display: block"));
        driver.findElement(By.cssSelector("[value='_formularz_zamowienia_sposob_dostawy_odbior_osobisty']")).click();
        assertTrue(driver.findElement(By.id("answer2")).getAttribute("style").contains("display: none"));
    }

    @Test
    public void emailAddressVerificationTest() throws Exception {
        goToStep2();

        driver.findElement(By.name("krok2-email-required")).clear();
        driver.findElement(By.name("krok2-emailv-required")).clear();
        driver.findElement(By.name("SubmitNext")).click();
        assertTrue(driver.findElement(By.xpath("(//input[@name='krok2-email-required'])/..")).getAttribute("class").contains("form_error"));

        driver.findElement(By.name("krok2-email-required")).clear();
        driver.findElement(By.name("krok2-email-required")).sendKeys("bezmałpy");
        driver.findElement(By.name("SubmitNext")).click();
        assertTrue(driver.findElement(By.xpath("(//input[@name='krok2-email-required'])/..")).getAttribute("class").contains("form_error"));

        driver.findElement(By.name("krok2-email-required")).clear();
        driver.findElement(By.name("krok2-email-required")).sendKeys("bezdomeny@");
        driver.findElement(By.name("SubmitNext")).click();
        assertTrue(driver.findElement(By.xpath("(//input[@name='krok2-email-required'])/..")).getAttribute("class").contains("form_error"));

        driver.findElement(By.name("krok2-email-required")).clear();
        driver.findElement(By.name("krok2-email-required")).sendKeys("bezdomenyTld@domena");
        driver.findElement(By.name("SubmitNext")).click();
        assertTrue(driver.findElement(By.xpath("(//input[@name='krok2-email-required'])/..")).getAttribute("class").contains("form_error"));

        driver.findElement(By.name("krok2-email-required")).clear();
        driver.findElement(By.name("krok2-email-required")).sendKeys("adres@poprawny.pl");
        driver.findElement(By.name("SubmitNext")).click();
        assertFalse(driver.findElement(By.xpath("(//input[@name='krok2-email-required'])/..")).getAttribute("class").contains("form_error"));

        driver.findElement(By.name("krok2-emailv-required")).clear();
        driver.findElement(By.name("SubmitNext")).click();
        assertTrue(driver.findElement(By.xpath("(//input[@name='krok2-emailv-required'])/..")).getAttribute("class").contains("form_error"));

        driver.findElement(By.name("krok2-emailv-required")).clear();
        driver.findElement(By.name("krok2-emailv-required")).sendKeys("adres@poprawny.pl");
        driver.findElement(By.name("SubmitNext")).click();
        assertFalse(driver.findElement(By.xpath("(//input[@name='krok2-emailv-required'])/..")).getAttribute("class").contains("form_error"));
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

    private void goToStep2() {
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
