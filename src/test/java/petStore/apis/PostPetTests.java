package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassBack;
import petStore.models.Category;

public class PostPetTests extends TestBaseClassBack {

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
        postPet.getRequestBody().getCategory().setId(123444L);
        postPet.getRequestBody().getCategory().setName("Johny");

        // Wywołuję metodę z TestBaseClass do wysłania zapytania do API
        getResponsePostSuccessTest(postPet);

        // Dodaję asercje do potwierdzenia powodzenia dodania rekordu
        softly.assertThat(postPet.getResponseBody().getId()).isEqualTo(Id);
        softly.assertAll();
    }
    @Test
    public void checkStatusCodeAndErrorWhen(){

        SoftAssertions softly = new SoftAssertions();
        PostPet4Errors postPet4Errors = new PostPet4Errors();

        postPet4Errors.getRequestBody().setId(1112L);

        getResponsePostSuccessTest(postPet4Errors);

        softly.assertThat(postPet4Errors.getResponseBody().getCode()).isEqualTo(404);
        softly.assertAll();

    }


}
