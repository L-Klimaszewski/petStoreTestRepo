package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassAPI;

import java.util.HashMap;
import java.util.Map;

public class GetPetTests extends TestBaseClassAPI {
    @Test
    public void getPetById() {

        /* Tworzę nowy obiekt klasy String i przypisuję do niego wartość. Dzięki takiemu rozwiązaniu będę mógł zmienić
          jego wartość w jednym miejscu zamiast wszystkich w których użyję tego obiektu. Tworzę także nowy obiekt
          klasy "SoftAssertions", którego metod użyję do potwierdzenia zgodności otrzymanych danych z oczekiwanymi.  */

        String id = "1951191000";
        SoftAssertions softly = new SoftAssertions();

        /* Tworzę nowy obiekt klasy "Map", który służy do przetrzymywania par klucz-wartość. Przy pomocy metody
          "put" przypisuję do obiektu paramsMap dwa argumenty: ciąg znaków '"id"' - (klucz), oraz wcześniej utworzony
          obiekt String - (wartość). */

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("id", id);

        /* Do nowo zainicjowanego obiektu klasy PostPet przy pomocy zawartych w tej klasie metod "setId" oraz "setName"
          przypisuje interesujące mnie dane. Metoda "setId" przyjmuje dane typu Long, natomiast obiekt id jest typu
          String. Z tego powodu używam metody "Long.valueOf()" do konwersji typu String na typ Long. Tak ustawiony obiekt
          postPet przekazuję jako argument w metodzie "getResponsePost". Metoda ta wysyła żądanie do API o utworzenie
          nowej pozycji o właściwościach zapisanych w obiekcie postPet. */

        PostPet postPet = new PostPet();
        postPet.getRequestBody().setId(Long.valueOf(id));
        postPet.getRequestBody().setName("Łajka");
        getResponsePost(postPet);

        /* Metoda "getResponseGetPathParamsTest", której używam do wysłania żądania do API przyjmuje 3 argumenty.
          Pierwszym z nich jest nowoutworzony obiekt getPet, który służy do opakowania informacji potrzebnych do wykonania
          żądania Get. Drugi argument to token autoryzacyjny, który jest pusty ponieważ te API go nie wymaga. Natomiast
          trzecim argumentem jest obiekt paramsMap. Zawiera on parametry, które zostaną dodane do ścieżki URL. */

        GetPet getPet = new GetPet();
        getResponseGetPathParamsTest(getPet, "", paramsMap);

        /* Używam metod klasy "GetPet" na obiekcie getPet aby uzyskać dostęp do danych zawartych w "body" odpowiedzi
          z API, które zostały w nim zapisane. Następnie korzystając z metody "isEqual" klasy "SoftAssertions" sprawdzam
          czy otrzymane dane zgadzają się z oczekiwanymi. W metodzie "getId" informacje są typu Long, natomiast metoda
          "isEqual" przyjmuje typ String. Metodą "toString" konwersuję typ Long na typ String. Na koniec używam metody
          "assertAll", która sprawdzi poprzednie warunki assercji. Dzięki temu kod zostanie wykonany do końca zamiast
          kończyć działanie po pierwszym błędzie. Pozwala to na sprawdzenie kilku warunków jednocześnie.  */

        softly.assertThat(getPet.getResponseBody().getName()).isEqualTo("Łajka");
        softly.assertThat(getPet.getResponseBody().getId().toString()).isEqualTo(id);
        softly.assertAll();

    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenShortCutPowerSignProvided() {

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","25p5");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenStringProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","abc");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"abc\"");
        softly.assertAll();
    }
    @Test // QUESTION
    public void checkStatusCodeAndErrorResponseWhenNegativeIntegerProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","-28");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);

        /* Błąd w aplikacji
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"abc\"");*/

        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(1);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("error");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("Pet not found");
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenNullWithQuotesProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","null");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"null\"");
        softly.assertAll();
    }

    @Test // FAILED
    public void checkStatusCodeAndErrorResponseWhenNullWithoutQuotesProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id",null);
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"io.restassured.internal.NoParameterValue@56681eaf\"");
        softly.assertAll();
        // zmienna treść wiadomości
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenDateProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","2049-11-27");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"2049-11-27\"");
        softly.assertAll();

    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenHourProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","19:33:02");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"19:33:02\"");
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenBooleanWithQuotesProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","true");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"true\"");
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenDecimalProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","189,56");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"189,56\"");
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenFloatProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","1,61834");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"1,61834\"");
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenSpecialSignsProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","$^?!%");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"$^?!%\"");
        softly.assertAll();
    }

    @Test // FAILED
    public void checkStatusCodeAndErrorResponseWhenCurlyBracketProvided() {

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id", "{}");
        getResponseGetPathParamsTest(getPet4Errors, "", paramsMap);
        //java.lang.IllegalArgumentException: Path parameters were not correctly defined. Undefined path parameters are: .
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenHexadecimalProvided() {

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id", "0xABCD");
        getResponseGetPathParamsTest(getPet4Errors, "", paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"0xABCD\"");
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenScientificNotationProvided() {

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id", "1.23e+4");
        getResponseGetPathParamsTest(getPet4Errors, "", paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \"1.23e+4\"");
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenEmptySpaceProvided() {

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id", " ");
        getResponseGetPathParamsTest(getPet4Errors, "", paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertThat(getPet4Errors.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(getPet4Errors.getResponseBody().getMessage()).isEqualTo("java.lang.NumberFormatException: For input string: \" \"");
        softly.assertAll();
    }
}
