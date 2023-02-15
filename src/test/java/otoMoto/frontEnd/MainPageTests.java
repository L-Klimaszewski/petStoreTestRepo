package otoMoto.frontEnd;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;
import otoMoto.TestBaseClassFrontEnd;

public class MainPageTests extends TestBaseClassFrontEnd {

    @FindBy (xpath = "//*[@id='filter_enum_make']/div/input")
    private WebElement searchBrandInput;

    @FindBy (xpath = "//*[@id='filter_float_price:to']/div")
    private WebElement chooseMaxPrice;

    @FindBy (xpath = "//*[@id='filter_float_price:to']/ul/li[12]/div/div/div/div")
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
        chooseMaximumPrice.click();

    }

}
