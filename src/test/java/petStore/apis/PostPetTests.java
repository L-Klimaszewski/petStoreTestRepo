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

        /* Inicializuję nowy obiekt klasy "SoftAssertions", czyli tzw. "miękkie asercje". Pozwoli mi to
          na wykonanie mojego kodu do końca, czyli w tym przypadku do metody "assertAll". Dzięki temu
          mogę sprawdzić kilka warunków jednocześnie bez przerywania testu po pierwszej złamanej asercji.
          Dopiero po wywołaniu metody "assertAll" program sprawdzi asercje i wyświetli informacje o
          powodzeniu lub niepowodzeniu. */

        SoftAssertions softly = new SoftAssertions();

        /* Użycie stałej long pozwoli mi na wprowadzanie w niej zmian w jednym miejscu bez konieczności
          dalszej ingerencji w kod. */

        long id = 1951191000L;

        /* Nowoutworzony obiekty klasy "Category" definiuję przy pomocy metod dostępnych w jego klasie.
          Metoda "setName" zawiera ciąg znaków, który będzie nazwą tej pozycji. Natomiast "setId" ustawia
          parametr Id, przyjmując typ long. */

        Category category = new Category();
        category.setName("Pies");
        category.setId(1231321L);

        /* Tworzę obiekt klasy "ArrayList" ponieważ metoda "setTags", której używam w dalszej części czekuje
          tego typu obiektu. Obiekt tagList przechowuje obiekty typu Tag, dlatego definiuję nowy obiekt tej
          klasy. Ustawiam jego właściwości przy pomocy dostępnych w klasie metod "setId" oraz "setName". Na
          koniec tak ustawiony obiekt przekazuję w argumencie metody "add", użytej na obiekcie tagList. */

        List<Tag> tagList = new ArrayList<>();
        Tag firstTag = new Tag();
        firstTag.setId(17128L);
        firstTag.setName("Cosmic dog");
        tagList.add(firstTag);

        /* Tworzę obiekt klasy "PostPet" i dzięki temu mam dostęp do metod tej klasy. Używam ich do
          zdefiniowania właściwości obiektu. W metodzie "setId" przekazuję obiekt id. Metoda "setName"
          zawiera ciąg znaków. Natomiast w metodach "setCategory" oraz "setTags" jako argumenty dołączam
          wcześniej utworzone i ustawione obiekty klas "Category" i "Tag". Przy pomocy metody "setStatus"
          ustawiam status na dostępny. Na koniec tak zdefiniowany obiekt postPet przekazuję jako jedyny
          argument metody "getResponsePost", która wysyła żądanie do API o utworzenie konkretnej pozycji
          w bazie danych. */

        PostPet postPet = new PostPet();
        postPet.getRequestBody().setId(id);
        postPet.getRequestBody().setName("Łajka");
        postPet.getRequestBody().setCategory(category);
        postPet.getRequestBody().setTags(tagList);
        postPet.getRequestBody().setStatus(Pet.StatusEnum.AVAILABLE);
        getResponsePost(postPet);

        /* Wykonuję metodę "getResponseBody" na obiekcie postPet aby uzyskać dostęp do danych w nim zapisanych.
          Następnie przy użyciu metody "isEqual" porównuję tak otrzymane dane do tych, które wcześniej
          zdefiniowałem i których oczekuję w "body" odpowiedzi z API. Każdy oddzielny warunek do spełnienia
          jest przekazywany jako argument pojedyńczej metody "assertThat". Wywołanie metody "assertAll"
          powoduje sprawdzenie wszystkich asercji i zakończy test z błędem jeśli któraś asercja została
          złamana. */

        softly.assertThat(postPet.getResponseBody().getId()).isEqualTo(id);
        softly.assertThat(postPet.getResponseBody().getCategory().getId()).isEqualTo(category.getId());
        softly.assertThat(postPet.getResponseBody().getCategory().getName()).isEqualTo(category.getName());
        softly.assertThat(postPet.getResponseBody().getName()).isEqualTo("Łajka");
        softly.assertThat(postPet.getResponseBody().getTags().get(0)).isEqualTo(firstTag);
        softly.assertThat(postPet.getResponseBody().getStatus()).isEqualTo(postPet.getRequestBody().getStatus());
        softly.assertAll();
    }

    @Test
    public void negative(){
        PostPet pet = new PostPet();
        pet.getRequestBody().setId(-123141L);
        pet.getRequestBody().setCategory(new Category().id(null));
        getResponsePost(pet);
    }
}
