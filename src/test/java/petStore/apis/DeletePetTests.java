package petStore.apis;

import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import petStore.TestBaseClassAPI;
import petStore.models.Category;
import petStore.models.Pet;
import petStore.models.Tag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeletePetTests extends TestBaseClassAPI {

    @Test

    public void deletingExistingRecord (){

        /* Tworzę nowy obiekt klasy "PostPet", dzięki temu uzyskam dostęp do metod zawartych w tej klasie.
         Obiekt użyję jako jeden z argumentów metody "getResponsePostSuccessTest". */

        PostPet postPet = new PostPet();

        /* Tworzę nowy obiekt klasy "DeletePet", którego użyję jako jeden z argumentów w metodzie
         "getResponseDeleteTest" do wysłania zapytania do API. */

        DeletePet deletePet = new DeletePet();

        /* Tworzę nowy obiekt klasy "SoftAssertions", którego metod użyję do potwierdzenia zgodności otrzymanych
          danych w stosunku do oczekiwanych. */

        SoftAssertions softly = new SoftAssertions();

        /* Tworzę nowy obiekt klasy "Map", który jest interfejsem służącym do przechowywania danych w
          postaci klucz- wartość. Obiekt klasy "String" do którego mogę przypisać dane w postaci
          ciągu znaków posłuży mi jako parametr. Przekazuję go jako drugi argument (wartość) metody "put"
          wywołanej na obiekcie paramsMap. Pierwszym argumentem (klucz) jest wartość "id". */

        Map<String,String> paramsMap = new HashMap<>();
        String id = "1234";
        paramsMap.put("id",id);

        /* Tworzę nowy obiekt klasy "ArrayList", który jest implementacją interfejsu "List". Klasa ta umożliwia
          przechowywanie danych w tablicy. Obiekt tagList przechowuje obiekty typu "Tag". Tworząc nowy obiekt klasy
          "Tag" uzyskuję dostęp do jego metod. Wykorzystuję metody "setId" oraz "setName" do zdefiniowania właściwości
          obiektu firstTag. Następnie wywołuję metodę "add" na obiekcie tagList i przekazuję do niego obiekt firstTag.    */

        List<Tag> tagList = new ArrayList<>();
        Tag firstTag = new Tag();
        firstTag.setId(17128L);
        firstTag.setName("First Pet");
        tagList.add(firstTag);

        /* Tworzę nowy obiekt klasy "Category" i dzięki dostępowi do jego metod używam "setId" i "setName" ustawiam
          jego właściwości.  */

        Category category = new Category();
        category.setId(1231321L);
        category.setName("Nazwa");

        /* Do wcześniej utworzonego obiektu typu "postPet". Do ustawienia jego właściwości używam metod zawartych
          w klasie "postPet". Do testu wykorzystuję wybranych metod, tj. "setId","setName", "setStatus". Dodatkowo
          przy użyciu metod "setCategory" i "setTags" przekazuję wcześniej utworzone i zdefiniowane obiekty "tagList"
          oraz "category". Tak zdefiniowany obiekt "postPet" przekazuję jako argument w metodzie "getResponsePostSuccessTest",
          której zadaniem jest wysłanie wniosku do API o utworzenie pozycji o wcześniej zdefiniowanych informacjach. */

        postPet.getRequestBody().setId(Long.parseLong(id));
        postPet.getRequestBody().setName("Łajka");
        postPet.getRequestBody().setTags(tagList);
        postPet.getRequestBody().setCategory(category);
        postPet.getRequestBody().setStatus(Pet.StatusEnum.AVAILABLE);
        getResponsePostSuccessTest(postPet);

        /* Metody "getResponseDeleteTest" używam do wysłania wniosku do API o usunięcie konkretnej pozycji. W argumentach
          przekazuję obiekty "deletePet", "paramsMap" a także kod oczekiwanej odpowiedzi z API. */

        getResponseDeleteTest(deletePet,paramsMap,200);

        /* Używam metod zawartych w klasie "DeletePet" na obiekcie deletePet do uzyskania dostępu do danych zawartych w
          ciele odpowiedzi z API. Przy pomocy metod klasy "SoftAssertions" weryfikuję czy dane, które otrzymałem są zgodne
          z tymi, których oczekiwałem. */

        softly.assertThat(deletePet.getResponseBody().getCode()).isEqualTo(200);
        softly.assertThat(deletePet.getResponseBody().getType()).isEqualTo("unknown");
        softly.assertThat(deletePet.getResponseBody().getMessage()).isEqualTo(id);
        softly.assertAll();

    }
}
