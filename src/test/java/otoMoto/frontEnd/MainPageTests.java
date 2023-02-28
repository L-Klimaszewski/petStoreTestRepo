package otoMoto.frontEnd;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import otoMoto.TestBaseClassFrontEnd;
import otoMoto.models.MainPage;

import java.time.Duration;

        /* Klasa testowa "MainPageTests" rozszerza klasę "TestBaseClassFrontEnd" uzyskując dostęp do pól oraz
        metod w niej zawartych. */

public class MainPageTests extends TestBaseClassFrontEnd {

    @Test
    public void checkIfCarTypeAndPriceChosenCorrectly() {

        /* Tworzę nową instancję klas "WebDriverWait", "SoftAssertions" oraz "Actions", których używam w teście.
        W konstruktorze obiektu wait przekazuję dwa argumenty. Obiekt driver, który reprezentuje kontrolę nad
        przeglądarką. Drugim argumentem jest czas oczekiwania w postaci obiektu Duration. Klasy "SoftAssertions"
        użyję do potwierdzenia czy oczekiwane dane są zgodne z wprowadzonymi. Natomiast klasę "Actions" wykorzystam
        do interakcji z elementami wyświetlanymi na stronie. */

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        SoftAssertions softly = new SoftAssertions();
        Actions actions = new Actions(driver);

        /* Wykonuję metodę "getToMainPage" na nowo utworzonym obiekcie mainPage klasy "MainPage" aby przenieść się
         pod adres zdefiniowany w tej metodzie. Dzięki instancji tej klasy mam dostęp do zawartych w niej modelach
         oraz metodach. */

        MainPage mainPage = new MainPage(driver);
        mainPage.getToMainPage();

        /* Oczekuję aż dany element będzie widoczny na stronie przy użyciu metody "until" przyjmującej jako argument
        metody "ExpectedConditions" określającej konkretny warunek do spełnienia. Po jego spełnieniu, używam metody
        "clickElement" zdefiniowanej w "TestBaseClassFrontEnd" na  */

        wait.until(ExpectedConditions.visibilityOf(mainPage.cookies));
        mainPage.clickElement(mainPage.cookies);

        /* Przy pomocy metody "typeTextIntoInput"z klasy "TestBaseClassFrontEnd" wprowadzam konkretne dane w pole
        tekstowe. Metoda ta przyjmuje za argumenty obiekt typu WebElement, na którym będzie wykonana oraz łańcuch
        znaków do wprowadzenia. Strona korzysta z sugestii danych wyszukiwania, więc używam metody klasy "Actions"
        do przeniesienia się na intersujący mnie wynik spośród sugerowanych i klikam w element w celu potwierdzenia
        wyboru. Następnie korzystając z metody "assertThat" sprawdzam czy wybrane dane są zgodne z oczekiwanymi i
        wyświetlone w odpowienim elemencie. */

        mainPage.typeTextIntoInput(mainPage.searchBrandInput, "Op");
        actions.moveToElement(mainPage.chooseCarBrand).click().build().perform();
        softly.assertThat(mainPage.searchBrandInput.getAttribute("value")).contains("Opel");

        /* Metoda "clickElement" z klasy "TestBaseClassFrontEnd" klika w przycisk rozwijający listę wyboru cen.
        Używam metody "moveToElement" z klasy Actions" do przeniesienia się na wybrany przeze mnie element reprezenujący
        interesującą mnie cenę. Następnie w niego klikam aby potwierdzić wybór. Metoda "AssertThat" z klasy
        "SoftAssertions" sprawdza czy dane wyświetlane w oczekiwanym miejscu są zgodne z wybranymi. */

        mainPage.clickElement(mainPage.chooseMinPriceButton);
        actions.moveToElement(mainPage.chooseMinPrice).click().build().perform();
        softly.assertThat(mainPage.chooseMinPriceInput.getAttribute("value")).contains("25 000 PLN");

        /* Wprowadzam dane do pola tekstowego używając metody "typeTextIntoInput" z klasy "TestBaseFrontEnd" na
        obiekcie mainPage z klasy "MainPage". W argumentach przekazuję obiekt na którym ma być wykonana ta
        metoda oraz wartość jaka ma być wpisana. Ponieważ podaję dokładną cenę, strona sugeruje nie interesujące
        mnie wartości. Klikam w wolnym miejscu aby jeszcze nie akceptować formularza wyszukiwania przy pomocy
        metody "click" z klasy "Actions". Dzięki czemu mogę przy użyciu metody "assertThat" sprawdzić czy wyświetlana
        wartość zgadza się z wybraną przeze mnie. */

        mainPage.typeTextIntoInput(mainPage.chooseMaxPriceInput, "50 000");
        actions.click(driver.findElement(By.xpath("//body"))).build().perform();
        softly.assertThat(mainPage.chooseMaxPriceInput.getAttribute("value")).contains("50 000 PLN");

        /* Klikam w element przekazany jako argument metody "clickElement" z klasy "TestBaseClassFrontEnd"
        w celu potwierdzenia formularza wyszukiwania. */

        mainPage.clickElement(mainPage.submitButton);

        /* Przy użyciu metody "until" z klasy "WebDriverWait" czekam aż element chosenCarBrand będzie dostępny do
        kliknięcia.  Spełnienie tego warunku pozwoli mi na dostęp do tego elementu, co umożliwi mi wykorzystanie
        miękkich asercji. Metodą "assertThat" sprawdzam czy wyświetlone wartości zgadzają się z wybranymi i
        wprowadzonymi wcześniej.  */

        wait.until(ExpectedConditions.visibilityOf(mainPage.chosenCarBrand));
        softly.assertThat(mainPage.chosenCarBrand.getAttribute("value").contains("Opel")).isTrue();
        wait.until(ExpectedConditions.visibilityOf(mainPage.chosenMinPrice));
        softly.assertThat(mainPage.chosenMinPrice.getAttribute("value").contains("25 000")).isTrue();
        softly.assertThat(mainPage.chosenMaxPrice.getAttribute("value").contains("50 000")).isTrue();
        softly.assertAll();
    }
}
