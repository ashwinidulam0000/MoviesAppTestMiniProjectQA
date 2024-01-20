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
import pages.MovieDetailsPage;
import pages.PopularPage;

import java.time.Duration;
import java.util.*;

public class MovieDetailsPageTest {
    public WebDriver driver;
    public WebDriverWait wait;
    LoginPage loginPage;
    HomePage homePage;
    PopularPage popularPage;
    MovieDetailsPage movieDetailsPage;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ramesh\\Desktop\\Selenium WebDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://qamoviesapp.ccbp.tech");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        popularPage = new PopularPage(driver);
        movieDetailsPage = new MovieDetailsPage(driver);

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
    public void popularMovieDetailsUiTest(){
        WebElement popularEl = homePage.getPopularEl();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        popularEl.click();

        String expUrl2 = "https://qamoviesapp.ccbp.tech/popular";
        wait.until(ExpectedConditions.urlToBe(expUrl2));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl2, "Popular Section URL is not matching..");

        List<WebElement> popularMovies = popularPage.getPopularMoviesList();
        popularMovies.get(3).click();

        String expUrl = "https://qamoviesapp.ccbp.tech/movies/b2872586-3e64-4fc0-a244-f21c2e62fb37";
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "Movie details page URL is not matching..");

        String movieTitle = movieDetailsPage.getMovieTitle();
        String expMovieTitle = "Luca";

        Assert.assertEquals(movieTitle, expMovieTitle, "Movie title is not matching..");

        List<WebElement> movieReview = movieDetailsPage.getMovieReview();

        String watchTime = movieReview.get(0).getText();
        String sensorRating = movieReview.get(1).getText();
        String releaseYear = movieReview.get(2).getText();

        String expWatchTime = "2h 7m";
        String expSensorRating = "U/A";
        String expReleaseYear = "2021";

        Assert.assertEquals(watchTime, expWatchTime, "Watch time is not matching..");
        Assert.assertEquals(sensorRating, expSensorRating, "Sensor Rating is not matching..");
        Assert.assertEquals(releaseYear, expReleaseYear, "Release year is not matching..");

        String movieOverview = movieDetailsPage.getMovieOverview();
        String expMovieOverview = "Luca and his best friend Alberto experience an unforgettable summer on the Italian Riviera. But all the fun is threatened by a deeply-held secret: they are sea monsters from another world just below the water’s surface.";

        Assert.assertEquals(movieOverview, expMovieOverview, "Movie overview is not matching..");

        WebElement playBtnEl = movieDetailsPage.getPlayBtn();

        Assert.assertTrue(playBtnEl.isDisplayed(), "Play button is not displayed..");

        String genresHeading = movieDetailsPage.getGenresHeading();
        String expGenreHeading = "Genres";

        Assert.assertEquals(genresHeading, expGenreHeading, "Genres Heading is not matching..");

        List<WebElement> genresContainer = movieDetailsPage.getGenresContainer();

        String[] expGenresContent = {"Action","Crime","Thriller"};

        ArrayList<String> genresContent = new ArrayList<String>();

        for(WebElement genreEl : genresContainer){
            genresContent.add(genreEl.getText());
        }

        ArrayList<String> expGenresContentList = new ArrayList<String>(Arrays.asList(expGenresContent));

        Assert.assertEquals(genresContent, expGenresContentList, "Genres Content is not matching..");

        String audioHeading = movieDetailsPage.getAudioHeading();
        String expAudioHeading = "Audio Available";

        Assert.assertEquals(audioHeading, expAudioHeading, "Audio heading is not matching..");

        List<WebElement> audioContainer = movieDetailsPage.getAudioContainer();

        String[] expAudioContent = {"German","English","Czech","French"};

        ArrayList<String> audioContent = new ArrayList<String>();

        for(WebElement audioEl : audioContainer){
            audioContent.add(audioEl.getText());
        }

        ArrayList<String> expAudioContentList = new ArrayList<String>(Arrays.asList(expAudioContent));

        Assert.assertEquals(audioContent, expAudioContentList, "Audio content is not matching..");

        List<WebElement> ratingCategory = movieDetailsPage.getRatingCategory();

        String ratingHeading = ratingCategory.get(0).getText();
        String ratingText = ratingCategory.get(1).getText();
        String ratingAvgHeading = ratingCategory.get(2).getText();
        String ratingAvgText = ratingCategory.get(3).getText();

        Assert.assertEquals(ratingHeading, "Rating Count", "Rating heading is not matching..");
        Assert.assertEquals(ratingText, "645", "Rating text is not matching..");
        Assert.assertEquals(ratingAvgHeading, "Rating Average", "Rating average heading is not matching..");
        Assert.assertEquals(ratingAvgText, "6.9", "Rating average text is not matching..");

        List<WebElement> budgetCategory = movieDetailsPage.getBudgetCategory();

        String budgetHeading = budgetCategory.get(0).getText();
        String budgetText = budgetCategory.get(1).getText();
        String releaseDateHeading = budgetCategory.get(2).getText();
        String releaseDateText = budgetCategory.get(3).getText();

        Assert.assertEquals(budgetHeading, "Budget", "Budget Heading is not matching..");
        Assert.assertEquals(budgetText, "23 Crores", "Budget text is not matching..");
        Assert.assertEquals(releaseDateHeading, "Release Date", "Release Date heading is not matching..");
        Assert.assertEquals(releaseDateText, "27th October 2021", "Release date text is not matching..");

        String similarMoviesHeading = movieDetailsPage.getSimilarMoviesHeading();

        Assert.assertEquals(similarMoviesHeading, "More like this", "Similar movies heading is not matching..");

        List<WebElement> similarMovies = movieDetailsPage.getSimilarMoviesList();

        Assert.assertEquals(similarMovies.size(), 35, "Similar movies count is not matching..");

    }

    @Test(priority = 2)
    public void homeMovieDetailsUiTest(){
        List<WebElement> homeMovies = homePage.getMoviesList();
        homeMovies.get(12).click();

        String expUrl = "https://qamoviesapp.ccbp.tech/movies/33119fe5-3966-4bba-b98c-10b241ffc9f8";
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlToBe(expUrl));

        Assert.assertEquals(driver.getCurrentUrl(), expUrl, "Movie details page URL is not matching..");

        String movieTitle = movieDetailsPage.getMovieTitle();
        String expMovieTitle = "Titanic";

        Assert.assertEquals(movieTitle, expMovieTitle, "Movie title is not matching..");

        List<WebElement> movieReview = movieDetailsPage.getMovieReview();

        String watchTime = movieReview.get(0).getText();
        String sensorRating = movieReview.get(1).getText();
        String releaseYear = movieReview.get(2).getText();

        String expWatchTime = "1h 35m";
        String expSensorRating = "U/A";
        String expReleaseYear = "1981";

        Assert.assertEquals(watchTime, expWatchTime, "Watch time is not matching..");
        Assert.assertEquals(sensorRating, expSensorRating, "Sensor Rating is not matching..");
        Assert.assertEquals(releaseYear, expReleaseYear, "Release year is not matching..");

        String movieOverview = movieDetailsPage.getMovieOverview();
        String expMovieOverview = "Incorporating both historical and fictionalized aspects, it is based on accounts of the sinking of the RMS Titanic, and stars Leonardo DiCaprio and Kate Winslet";

        Assert.assertEquals(movieOverview, expMovieOverview, "Movie overview is not matching..");

        WebElement playBtnEl = movieDetailsPage.getPlayBtn();

        Assert.assertTrue(playBtnEl.isDisplayed(), "Play button is not displayed..");

        String genresHeading = movieDetailsPage.getGenresHeading();
        String expGenreHeading = "Genres";

        Assert.assertEquals(genresHeading, expGenreHeading, "Genres Heading is not matching..");

        List<WebElement> genresContainer = movieDetailsPage.getGenresContainer();

        String[] expGenresContent = {"Drama"};

        ArrayList<String> genresContent = new ArrayList<String>();

        for(WebElement genreEl : genresContainer){
            genresContent.add(genreEl.getText());
        }

        ArrayList<String> expGenresContentList = new ArrayList<String>(Arrays.asList(expGenresContent));

        Assert.assertEquals(genresContent, expGenresContentList, "Genres Content is not matching..");

        String audioHeading = movieDetailsPage.getAudioHeading();
        String expAudioHeading = "Audio Available";

        Assert.assertEquals(audioHeading, expAudioHeading, "Audio heading is not matching..");

        List<WebElement> audioContainer = movieDetailsPage.getAudioContainer();

        String[] expAudioContent = {"French"};

        ArrayList<String> audioContent = new ArrayList<String>();

        for(WebElement audioEl : audioContainer){
            audioContent.add(audioEl.getText());
        }

        ArrayList<String> expAudioContentList = new ArrayList<String>(Arrays.asList(expAudioContent));

        Assert.assertEquals(audioContent, expAudioContentList, "Audio content is not matching..");

        List<WebElement> ratingCategory = movieDetailsPage.getRatingCategory();

        String ratingHeading = ratingCategory.get(0).getText();
        String ratingText = ratingCategory.get(1).getText();
        String ratingAvgHeading = ratingCategory.get(2).getText();
        String ratingAvgText = ratingCategory.get(3).getText();

        Assert.assertEquals(ratingHeading, "Rating Count", "Rating heading is not matching..");
        Assert.assertEquals(ratingText, "39", "Rating text is not matching..");
        Assert.assertEquals(ratingAvgHeading, "Rating Average", "Rating average heading is not matching..");
        Assert.assertEquals(ratingAvgText, "7.6", "Rating average text is not matching..");

        List<WebElement> budgetCategory = movieDetailsPage.getBudgetCategory();

        String budgetHeading = budgetCategory.get(0).getText();
        String budgetText = budgetCategory.get(1).getText();
        String releaseDateHeading = budgetCategory.get(2).getText();
        String releaseDateText = budgetCategory.get(3).getText();

        Assert.assertEquals(budgetHeading, "Budget", "Budget Heading is not matching..");
        Assert.assertEquals(budgetText, "1.6 Crores", "Budget text is not matching..");
        Assert.assertEquals(releaseDateHeading, "Release Date", "Release Date heading is not matching..");
        Assert.assertEquals(releaseDateText, "25th March 1981", "Release date text is not matching..");

        String similarMoviesHeading = movieDetailsPage.getSimilarMoviesHeading();

        Assert.assertEquals(similarMoviesHeading, "More like this", "Similar movies heading is not matching..");

        List<WebElement> similarMovies = movieDetailsPage.getSimilarMoviesList();

        Assert.assertEquals(similarMovies.size(), 13, "Similar movies count is not matching..");

    }
}

