import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class HeaderSectionPageTest {
    public WebDriver driver;
    public WebDriverWait wait;

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ramesh\\Desktop\\Selenium WebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        loginPage.loginToApplication("rahul", "rahul@2021");
        String expUrl = "https://qamoviesapp.ccbp.tech/";
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "URL do not matching..");
    }

    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    @Test(priority = 1)
    public void HeaderSectionUiTest(){
        WebElement moviesLogoEl = homePage.getMoviesLogoEl();
        String homeText = homePage.getHomeText();
        String popularText = homePage.getPopularText();
        WebElement avatarImgEl = homePage.getAvatarImgEl();

        String expHomeText = "Home";
        String expPopularText = "Popular";

        Assert.assertEquals(homeText, expHomeText, "Home Text is not matching..");
        Assert.assertEquals(popularText, expPopularText, "Popular Text is not matching..");
        Assert.assertTrue(moviesLogoEl.isDisplayed(), "Movies Logo is not displayed..");
        Assert.assertTrue(avatarImgEl.isDisplayed(), "Avatar Image is not displayed..");

    }

    @Test(priority = 2)
    public void HeaderSectionFunctionalityTest(){
        WebElement popularEl = homePage.getPopularEl();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        popularEl.click();

        String expUrl = "https://qamoviesapp.ccbp.tech/popular";
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "Popular Section URL is not matching..");

        WebElement homeEl = homePage.getHomeEl();
        homeEl.click();

        expUrl = "https://qamoviesapp.ccbp.tech/";
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "Home Section URL is not matching..");

        WebElement searchEl = homePage.getSearchEl();
        searchEl.click();

        expUrl = "https://qamoviesapp.ccbp.tech/search";
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "Search Section URL is not matching..");

        WebElement avatarImgEl = homePage.getAvatarImgEl();
        avatarImgEl.click();

        expUrl = "https://qamoviesapp.ccbp.tech/account";
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "Account Section URL is not matching..");


    }

}

