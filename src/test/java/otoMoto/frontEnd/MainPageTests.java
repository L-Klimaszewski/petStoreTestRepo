package otoMoto.frontEnd;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import otoMoto.TestBaseClassFrontEnd;

import java.time.Duration;

public class MainPageTests extends TestBaseClassFrontEnd {

    @FindBy (xpath = "//*[@id='filter_enum_make']/div/input")
    private WebElement searchBrandInput;

    @FindBy (xpath = "//*[@id='filter_float_price:to']/div")
    private WebElement chooseMaxPrice;

    @FindBy (xpath = "//*[@id='filter_float_price:to']/ul/li[12]")
    private WebElement chooseMaximumPrice;


    @Test
    public void testingOtoMotoMainPage(){

        // Wysyłam żądanie do przeglądarki, aby otworzyła podaną stronę.
        driver.get("https://otomoto.pl");

        // Wyszukuję element na stronie o konkretnym ID, a następnie klikam w niego.
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();


        search(searchBrandInput,"Opel");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(searchBrandInput.isDisplayed()).isTrue();
        softly.assertAll();

        chooseMaxPrice.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        By listLocator = By.xpath("//*[@id='filter_float_price:to']/ul");
        wait.until(ExpectedConditions.visibilityOfElementLocated(listLocator));
        Actions actions = new Actions(driver);
        actions.moveToElement(chooseMaximumPrice).click().build().perform();


    }

}
