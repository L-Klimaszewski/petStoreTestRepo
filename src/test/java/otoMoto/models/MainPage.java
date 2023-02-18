package otoMoto.models;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import otoMoto.TestBaseClassFrontEnd;

    /* Klasa "MainPage" jest reprezentacją głównej strony, którą testuję. Jest klasą pochodną klasy
     "TestBaseClassFrontEnd" co pozwala mi na korzystanie z metod w niej zawartych. Klasa "MainPage"
     zawiera elementy strony  */

public class MainPage extends TestBaseClassFrontEnd {

    /* Konstruktor klasy "MainPage" zostanie wywoływany podczas tworzenia nowego obiektu tej klasy.
     Przyjmuje za argument obiekt klasy "WebDriver" o nazwie 'driver'. Obiekt 'driver' przypisany
     jest do pola 'driver' wewnątrz obiektu klasy "MainPage". */
    public MainPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    /* Poszczególne elementy strony identyfikowane po ich unikalnych atrybutach takich jak id, xpath itp.
     Są to elementy, których używam w testach. Adnotacje @FindBy pozwalają mi na łatwe zlokalizowanie
     elementów strony i użycie ich w metodach klas. */

    @FindBy (id = "onetrust-accept-btn-handler")
    public WebElement cookies;
    @FindBy (xpath = "//*[@id='filter_enum_make']/div/input")
    public WebElement searchBrandInput;
    @FindBy (css = "li:nth-child(3) div:nth-child(1) div:nth-child(1) div:nth-child(1) div:nth-child(1) span:nth-child(1)")
    public WebElement chooseCarBrand;
    @FindBy (css = "#filter_float_price\\:to > div > input")
    public WebElement chooseMaxPriceInput;
    @FindBy (xpath = "//*[@id='filter_float_price:from']/div/button")
    public WebElement chooseMinPriceButton;
    @FindBy (xpath = "//*[@id='filter_float_price:from']/ul/li[8]")
    public WebElement chooseMinPrice;
    @FindBy (xpath ="//*[@id='filter_float_price:from']/div/input")
    public WebElement chooseMinPriceInput;
    @FindBy (xpath = "//*[@id='__next']/div/div/div/main/div[2]/article/fieldset/div/form/div[2]/button[1]")
    public WebElement submitButton;
    @FindBy (xpath = "//*[@id='__next']/div/div/div/div[2]/div[1]/div/form/section/div/div[2]/div/div/input")
    public WebElement chosenCarBrand;
    @FindBy (xpath = "//*[@id='__next']/div/div/div/div[2]/div[1]/div/form/section/div/div[5]/div/div[1]/div/div/input")
    public WebElement chosenMinPrice;
    @FindBy (xpath = "//*[@id='__next']/div/div/div/div[2]/div[1]/div/form/section/div/div[5]/div/div[2]/div/div/input")
    public WebElement chosenMaxPrice;


    // Metoda otwiera stronę główną otomoto.pl z użyciem obiektu WebDriver.
    public void getToMainPage(){
        driver.get("https://otomoto.pl");
    }
}
