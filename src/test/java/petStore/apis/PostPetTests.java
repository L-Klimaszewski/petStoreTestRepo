package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassAPI;
import petStore.models.Category;
import petStore.models.Pet;
import petStore.models.Tag;

import java.util.ArrayList;
import java.util.List;


public class PostPetTests extends TestBaseClassAPI {

    @Test
    public void postNewPetToStore(){
        // Tworzę nowy obiekt klas SoftAssertions, PostPet, Category i przypisuję do odpowiednich zmiennych
        SoftAssertions softly = new SoftAssertions();
        PostPet postPet = new PostPet();
        long Id = 1951191000L;
        Category category = new Category();
        Tag firstTag = new Tag();
        firstTag.setId(17128L);
        firstTag.setName("First Pet");
        List<Tag> tagList = new ArrayList<>();
        tagList.add(firstTag);
        category.setName("Nazwa");
        category.setId(1231321L);

        // Wprowadzam parametry do zapytania
        postPet.getRequestBody().setId(Id);
        postPet.getRequestBody().setName("Łajka");
        postPet.getRequestBody().setCategory(category);
        postPet.getRequestBody().setTags(tagList);
        postPet.getRequestBody().setStatus(Pet.StatusEnum.AVAILABLE);


        // Wywołuję metodę z TestBaseClassFrontEnd do wysłania zapytania do API
        getResponsePostSuccessTest(postPet);

        // Dodaję asercje do potwierdzenia powodzenia dodania rekordu
        softly.assertThat(postPet.getResponseBody().getId()).isEqualTo(Id);
        softly.assertAll();
    }

    @Test
    public void negative(){
        PostPet pet = new PostPet();
        pet.getRequestBody().setId(-123141L);
        pet.getRequestBody().setCategory(new Category().id(null));
        getResponsePostSuccessTest(pet);
    }
}
