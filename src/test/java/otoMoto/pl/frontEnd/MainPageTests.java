package otoMoto.pl.frontEnd;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import otoMoto.pl.TestBaseClassFrontEnd;

public class MainPageTests extends TestBaseClassFrontEnd {



    @Test
    public void testingOtoMotoMainPage(){

        // Wysyłam żądanie do przeglądarki, aby otworzyła podaną stronę.
        driver.get("https://otomoto.pl");

        // Wyszukuję element na stronie o konkretnym ID, a następnie klikam w niego.
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();



    }

}
