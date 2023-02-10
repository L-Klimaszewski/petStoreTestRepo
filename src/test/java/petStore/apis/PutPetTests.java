package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassAPI;
import petStore.models.Category;
import petStore.models.Pet;


public class PutPetTests extends TestBaseClassAPI {

    @Test
    public void updatingOldRecordWithNewData(){
        // Tworzę nowy obiekt klas SoftAssertions, PutPet, Category i przypisuję do odpowiednich zmiennych
        SoftAssertions softly = new SoftAssertions();
        PutPet putPet = new PutPet();
        Category category = new Category();

        // Wprowadzam parametry do zapytania
        putPet.getRequestBody().setId(123124L);
        putPet.getRequestBody().setName("B Good");
        putPet.getRequestBody().setCategory(category);
        assert putPet.getRequestBody().getCategory() != null;
        putPet.getRequestBody().getCategory().setName("Johny");
        putPet.getRequestBody().setStatus(Pet.StatusEnum.SOLD);

        // Wywołuję metodę z TestBaseClassFrontEnd do wysłania zapytania do API
       getResponsePutSuccessTest(putPet);

        // Dodaję asercje do potwierdzenia powodzenia aktualizacji rekordu
        softly.assertThat(putPet.getResponseBody().getId()).isEqualTo(123124L);
        softly.assertAll();
    }



}
