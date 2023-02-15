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
    @FindBy (xpath = "//*[@id='filter_float_price:to']/div/input")
    private WebElement chooseMaxPriceInput;
    @FindBy (xpath = "//*[@id='filter_float_price:to']/div")
    private WebElement chooseMaxPriceButton;
    @FindBy (xpath = "//*[@id='filter_float_price:to']/ul/li[12]")
    private WebElement chooseMaxPrice;


    @Test
    public void testingOtoMotoMainPage(){

        // Inicjalizuję nowe obiekty klas, których metod będę używał w teście.

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        SoftAssertions softly = new SoftAssertions();
        Actions actions = new Actions(driver);

        // Wysyłam żądanie do przeglądarki, aby otworzyła podaną stronę.
        driver.get("https://otomoto.pl");

        // Wyszukuję element na stronie o konkretnym ID, a następnie klikam w niego.
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();

        // Wykorzystuję metodę "search" do wpisania w polu tekstowym (searchBrandInput) hasła "Opel".
        search(searchBrandInput,"Opel");

        // Używam metody asserThat z klasy "SoftAssertions" do sprawdzenia czy spełnione są określone warunki.

        softly.assertThat(searchBrandInput.isDisplayed()).isTrue();
        softly.assertThat(searchBrandInput.getAttribute("value")).contains("Opel");
        softly.assertAll();

        // Klikam przycisk rozwijający listę cen do wyboru.
        chooseMaxPriceButton.click();

        // Oczekuję aż lista, której adres jest zapisany w zmiennej maxPriceList będzie widoczna.

        By maxPriceList = By.xpath("//*[@id='filter_float_price:to']/ul");
        wait.until(ExpectedConditions.visibilityOfElementLocated(maxPriceList));

        /* Przy pomocy metody "moveToElement" z klasy "Actions" wybieram konkretną cenę zdefiniowaną jako
        obiekt chooseMaxPrice, a następnie kliknięciem wybieram ten obiekt. */

        actions.moveToElement(chooseMaxPrice).click().build().perform();

        // Używam asercji aby sprawdzić czy pole tekstowe zawiera tą samą wartość, którą wybrałem wcześniej.

        softly.assertThat(chooseMaxPriceInput.getAttribute("value")).isEqualTo("50 000 PLN");
        softly.assertAll();

    }

}
