package otoMoto;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;


public class TestBaseClassFrontEnd {

    /* Tworzę zmienną 'driver' typu "WebDriver" z modyfikatorem public, tak aby 'driver' był dostępny dla
     innych klas.  */
    public WebDriver driver;

    @BeforeSuite
    public static void getSuiteName(ITestContext context) {

        // Przy pomocy obiektu ITestContext pobieram nazwę bierzącego profilu i wyświetlam ją w konsoli.

        System.out.println("Suite name: " + context.getCurrentXmlTest().getSuite().getName());
    }

    @BeforeClass
    public void beforeClass() {

        /* Powołuję nowy obiekt typu String i przypisuję do niego nazwę klasy dla której ten kod jest
          wykonywany. Następnie wynik tej operacji jest wypisywany w konsoli przy użyciu metody "println". */

        String className = this.getClass().getSimpleName();
        System.out.println("Class name: " + className);
    }

    @BeforeMethod
    public void beforeMethod(ITestResult result) {

        // Wyświetlam nazwę metody na konsoli
        System.out.println("Method name: " + result.getMethod().getMethodName());

        /* Pobieram najnowszą wersję ChromDriver. Następnie tworzę jego nową instancję
         oraz inicjuję Page Factory dla klasy w której będę ich używać */

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        PageFactory.initElements(driver, this);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {

        // Sprawdzam status testu i wyświetlam jego wynik na konsoli

        try {
            if (result.getStatus() == ITestResult.SUCCESS) {
                System.out.println("Test Succeed");
            } else if (result.getStatus() == ITestResult.FAILURE) {
                System.out.println("Test Failed");
            } else if (result.getStatus() == ITestResult.SKIP) {
                System.out.println("Test Skipped");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {

        // Wyświetlam informację o końcu danej klasy.

        System.out.println("End of class");
    }

    @AfterSuite
    public void afterSuite() {

        // Wyświetlam informację o końcu danego profilu testowego.

        System.out.println("End of the suite");
    }

    public void clickElement (WebElement element){

        // Metoda używana do kliknięcia w element przekazany jako argument.

        element.click();
    }


    public void typeTextIntoInput(WebElement element,String search){

        /* Metoda, która przesyła łancuch znaków przekazany jako drugi argument
          do obiektu przekazanego jako pierwszy argument. */

        element.sendKeys(search);
    }

}
