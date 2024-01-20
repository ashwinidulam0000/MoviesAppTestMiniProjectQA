package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MovieDetailsPage {
    WebDriver driver;
    WebDriverWait wait;

    By movieTitleLocator = By.className("movie-title");
    By movieReviewLocator = By.xpath("//div[@class='movie-review-container']/child::*");
    By movieOverviewLocator = By.className("movie-overview");
    By playBtnLocator = By.className("play-button");
    By genresHeadingLocator = By.className("genres-heading");
    By genresContainerLocator = By.xpath("//div[@class='genres-container']/child::*");
    By audioHeadingLocator = By.className("audio-heading");
    By audioContainerLocator = By.xpath("//ul[@class='audio-container']//li//p");
    By ratingCategoryLocator = By.xpath("//div[@class='rating-category']/child::*");
    By budgetCategoryLocator = By.xpath("//div[@class='budget-category']/child::*");
    By similarMoviesHeadingLocator = By.className("similar-movies-heading");
    By similarMoviesLocator = By.xpath("//ul[@class='similar-movies-list-container']/child::*/img");

    public MovieDetailsPage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getMovieTitle(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(movieTitleLocator));
        return driver.findElement(movieTitleLocator).getText();
    }

    public List<WebElement> getMovieReview(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(movieReviewLocator));
        List<WebElement> movieReview = driver.findElements(movieReviewLocator);
        return movieReview;
    }

    public String getMovieOverview(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(movieOverviewLocator));
        return driver.findElement(movieOverviewLocator).getText();
    }

    public WebElement getPlayBtn(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(playBtnLocator));
        return driver.findElement(playBtnLocator);
    }

    public String getGenresHeading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(genresHeadingLocator));
        return driver.findElement(genresHeadingLocator).getText();
    }

    public List<WebElement> getGenresContainer(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(genresContainerLocator));
        List<WebElement> genresContainer = driver.findElements(genresContainerLocator);
        return genresContainer;
    }

    public String getAudioHeading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(audioHeadingLocator));
        return driver.findElement(audioHeadingLocator).getText();
    }

    public List<WebElement> getAudioContainer(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(audioContainerLocator));
        List<WebElement> audioContainer = driver.findElements(audioContainerLocator);
        return audioContainer;
    }

    public List<WebElement> getRatingCategory(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(ratingCategoryLocator));
        List<WebElement> ratingCategory = driver.findElements(ratingCategoryLocator);
        return ratingCategory;
    }

    public List<WebElement> getBudgetCategory(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(budgetCategoryLocator));
        List<WebElement> budgetCategory = driver.findElements(budgetCategoryLocator);
        return budgetCategory;
    }

    public String getSimilarMoviesHeading(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(similarMoviesHeadingLocator));
        return driver.findElement(similarMoviesHeadingLocator).getText();
    }

    public List<WebElement> getSimilarMoviesList(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(similarMoviesLocator));
        List<WebElement> similarMoviesList = driver.findElements(similarMoviesLocator);
        return similarMoviesList;
    }
}

