package ui

import com.example.semprace.Creator
import com.example.semprace.NnpiaSemPraceApplication
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = NnpiaSemPraceApplication.class)
@Import(Creator.class)
class AuthenticationTest {

    private static final int RESPONSE_TIMEOUT = 30;
    private static final String CORRECT_USER = "user";
    private static final String CORRECT_PASSWORD = "user";

    private WebDriver driver;

    @BeforeAll
    public static void setupWebdriverChromeDriver() {
        String chromedriverPath = AuthenticationTest.class.getResource("/chromedriver.exe").getFile();
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
    }

    @BeforeEach
    public void beforeEach() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (driver != null) driver.quit();
    }

    @Test
    public void loginUser() {
        signIn(CORRECT_USER, CORRECT_PASSWORD, false);
    }

    @Test()
    public void loginUserBadCredentials() {
        Assertions.assertThrows(Exception.class,
                signIn(String.valueOf(new Random().nextInt()), String.valueOf(new Random().nextInt()), true))
    }



    private void signIn(final String username, final String password, final boolean expectedFail) {
        driver.get("https://st55425-frontend.herokuapp.com");
        driver.findElement(By.cssSelector("ul > li:last-of-type")).click();
        driver.findElement(By.cssSelector("input[type=\"text\"]")).sendKeys(username);
        driver.findElement(By.cssSelector("input[type=\"password\"]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type=\"submit\"]")).click();

        WebDriverWait wt = new WebDriverWait(driver, RESPONSE_TIMEOUT);
        if(!expectedFail){
            wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dropdown")));
        }else{
            wt.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-inline-message-text")));
        }
    }



}
