package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassAPI;
import petStore.models.Pet;


public class PutPetTests extends TestBaseClassAPI {

    @Test
    public void updatingOldRecordWithNewData() {

        /* Tworzę nowy obiekt klasy "PutPet". Wywołując metodę "getRequestBody" na obiekcie putPet uzyskuję
          dostęp do "body" żądania, a następnie przy użyciu metod "setId", "setName" oraz "setStatus" wprowadzam
          nowe wartości. Tak ustawiony obiekt putPet przekazuję jako argument metody "getResponsePut", która
          wysyła żądanie PUT do API. */

        PutPet putPet = new PutPet();
        putPet.getRequestBody().setId(123124L);
        putPet.getRequestBody().setName("Johnny B Good");
        putPet.getRequestBody().setStatus(Pet.StatusEnum.SOLD);
        getResponsePut(putPet);

        /* Tworzę obiekt klasy "SoftAssertions", dzięki czemu będę mogł wykorzystać tzw. miękkie asercję do
          sprawdzenia czy określone przeze mnie warunki zostały spełnione. W argumencie metody "assertThat"
          umieszczam dane, które otrzymałem w odpowiedzi z API. Następnie w argumencie metody "isEqual"
          przekazuję warunki jakich spełnienia oczekuję. Wywołanie metody "assertAll" powoduje sprawdzenie
          wszystkich asercji i poinformowanie o wyniku. */

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(putPet.getResponseBody().getId()).isEqualTo(123124L);
        softly.assertThat(putPet.getResponseBody().getName()).isEqualTo("Johnny B Good");
        softly.assertThat(putPet.getResponseBody().getStatus()).isEqualTo(putPet.getRequestBody().getStatus());
        softly.assertAll();
    }

    @Test
    public void checkStatusCodeAndErrorResponseWhenArabicSentenceProvided(){

        PutPet putPet = new PutPet();
        SoftAssertions softly = new SoftAssertions();
        long id = 165768L;
        String name = "يزعجني أنك تشخر.";

        putPet.getRequestBody().setId(id);
        putPet.getRequestBody().setName(name);
        getResponsePut(putPet);

        softly.assertThat(putPet.getResponseBody().getId()).isEqualTo(id);
        softly.assertThat(putPet.getResponseBody().getName()).isEqualTo(name);
        softly.assertAll();
    }

     /*  Z mojego punktu widzenia aplikacja działa prawidłowo i bez dokumentacji nie
       jestem w stanie przeprowadzić dalszych testów.  */
}
