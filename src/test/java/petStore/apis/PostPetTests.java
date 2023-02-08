package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassBackEnd;
import petStore.models.Category;
import petStore.models.Pet;


public class PostPetTests extends TestBaseClassBackEnd {

    @Test
    public void postNewPetToStore(){
        // Tworzę nowy obiekt klas SoftAssertions, PostPet, Category i przypisuję do odpowiednich zmiennych
        SoftAssertions softly = new SoftAssertions();
        PostPet postPet = new PostPet();
        long Id = 1951191000L;
        Category category = new Category();

        // Wprowadzam parametry do zapytania
        postPet.getRequestBody().setId(Id);
        postPet.getRequestBody().setName("Łajka");
        postPet.getRequestBody().setCategory(category);
        assert postPet.getRequestBody().getCategory() != null;
        postPet.getRequestBody().getCategory().setId(123444L);
        postPet.getRequestBody().getCategory().setName("Johny");
        //postPet.getRequestBody().setTags();                    ZAPYTAĆ
        postPet.getRequestBody().setStatus(Pet.StatusEnum.AVAILABLE);


        // Wywołuję metodę z TestBaseClass do wysłania zapytania do API
        getResponsePostSuccessTest(postPet);

        // Dodaję asercje do potwierdzenia powodzenia dodania rekordu
        softly.assertThat(postPet.getResponseBody().getId()).isEqualTo(Id);
        softly.assertAll();
    }



}
