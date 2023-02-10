package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassAPI;

import java.util.HashMap;
import java.util.Map;

public class GetPetTests extends TestBaseClassAPI {
    @Test
    public void getPetById() {
        // Tworzę nowe obiekty klas GetPet,HashMap oraz SoftAssertions i przypisuję je do odpowienich zmiennych
        GetPet getPet = new GetPet();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();

        // Do zmiennej paramsMap dodaję parametry w postaci String, które następnie przekazuję jako argumenty do podanej poniżej metody
        paramsMap.put("id", "999");
        getResponseGetPathParamsTest(getPet, "", paramsMap);

        // Dodaje asercje do danych uzyskanych w odpowiedzi z API
        softly.assertThat(getPet.getResponseBody().getName()).isEqualTo("Frand");
        softly.assertThat(getPet.getResponseBody().getId()).isEqualTo(999);
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
        softly.assertAll();
    }
    @Test
    public void checkStatusCodeAndErrorResponseWhenNegativeIntegerProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","-28");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(1);//404
        //asercja na wszystko co zwraca error
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
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenNullWithoutQuotesProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id",null);
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenDateProvided(){

        GetPet4Errors getPet4Errors = new GetPet4Errors();
        Map<String, String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        paramsMap.put("id","2049-11-27");
        getResponseGetPathParamsTest(getPet4Errors,"",paramsMap);
        softly.assertThat(getPet4Errors.getResponseBody().getCode()).isEqualTo(404);
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
        softly.assertAll();
    }

    @Test
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
        softly.assertAll();

    }
}
