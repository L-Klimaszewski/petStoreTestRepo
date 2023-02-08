package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassBackEnd;

import java.util.HashMap;
import java.util.Map;

public class DeletePetTests extends TestBaseClassBackEnd {

    @Test

    public void deletingExistingRecord (){
        // Tworzę nowe obiekty klas DeletePet, HashMap, oraz SoftAssertions i przypisuję je do odpowienich zmiennych
        DeletePet deletePet = new DeletePet();
        Map<String,String> paramsMap = new HashMap<>();
        SoftAssertions softly = new SoftAssertions();
        String id = "189273";

        // Do zmiennej paramsMap dodaję parametry w postaci String, które następnie przekazuję jako argumenty do podanej poniżej metody
        paramsMap.put("id",id);

        // Wysyłam zapytanie do Api z parametrami zdefiniowanymi wcześniej
        getResponseDeleteSuccessTest(deletePet,paramsMap);

        // Dodaje asercje do danych uzyskanych w odpowiedzi z API
        softly.assertThat(deletePet.getResponseBody().getCode()).isEqualTo(200);
        softly.assertThat(deletePet.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(deletePet.getResponseBody().getMessage()).isEqualTo(id);
        softly.assertAll();

        /*
        ID to use:
        189273
        24329
        222
        112233
        444
        420
        */

    }
}
